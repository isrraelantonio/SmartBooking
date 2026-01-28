package com.SmartBooking.Modelos.Reserva.Validators;

import com.SmartBooking.exception.ValidacaoException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.Duration;

/** Esse validador é reponsável por contabilizar o tempo de locação e identificar se o mesmo está de
 *  acordo com o tempo máximo e mínimo premitido para criçaão de uma reserva.
 *
 *
 * <h3>Cenários</h3>
 *
 *  * <b>Criação:</b> ...
 * <P>
 *     Na crição da reserva as informações dos campos {@code inicio}  e {@code fim} são obrigatórias
 * e validadas pelo {@code @NotNull} no DTO {@code CriacaoReservaDTO}
 * </P>
 *
 * * <b>Atualização:</b> ...
 *
 * <p> Tratando-se de atualizações parciais o {@code  if (dados.getInicio() != null && dados.getFim() != null)}
 * garante que em caso de anulidade desses campos as ações do validador  sejam desconsideradas</p>
 *
 *n
 *
 *
 *
 *
 *
 */

@Order(4)
@Component
public class ValidadorDuracaoReserva extends  ValidadorBaseReserva{

    @Override
    public void executarValidacao(DadosAgendamentoReserva dados){

            var duracao = Duration.between(dados.getInicio(), dados.getFim());

            if (duracao.toMinutes() < 30) {
                throw new ValidacaoException("O tempo mínimo para locação é de 30 minutos");
            }
            if (duracao.toHours() > 8) {
                throw new ValidacaoException("O tempo máximo para a locação é de 8 horas.");
            }

    }

}
