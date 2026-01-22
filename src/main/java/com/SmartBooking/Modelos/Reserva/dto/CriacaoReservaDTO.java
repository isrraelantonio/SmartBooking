package com.SmartBooking.Modelos.Reserva.dto;

import com.SmartBooking.Modelos.Reserva.Validators.DadosAgendamentoReserva;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CriacaoReservaDTO(
        @NotNull
        Long usuarioId,
        @NotNull
        Long espacoId,
        @NotNull
        LocalDateTime inicio,
        @NotNull
        LocalDateTime fim
)implements DadosAgendamentoReserva {
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

        @Override
        public String getStatus() {
                return "";
        }
}