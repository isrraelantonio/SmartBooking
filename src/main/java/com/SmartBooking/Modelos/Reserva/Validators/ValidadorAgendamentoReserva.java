package com.SmartBooking.Modelos.Reserva.Validators;


/*
 *Contrato de validação de agendamento de reserva
 *
 * As implementações devem lançar {@link ValidacaoExceptin} em caso de violação das regras de negócio.
 *
 */
public interface ValidadorAgendamentoReserva {
    void validarReserva(DadosAgendamentoReserva dados);
}
  