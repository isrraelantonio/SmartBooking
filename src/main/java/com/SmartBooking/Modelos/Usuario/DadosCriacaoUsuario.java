package com.SmartBooking.Modelos.Usuario;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public record DadosCriacaoUsuario(
         @NotNull
         String nome,
         @NotNull
         String email,
         @NotNull
         String senha
) {
}
