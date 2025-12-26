package com.SmartBooking.Modelos.Reserva.Validacoes;

import com.SmartBooking.Modelos.Reserva.DadosCriarReserva;
import com.SmartBooking.Modelos.Reserva.ReservaRepositoy;
import com.SmartBooking.exceções.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Order(1)
@Component
public class ValidarRertroatividade implements ValidarAgendamentoDeReserva{

    @Override
    public void validarReserva(DadosCriarReserva dados){
        var dataDehoje = LocalDateTime.now();
        if(dados.inicio().isBefore(dataDehoje)){
            throw new ValidacaoException("Insira uma data válida");
        }

    }
}
