package com.SmartBooking.Modelos.Reserva.Validators;


import com.SmartBooking.exception.ValidacaoException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Validador responsável por garantir a consistência do intervalo de tempo
 * informado em operações de criação ou atualização de reservas.
 *
 * <p>
 * Este validador assegura que os campos {@code inicio} e {@code fim} sejam
 * informados de forma conjunta. Ou seja:
 * </p>
 *
 * <ul>
 *   <li>Se {@code inicio} for informado, {@code fim} também deve ser informado</li>
 *   <li>Se {@code fim} for informado, {@code inicio} também deve ser informado</li>
 * </ul>
 *
 * <p>
 *
 * Na criação da reserva, os campos {@code inicio} e {@code fim} são obrigatórios e
 * possuem validação {@code @NotNull} no DTO {@code CriacaoReservaDTO}.
 * </p>
 * <p>
 * Em cenários de atualização parcial, é permitido que ambos os campos
 * {@code inicio} e {@code fim} sejam {@code null}, indicando que o horário
 * da reserva não será alterado.
 * </p>
 *
 * <h3>Ordem de execução</h3>
 * <p>
 * Este validador deve ser executado antes de quaisquer validações que assumam
 * a existência de um intervalo completo (por exemplo, validações de
 * retroatividade ou conflito de horários).
 * </p>
 *
 * <p>
 * A ordem é garantida pela anotação {@link org.springframework.core.annotation.Order}.
 * </p>
 *
 * @throws ValidacaoException
 *         caso apenas um dos campos {@code inicio} ou {@code fim} seja informado
 */

@Order(1)
@Component
public class ValidadorIntervaloCompleto extends ValidadorBaseReserva {
    @Override
    public void executarValidacao(DadosAgendamentoReserva dados) {

            throw new ValidacaoException("Para alterar o horário da reserva, informe data/hora inicial e final.");
    }

    @Override
    protected boolean  deveExecutar(DadosAgendamentoReserva dados) {
        System.out.println("Obrigado meu Deus.");
        return dados.getInicio() == null || dados.getFim() == null;
    }
}