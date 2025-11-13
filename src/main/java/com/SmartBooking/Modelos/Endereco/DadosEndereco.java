package com.SmartBooking.Modelos.Endereco;

import jakarta.validation.constraints.NotNull;

public record DadosEndereco(
         Long id,
         @NotNull
         String estado,
         @NotNull
         String cidade,
         @NotNull
         String rua,
         @NotNull
         Integer numero
) {
}
