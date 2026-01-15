package com.SmartBooking.Modelos.Usuario;

import com.SmartBooking.Modelos.Usuario.dto.AtualizacaoUsuarioDTO;
import com.SmartBooking.Modelos.Usuario.dto.DadosCriacaoUsuarioDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService servicosUsuario;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarUsuario(@RequestBody @Valid DadosCriacaoUsuarioDTO dados, UriComponentsBuilder uribilder) {
        var dto = servicosUsuario.criarUsuario(dados);
        var uri = uribilder.path("/usuario/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarUsuario(@RequestBody @Valid AtualizacaoUsuarioDTO dados) {
       var dto = servicosUsuario.atualizarUsuario(dados);
       return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirUsuario(@PathVariable Long id) {
        servicosUsuario.excluirUsuario(id);
       return ResponseEntity.noContent().build();
    }

}
