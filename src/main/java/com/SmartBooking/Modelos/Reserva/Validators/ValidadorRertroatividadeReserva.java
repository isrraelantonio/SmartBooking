package com.SmartBooking.Modelos.Reserva.Validators;

import com.SmartBooking.exception.ValidacaoException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Order(2)
@Component
public class ValidadorRertroatividadeReserva extends ValidadorBaseReserva {

    @Override
    public void executarValidacao(DadosAgendamentoReserva dados){
            var dataDehoje = LocalDateTime.now();
            if(dados.getInicio().isBefore(dataDehoje)){
                throw new ValidacaoException("Insira uma data válida");
            }


    }
}
