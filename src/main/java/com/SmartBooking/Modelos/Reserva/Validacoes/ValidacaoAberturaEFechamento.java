package com.SmartBooking.Modelos.Reserva.Validacoes;

import com.SmartBooking.Modelos.Espaco.Espaco;
import com.SmartBooking.Modelos.Espaco.EspacoRepository;
import com.SmartBooking.Modelos.Reserva.DadosCriarReserva;
import com.SmartBooking.Modelos.Reserva.ReservaRepositoy;
import com.SmartBooking.exceções.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(5)
@Component
public class ValidacaoAberturaEFechamento implements ValidarAgendamentoDeReserva{

    @Autowired
    private ReservaRepositoy reservaRepositoy;

    @Autowired
    private EspacoRepository espacoRepository;

    @Override
    public void validarReserva(DadosCriarReserva dados) {
        var espaco = espacoRepository.existeEspaco(dados.espacoId());

        if(espaco != null){
            if(dados.inicio().toLocalTime().isBefore(espaco.getAbertura()) || dados.inicio().toLocalTime().isAfter(espaco.getFechamento())){
                throw new ValidacaoException("horário de início definido  está fora do horario de abertura ou fechamento do espaco");
            }
            if(dados.fim().toLocalTime().isBefore(espaco.getAbertura()) || dados.fim().toLocalTime().isAfter(espaco.getFechamento())){
                throw new ValidacaoException("horário de início definido  está fora do horario de abertura ou fechamento do espaco");
            }
        } else{
            throw new ValidacaoException("Esse espaco não existe em nosso banco de dados");
        }


    }
}
