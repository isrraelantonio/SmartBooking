package com.SmartBooking.Modelos.Usuario;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoDoUsuario(
        @NotNull
        Long id,
        String nome,
        String email,
        String senha
) {
}
