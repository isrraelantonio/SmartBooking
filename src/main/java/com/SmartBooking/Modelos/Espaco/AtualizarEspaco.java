package com.SmartBooking.Modelos.Espaco;

import com.SmartBooking.Modelos.Endereco.Endereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record AtualizarEspaco(
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
