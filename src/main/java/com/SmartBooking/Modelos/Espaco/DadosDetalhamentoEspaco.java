package com.SmartBooking.Modelos.Espaco;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record DadosDetalhamentoEspaco(Long Id, String nome, String email, Integer capacidade, String tipodeAmbiente, Long endereco, String andar, String referencia, LocalTime abertura,  LocalTime fechamento) {
   public DadosDetalhamentoEspaco(Espaco dados){
       this(dados.getId(), dados.getNome(), dados.getEmail(), dados.getCapacidade(), dados.getAmbiente(), dados.getEndereco().getId(), dados.getAndar(), dados.getReferencia(), dados.getAbertura(), dados.getFechamento());
   }

}
