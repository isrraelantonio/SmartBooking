package com.SmartBooking.Controller;

import com.SmartBooking.Modelos.Usuario.*;
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

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarUsuario(@RequestBody @Valid DadosCriacaoUsuario dados, UriComponentsBuilder uribilder) {

        if (usuarioRepository.existsByEmail(dados.email())) {
            return ResponseEntity.badRequest().body("Esse E-mail já está cadastrado");
        } else {
            var usuario = new Usuario(dados);
            usuarioRepository.save(usuario);
            var uri = uribilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
            return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario));
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarUsuario(@RequestBody @Valid DadosAtualizacaoDoUsuario dados) {
        if (usuarioRepository.existsByEmail(dados.email())) {
            return ResponseEntity.badRequest().body("Esse E-mail já está cadastrado");
        } else {
            var usuario = usuarioRepository.getReferenceById(dados.id());
            usuario.atulizarDados(dados);
            return ResponseEntity.ok("Usuário atualizado com sucesso!");

        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirUsuario(@PathVariable Long id) {
        var usuario = usuarioRepository.getReferenceById(id);
        usuario.excluir();
        return ResponseEntity.noContent().build();
    }

}
