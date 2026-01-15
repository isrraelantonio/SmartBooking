package com.SmartBooking.Modelos.Espaco;


import com.SmartBooking.Modelos.Endereco.EnderecoRepository;
import com.SmartBooking.Modelos.Espaco.dto.CriacaoEspacoDTO;
import com.SmartBooking.Modelos.Espaco.dto.DadosDetalhamentoEspacoDTO;
import com.SmartBooking.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EspacoService {

    @Autowired
    public EnderecoRepository enderecoRepository;
    @Autowired
    public EspacoRepository espacoRepository;

    public DadosDetalhamentoEspacoDTO criacaoEspaco(CriacaoEspacoDTO dados) {

        if (!enderecoRepository.existsById(dados.endereco())) {
            throw new ValidacaoException("id do endereco não existe!");
        }
        if (dados.capacidade() <= 20) {
            throw new ValidacaoException("Só aceitamos espaços que comportem 20 ou mais pessoas");

        } if(espacoRepository.existsByEmail(dados.email())) {
            throw new ValidacaoException("Esse E-mail já está vinculado a um espaço.");
        }
        var endereco = enderecoRepository.getReferenceById(dados.endereco());
        var espaco = new Espaco(dados, endereco);
        espacoRepository.save(espaco);

        return  new DadosDetalhamentoEspacoDTO(espaco);

    }


    public void desativarEspaco(long id) {
        var espaco = espacoRepository.existeEspaco(id);
        if(espaco != null){
            espaco.desativarespaco();
            espacoRepository.save(espaco);
        }else{
            throw new ValidacaoException("Esse espaço já se encontra desativado ou não existe em nosso banco de dados.");
        }
    }

    public void ativarEspaco(long id) {
        var espaco = espacoRepository.existeEspacoDesativado(id);
        if(espaco != null){
            espaco.ativarespaco();
            espacoRepository.save(espaco);
        }else{
            throw new ValidacaoException("Esse espaço já se encontra ativo ou não existe em nosso banco de dados.");
        }
    }
}
