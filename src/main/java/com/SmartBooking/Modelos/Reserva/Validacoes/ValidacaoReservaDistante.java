package com.SmartBooking.Modelos.Reserva.Validacoes;


import com.SmartBooking.Modelos.Reserva.DadosCriarReserva;
import com.SmartBooking.exceções.ValidacaoException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Order(4)
@Component
public class ValidacaoReservaDistante implements ValidarAgendamentoDeReserva{
    @Override
    public void validarReserva(DadosCriarReserva dados) {
        var data = LocalDate.now();
        var dataLimite = data.plusDays(30);

        if(dados.inicio().toLocalDate().isAfter(dataLimite)){
            throw new ValidacaoException("Só podemos registrar reservas com antecedência de 30 dias, não mais que isso.");

        }

    }
}
