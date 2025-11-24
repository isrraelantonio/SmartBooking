package com.SmartBooking.Modelos.Reserva;

import java.time.LocalDateTime;

public record DadosCriarReserva(
        Long usuarioId,
        Long espacoId,
        LocalDateTime inicio,
        LocalDateTime fim,
        StatusConfirmacao status
) {
}
