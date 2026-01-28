package com.SmartBooking.Modelos.Reserva.Validators;

import com.SmartBooking.exception.ValidacaoException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


/**
 * Validador responsável por regras de coerência entre datas da reserva.
 *
 * <p><b>Pré-condição:</b>
 * Este validador assume que os campos {@code inicio} e {@code fim}
 * já foram validados quanto à presença.
 *
 * <p>⚠️ Deve ser executado APÓS {@link ValidadorIntervaloCompleto}.
 */
@Order(3)
@Component
public class ValidadorDataInicioFimReserva extends ValidadorBaseReserva {

    private static final long HORAS_MINIMAS_ANTECEDENCIA = 4;

    @Override
    public void executarValidacao(DadosAgendamentoReserva dados){

        var inicio = dados.getInicio();
        var fim = dados.getFim();


        if (fim.isBefore(inicio)) {
            throw new ValidacaoException("A data final não pode ser anterior a data inicial");
        }

        var dataDeAgora = LocalDateTime.now();

            if (inicio.isBefore(dataDeAgora.plusHours(HORAS_MINIMAS_ANTECEDENCIA))) {
            throw new ValidacaoException("O agendamento deve ocorrer com pelo menos 4 horas de antecedência.");
        }

    }


}
