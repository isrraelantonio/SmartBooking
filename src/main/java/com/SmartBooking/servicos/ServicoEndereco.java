package com.SmartBooking.servicos;


import com.SmartBooking.Modelos.Endereco.CriacaoEndereco;
import com.SmartBooking.Modelos.Endereco.DadosDetalhamentoEndereco;
import com.SmartBooking.Modelos.Endereco.Endereco;
import com.SmartBooking.Modelos.Endereco.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicoEndereco {

    @Autowired
    public EnderecoRepository enderecoRepository;

    public DadosDetalhamentoEndereco criarEndereco(CriacaoEndereco dados){
        var endereco = new Endereco(dados);
        enderecoRepository.save(endereco);
        return  new DadosDetalhamentoEndereco(endereco);

    }
}
