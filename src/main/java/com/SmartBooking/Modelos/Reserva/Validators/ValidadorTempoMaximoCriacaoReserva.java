package com.SmartBooking.Modelos.Reserva.Validators;


import com.SmartBooking.exception.ValidacaoException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Order(5)
@Component
public class ValidadorTempoMaximoCriacaoReserva extends ValidadorBaseReserva {
    @Override
    public void executarValidacao(DadosAgendamentoReserva dados) {
        var data = LocalDate.now();
        var dataLimite = data.plusDays(30);

        if(dados.getInicio().toLocalDate().isAfter(dataLimite)){
            throw new ValidacaoException("Só podemos registrar reservas com antecedência de 30 dias, não mais que isso.");

        }

    }
}
