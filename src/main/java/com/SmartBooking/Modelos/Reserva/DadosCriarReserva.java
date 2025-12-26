package com.SmartBooking.Modelos.Reserva;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosCriarReserva(
        @NotNull
        Long usuarioId,
        @NotNull
        Long espacoId,
        @NotNull
        LocalDateTime inicio,
        @NotNull
        LocalDateTime fim
) {
}
