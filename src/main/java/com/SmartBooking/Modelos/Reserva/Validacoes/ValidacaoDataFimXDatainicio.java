package com.SmartBooking.Modelos.Reserva.Validacoes;

import com.SmartBooking.Modelos.Reserva.DadosCriarReserva;
import com.SmartBooking.exceções.ValidacaoException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Order(2)
@Component
public class ValidacaoDataFimXDatainicio implements ValidarAgendamentoDeReserva {

    @Override
    public void validarReserva(DadosCriarReserva dados){
        var dataDeAgora = LocalDateTime.now();
        if(dados.fim().isBefore(dados.inicio())){
            throw new ValidacaoException("A data final não pode ser anterior a data inicial");
        }
        if (dados.inicio().isBefore(dataDeAgora.plusHours(4))) {
            throw new ValidacaoException("O agendamento deve ocorrer com pelo menos 4 horas de antecedência.");
        }


    }


}
