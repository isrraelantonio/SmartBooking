package com.SmartBooking.Modelos.Espaco.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record CriacaoEspacoDTO(
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
