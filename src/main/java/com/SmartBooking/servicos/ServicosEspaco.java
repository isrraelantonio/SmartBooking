package com.SmartBooking.servicos;


import com.SmartBooking.Modelos.Endereco.EnderecoRepository;
import com.SmartBooking.Modelos.Espaco.CadastrarEspaco;
import com.SmartBooking.Modelos.Espaco.DadosDetalhamentoEspaco;
import com.SmartBooking.Modelos.Espaco.Espaco;
import com.SmartBooking.Modelos.Espaco.EspacoRepository;
import com.SmartBooking.validacao.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ServicosEspaco {

    @Autowired
    public EnderecoRepository enderecoRepository;
    @Autowired
    public EspacoRepository espacoRepository;

    public ResponseEntity<DadosDetalhamentoEspaco> criacaoEspaco(CadastrarEspaco dados, UriComponentsBuilder uribilder) {

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
        var uri = uribilder.path("/espaco/{id}").buildAndExpand(espaco.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoEspaco(espaco));
    }
}
