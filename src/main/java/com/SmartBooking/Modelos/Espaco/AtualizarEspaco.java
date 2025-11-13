package com.SmartBooking.Modelos.Espaco;

import com.SmartBooking.Modelos.Endereco.Endereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record AtualizarEspaco(
        @NotNull
        Long id,
        String nome,
        String email,
        Integer capacidade,
        String tipodeAmbiente,
        Endereco endereco,
        String andar,
        String referencia
) {
}
