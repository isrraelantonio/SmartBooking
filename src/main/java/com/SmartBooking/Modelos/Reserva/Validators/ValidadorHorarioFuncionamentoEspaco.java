package com.SmartBooking.Modelos.Reserva.Validators;

import com.SmartBooking.Modelos.Espaco.EspacoRepository;
import com.SmartBooking.Modelos.Reserva.ReservaRepositoy;
import com.SmartBooking.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(6)
@Component
public class ValidadorHorarioFuncionamentoEspaco extends ValidadorBaseReserva {

    @Autowired
    private ReservaRepositoy reservaRepositoy;

    @Autowired
    private EspacoRepository espacoRepository;

    @Override
    public void executarValidacao(DadosAgendamentoReserva dados) {
        var espaco = espacoRepository.existeEspaco(dados.getEspacoId());

        if(espaco != null){
            if(dados.getInicio().toLocalTime().isBefore(espaco.getAbertura()) || dados.getInicio().toLocalTime().isAfter(espaco.getFechamento())){
                throw new ValidacaoException("horário de início definido  está fora do horario de abertura ou fechamento do espaco");
            }
            if(dados.getFim().toLocalTime().isBefore(espaco.getAbertura()) || dados.getFim().toLocalTime().isAfter(espaco.getFechamento())){
                throw new ValidacaoException("horário de encerramento definido  está fora do horario de abertura ou fechamento do espaco");
            }
        } else{
            throw new ValidacaoException("Esse espaco não existe em nosso banco de dados");
        }


    }
  
}
