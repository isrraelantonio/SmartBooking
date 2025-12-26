package com.SmartBooking.servicos;


import com.SmartBooking.Modelos.Espaco.Espaco;
import com.SmartBooking.Modelos.Espaco.EspacoRepository;
import com.SmartBooking.Modelos.Reserva.DadosCriarReserva;
import com.SmartBooking.Modelos.Reserva.DadosDetalhamentoReserva;
import com.SmartBooking.Modelos.Reserva.Reserva;
import com.SmartBooking.Modelos.Reserva.ReservaRepositoy;
import com.SmartBooking.Modelos.Reserva.Validacoes.ValidarAgendamentoDeReserva;
import com.SmartBooking.Modelos.Usuario.Usuario;
import com.SmartBooking.Modelos.Usuario.UsuarioRepository;
import com.SmartBooking.exceções.ValidacaoException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoReserva {

    @Autowired
    private List<ValidarAgendamentoDeReserva> validadores;

    @Autowired
    private ReservaRepositoy reservaRepositoy;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EspacoRepository espacoRepository;

    public DadosDetalhamentoReserva criarReserva(DadosCriarReserva dados) {
        if (!usuarioRepository.existsByIdAndAtivoTrue(dados.usuarioId())){
            throw new ValidacaoException("Esse usuário não existe em nosso banco de dados.");
        }

        if (!espacoRepository.existsById(dados.espacoId())){
            throw new ValidacaoException("Esse espaco não existe em nosso banco de dados.");
        }
       validadores.forEach(v-> v.validarReserva(dados));

        var usuario = usuarioRepository.getReferenceById(dados.usuarioId());
        var espaco = espacoRepository.existeEspaco(dados.espacoId());
        var reserva = new Reserva(dados, usuario, espaco);
        reservaRepositoy.save(reserva);

        return new DadosDetalhamentoReserva(reserva);


    }
}
