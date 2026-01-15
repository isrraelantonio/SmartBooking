package com.SmartBooking.Modelos.Espaco.dto;

import com.SmartBooking.Modelos.Endereco.Endereco;

import java.time.LocalTime;

public record AtualizacaoEspacoDTO(
        String nome,
        String email,
        Integer capacidade,
        String tipodeAmbiente,
        Endereco endereco,
        String andar,
        String referencia,
        LocalTime abertura,
        LocalTime fechamento
) {

}
