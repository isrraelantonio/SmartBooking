package com.SmartBooking.Modelos.Espaco.dto;

import com.SmartBooking.Modelos.Espaco.Espaco;

import java.time.LocalTime;

public record DadosDetalhamentoEspacoDTO(Long Id, String nome, String email, Integer capacidade, String tipodeAmbiente, Long endereco, String andar, String referencia, LocalTime abertura, LocalTime fechamento) {
   public DadosDetalhamentoEspacoDTO(Espaco dados){
       this(dados.getId(), dados.getNome(), dados.getEmail(), dados.getCapacidade(), dados.getAmbiente(), dados.getEndereco().getId(), dados.getAndar(), dados.getReferencia(), dados.getAbertura(), dados.getFechamento());
   }

}
