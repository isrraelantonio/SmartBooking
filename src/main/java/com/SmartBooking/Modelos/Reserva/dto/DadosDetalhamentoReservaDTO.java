package com.SmartBooking.Modelos.Reserva.dto;

import com.SmartBooking.Modelos.Reserva.Reserva;
import com.SmartBooking.Modelos.Reserva.StatusReserva;

import java.time.LocalDateTime;

public record DadosDetalhamentoReservaDTO(
        Long id,
        Long usuarioId,
        Long espacoId,
        LocalDateTime inicio,
        LocalDateTime fim,
        StatusReserva status
){

    public DadosDetalhamentoReservaDTO(Reserva dados){
        this(dados.getId(), dados.getUsuario().getId(), dados.getEspaco().getId(), dados.getInicio(), dados.getFim(), dados.getStatus());
    }
}
