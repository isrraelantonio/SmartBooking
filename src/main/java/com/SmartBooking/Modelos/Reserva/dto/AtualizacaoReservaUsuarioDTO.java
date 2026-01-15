package com.SmartBooking.Modelos.Reserva.dto;

import com.SmartBooking.Modelos.Reserva.Validators.DadosAgendamentoReserva;

import java.time.LocalDateTime;

public record AtualizacaoReservaUsuarioDTO(
         Long espacoId,
         LocalDateTime inicio,
         LocalDateTime fim

) implements DadosAgendamentoReserva {
    @Override
    public LocalDateTime getInicio() {
        return inicio;
    }

    @Override
    public LocalDateTime getFim() {
        return fim;
    }

    @Override
    public Long getEspacoId() {
        return espacoId;
    }
}