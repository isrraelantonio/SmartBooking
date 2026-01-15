package com.SmartBooking.Modelos.Usuario;

import com.SmartBooking.Modelos.Usuario.dto.AtualizacaoUsuarioDTO;
import com.SmartBooking.Modelos.Usuario.dto.DadosCriacaoUsuarioDTO;
import com.SmartBooking.Modelos.Usuario.dto.DadosDetalhamentoUsuarioDTO;
import com.SmartBooking.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public DadosDetalhamentoUsuarioDTO criarUsuario(DadosCriacaoUsuarioDTO dados){
        if (usuarioRepository.existsByEmail(dados.email())) {
            throw new ValidacaoException("Esse E-mail já está cadastrado");
        } else {
            var usuario = new Usuario(dados);
            usuarioRepository.save(usuario);
            return new DadosDetalhamentoUsuarioDTO(usuario);

        }

    }

    public String  atualizarUsuario(AtualizacaoUsuarioDTO dados){
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
