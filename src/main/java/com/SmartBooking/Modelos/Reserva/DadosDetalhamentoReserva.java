package com.SmartBooking.Modelos.Reserva;

import java.time.LocalDateTime;

public record DadosDetalhamentoReserva(
        Long id,
        Long usuarioId,
        Long espacoId,
        LocalDateTime inicio,
        LocalDateTime fim,
        StatusConfirmacao status
){

    public DadosDetalhamentoReserva(Reserva dados){
        this(dados.getId(), dados.getUsuario().getId(), dados.getEspaco().getId(), dados.getInicio(), dados.getFim(), dados.getStatus());
    }
}
