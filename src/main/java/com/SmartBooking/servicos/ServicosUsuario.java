package com.SmartBooking.servicos;

import com.SmartBooking.Modelos.Usuario.*;
import com.SmartBooking.validacao.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ServicosUsuario {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseEntity criarUsuario(DadosCriacaoUsuario dados, UriComponentsBuilder uribilder){
        if (usuarioRepository.existsByEmail(dados.email())) {
            throw new ValidacaoException("Esse E-mail já está cadastrado");
        } else {
            var usuario = new Usuario(dados);
            usuarioRepository.save(usuario);
            var uri = uribilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
            return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario));
        }

    }

    public ResponseEntity  atualizarUsuario(DadosAtualizacaoDoUsuario dados){
        if (usuarioRepository.existsByEmail(dados.email())) {
            return ResponseEntity.badRequest().body("Esse E-mail já está cadastrado");
        } else {
            var usuario = usuarioRepository.getReferenceById(dados.id());
            usuario.atulizarDados(dados);
            return ResponseEntity.ok("Usuário atualizado com sucesso!");

        }
    }

    public  ResponseEntity excluirUsuario(Long id){
        var usuario = usuarioRepository.getReferenceById(id);
        usuario.excluir();
        return ResponseEntity.noContent().build();

    }


}
