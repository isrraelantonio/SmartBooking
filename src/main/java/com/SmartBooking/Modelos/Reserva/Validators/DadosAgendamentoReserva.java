package com.SmartBooking.Modelos.Reserva.Validators;

import java.time.LocalDateTime;


/**
 * Abstração dos dados necessários para validações
 * relacionadas ao agendamento de reservas.
 *
 * Deve ser implementada por DTOs de criação ou atualização.
 */

public interface DadosAgendamentoReserva {
    LocalDateTime getInicio();
    LocalDateTime getFim();
    Long getEspacoId();
    String getStatus();

}
