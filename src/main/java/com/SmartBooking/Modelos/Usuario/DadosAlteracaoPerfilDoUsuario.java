package com.SmartBooking.Modelos.Usuario;

import jakarta.validation.constraints.NotNull;

public record DadosAlteracaoPerfilDoUsuario(
        @NotNull
        Long id,
        String nome
) {
}
