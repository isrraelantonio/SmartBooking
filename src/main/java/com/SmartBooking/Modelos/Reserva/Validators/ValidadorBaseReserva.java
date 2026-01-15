package com.SmartBooking.Modelos.Reserva.Validators;

public abstract class ValidadorBaseReserva  implements ValidadorAgendamentoReserva {

    @Override
    public void validarReserva(DadosAgendamentoReserva dados) {

        if (!deveExecutar(dados)) {
            return;
        }
        executarValidacao(dados);
    }

    protected abstract void executarValidacao(DadosAgendamentoReserva dados);


    protected boolean deveExecutar(DadosAgendamentoReserva dados) {
        return dados.getInicio() != null || dados.getFim() != null;
    }



}
