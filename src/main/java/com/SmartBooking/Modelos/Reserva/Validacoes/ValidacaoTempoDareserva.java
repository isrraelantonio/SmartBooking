package com.SmartBooking.Modelos.Reserva.Validacoes;

import com.SmartBooking.Modelos.Reserva.DadosCriarReserva;
import com.SmartBooking.exceções.ValidacaoException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
@Order(3)
@Component
public class ValidacaoTempoDareserva implements ValidarAgendamentoDeReserva{

    @Override
    public void validarReserva(DadosCriarReserva dados){
        var duracao = Duration.between(dados.inicio(), dados.fim());

        if(duracao.toMinutes() < 30){
            throw new ValidacaoException("O tempo mínimo para locação é de 30 minutos");
        }
        if (duracao.toHours() > 8) {
            throw new ValidacaoException("O tempo máximo para a locação é de 8 horas.");
        }


    }

}
