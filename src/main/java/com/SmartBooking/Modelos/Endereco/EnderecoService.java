package com.SmartBooking.Modelos.Endereco;


import com.SmartBooking.Modelos.Endereco.dto.CriacaoEnderecoDTO;
import com.SmartBooking.Modelos.Endereco.dto.DetalhamentoEnderecoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    public EnderecoRepository enderecoRepository;

    public DetalhamentoEnderecoDTO criarEndereco(CriacaoEnderecoDTO dados){
        var endereco = new Endereco(dados);
        enderecoRepository.save(endereco);
        return  new DetalhamentoEnderecoDTO(endereco);

    }
}
