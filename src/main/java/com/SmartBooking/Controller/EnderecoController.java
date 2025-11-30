package com.SmartBooking.Controller;


import com.SmartBooking.Modelos.Endereco.CriacaoEndereco;
import com.SmartBooking.Modelos.Endereco.DadosDetalhamentoEndereco;
import com.SmartBooking.Modelos.Endereco.Endereco;
import com.SmartBooking.Modelos.Endereco.EnderecoRepository;
import com.SmartBooking.Modelos.Espaco.DadosDetalhamentoEspaco;
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
    public EnderecoRepository enderecoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarEndereco(@RequestBody @Valid CriacaoEndereco dados, UriComponentsBuilder uribilder){

        var endereco = new Endereco(dados);
        enderecoRepository.save(endereco);
        var uri = uribilder.path("/endereco/{id}").buildAndExpand(endereco.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoEndereco(endereco));
    }




}
