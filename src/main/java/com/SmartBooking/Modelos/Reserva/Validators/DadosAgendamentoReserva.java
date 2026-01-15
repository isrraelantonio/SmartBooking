package com.SmartBooking.Modelos.Reserva.Validators;

import java.time.LocalDateTime;

public interface DadosAgendamentoReserva {
    LocalDateTime getInicio();
    LocalDateTime getFim();
    Long getEspacoId();

}
