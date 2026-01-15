package com.SmartBooking.Modelos.Usuario.dto;

import jakarta.validation.constraints.NotNull;

public record AtualizacaoUsuarioDTO(
        @NotNull
        Long id,
        String nome,
        String email,
        String senha
) {
}
