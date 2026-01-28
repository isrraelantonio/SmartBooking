package com.SmartBooking.Modelos.Reserva.Validators;




/*
 *Classe base para validadores de agendamento de reservas
 *
 * Centraliza o fluxo de execução e garante que as valiações só sejam executadas mediante condições mínima
 *  A ordem de execução é controlada via {@link Order} presente nos validadores.
 */

public abstract class ValidadorBaseReserva  implements ValidadorAgendamentoReserva {

    @Override
    public void validarReserva(DadosAgendamentoReserva dados) {

        if (!deveExecutar(dados)) {
            return;
        }
        executarValidacao(dados);
    }

    protected abstract void executarValidacao(DadosAgendamentoReserva dados);


    protected  boolean deveExecutar(DadosAgendamentoReserva dados){
        return dados.getInicio() != null && dados.getFim() != null;
    }




}
