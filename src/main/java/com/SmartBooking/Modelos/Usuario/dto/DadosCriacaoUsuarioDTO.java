package com.SmartBooking.Modelos.Usuario.dto;

import jakarta.validation.constraints.NotNull;

public record DadosCriacaoUsuarioDTO(
         @NotNull
         String nome,
         @NotNull
         String email,
         @NotNull
         String senha
) {
}
