package com.SmartBooking.Modelos.Endereco;


import com.SmartBooking.Modelos.Endereco.dto.CriacaoEnderecoDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("endereco")
public class EnderecoController{
    @Autowired
    private EnderecoService servicoEndereco;


    @PostMapping
    @Transactional
    public ResponseEntity cadastrarEndereco(@RequestBody @Valid CriacaoEnderecoDTO dados, UriComponentsBuilder uribilder){
        var dto = servicoEndereco.criarEndereco(dados);
        var uri = uribilder.path("/endereco/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

// Estou enfrentando problemas na atualização de reservas, algum id tem estado nulo


}
