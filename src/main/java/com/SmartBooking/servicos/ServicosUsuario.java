package com.SmartBooking.servicos;

import com.SmartBooking.Modelos.Usuario.*;
import com.SmartBooking.exceções.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicosUsuario {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public DadosDetalhamentoUsuario criarUsuario(DadosCriacaoUsuario dados){
        if (usuarioRepository.existsByEmail(dados.email())) {
            throw new ValidacaoException("Esse E-mail já está cadastrado");
        } else {
            var usuario = new Usuario(dados);
            usuarioRepository.save(usuario);
            return new DadosDetalhamentoUsuario(usuario);

        }

    }

    public String  atualizarUsuario(DadosAtualizacaoDoUsuario dados){
        if (usuarioRepository.existsByEmail(dados.email())) {
            throw  new ValidacaoException("Esse E-mail já está cadastrado");
        } else {
            var usuario = usuarioRepository.getReferenceById(dados.id());
            usuario.atulizarDados(dados);
            return "Usuário atualizado com sucesso!";
        }
    }

    public  void excluirUsuario(Long id){

        if (!usuarioRepository.existsByIdAndAtivoTrue(id)) {
            throw  new ValidacaoException("Esse usuário está desativado em nosso banco de dados");
        }

        var usuario = usuarioRepository.getReferenceById(id);
        usuario.excluir();
    }


}
