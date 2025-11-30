package com.SmartBooking.Modelos.Endereco;

import jakarta.validation.constraints.NotNull;

public record CriacaoEndereco(
        String estado,
        @NotNull
        String cidade,
        @NotNull
        String cep,
        @NotNull
        String rua,
        @NotNull
        Integer numero
) {
}
