package com.SmartBooking.Modelos.Endereco;

import jakarta.validation.constraints.NotNull;

public record AtualizarEndereco(
        @NotNull
        Long id,
        String estado,
        String cidade,
        String cep,
        String rua,
        Integer numero
) {
}
