package com.SmartBooking.Modelos.Endereco.dto;

import jakarta.validation.constraints.NotNull;

public record CriacaoEnderecoDTO(
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
