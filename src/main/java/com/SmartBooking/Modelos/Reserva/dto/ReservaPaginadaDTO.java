package com.SmartBooking.Modelos.Reserva.dto;

import com.SmartBooking.Modelos.Reserva.Reserva;
import com.SmartBooking.Modelos.Reserva.StatusReserva;

import java.time.LocalDateTime;

public record ReservaPaginadaDTO(
        Long id,
        Long usuarioId,
        Long espacoId,
        LocalDateTime inicio,
        LocalDateTime fim,
        StatusReserva status,
        String observacao
) {
    public ReservaPaginadaDTO(Reserva dados){
        this(dados.getId(), dados.getUsuario().getId(), dados.getEspaco().getId(), dados.getInicio(), dados.getFim(), dados.getStatus(), dados.getObservacao());
    }
}
