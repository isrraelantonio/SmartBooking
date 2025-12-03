package com.SmartBooking.Controller;

import com.SmartBooking.Modelos.Usuario.*;
import com.SmartBooking.servicos.ServicosUsuario;
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
    private ServicosUsuario servicosUsuario;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarUsuario(@RequestBody @Valid DadosCriacaoUsuario dados, UriComponentsBuilder uribilder) {
        var dto = servicosUsuario.criarUsuario(dados);
        var uri = uribilder.path("/usuario/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarUsuario(@RequestBody @Valid DadosAtualizacaoDoUsuario dados) {
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
