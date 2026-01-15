package com.SmartBooking.Modelos.Endereco.dto;

import jakarta.validation.constraints.NotNull;

public record AtualizacaoEnderecoDTO(
        @NotNull
        Long id,
        String estado,
        String cidade,
        String cep,
        String rua,
        Integer numero
) {
}
