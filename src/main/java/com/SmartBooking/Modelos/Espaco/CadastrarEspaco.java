package com.SmartBooking.Modelos.Espaco;

import com.SmartBooking.Modelos.Endereco.Endereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record CadastrarEspaco(
         @NotNull
         String nome,
         @NotNull
         String email,
         @NotNull
         Integer capacidade,
         @NotNull
         String tipodeAmbiente,
         @NotNull
         Long endereco,
         @NotNull
         String andar,
         @NotNull
         String referencia,
         @NotNull
         LocalTime abertura,
         @NotNull
         LocalTime fechamento
) {
}
