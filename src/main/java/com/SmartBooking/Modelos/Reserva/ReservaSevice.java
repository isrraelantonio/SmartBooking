package com.SmartBooking.Modelos.Reserva;


import com.SmartBooking.Modelos.Espaco.Espaco;
import com.SmartBooking.Modelos.Espaco.EspacoRepository;
import com.SmartBooking.Modelos.Reserva.Validators.DadosAgendamentoReserva;
import com.SmartBooking.Modelos.Reserva.Validators.ValidadorAgendamentoReserva;
import com.SmartBooking.Modelos.Reserva.dto.*;
import com.SmartBooking.Modelos.Usuario.Usuario;
import com.SmartBooking.Modelos.Usuario.UsuarioRepository;
import com.SmartBooking.exception.ValidacaoException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservaSevice {

    @Autowired
    private List<ValidadorAgendamentoReserva> validadores;

    @Autowired
    private ReservaRepositoy reservaRepositoy;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EspacoRepository espacoRepository;

    public DadosDetalhamentoReservaDTO criarReserva(CriacaoReservaDTO dados) {

        verificarUsuario(dados.usuarioId());
        verificarEspaco(dados.usuarioId());

        validarReserva(dados);

        var usuario = usuarioRepository.getReferenceById(dados.usuarioId());
        var espaco = espacoRepository.findByIdAndDisponivelTrue(dados.espacoId());
        var reserva = new Reserva(dados, usuario, espaco);
        reservaRepositoy.save(reserva);

        return new DadosDetalhamentoReservaDTO(reserva);

    }

    public void validarReserva(DadosAgendamentoReserva dados){
        validadores.forEach(v -> v.validarReserva(dados));

    }

    public void verificarUsuario(Long idUsuario) {

        if (!usuarioRepository.existsByIdAndAtivoTrue(idUsuario)) {
            throw new ValidacaoException("Esse usuário não existe em nosso banco de dados.");
        }
    }

    public void verificarEspaco(Long idEspaco) {

        if (!espacoRepository.existsById(idEspaco)) {
            throw new ValidacaoException("Esse espaco não existe em nosso banco de dados.");
        }

    }


    public String atualizarReservaAdm(AtualizacaoReservaAdmDTO dados, Long id) {
        Usuario usuario = null;
        Espaco espaco = null;
        Reserva reserva;

        if (dados.usuarioId() != null) {
            verificarUsuario(dados.usuarioId());
            usuario = usuarioRepository.getReferenceById(dados.usuarioId());
        }
        if (dados.espacoId() != null) {
            verificarEspaco(dados.espacoId());
            espaco = espacoRepository.findByIdAndDisponivelTrue(dados.espacoId());
        }


        if (StatusReserva.NAO_COMPARECEU.equals(dados.status())){
            reserva = reservaRepositoy.existeReservaStatus(id, List.of(
                    StatusReserva.CONFIRMADO));
            if (reserva == null) {
                throw new ValidacaoException("Reserva não encontrada ou status inválido");
            }
            reserva.marcarComoNaoCompareceu(LocalDateTime.now());
        }else{
            reserva = reservaRepositoy.existeReservaStatus(id, List.of(
                    StatusReserva.PENDENTE,
                    StatusReserva.NEGADA,
                    StatusReserva.CONFIRMADO));
        }

        if (reserva != null) {
            validarReserva(dados);
            reserva.atualizarReservaAdm(dados, espaco, usuario);
            reservaRepositoy.save(reserva);
            return "Reserva atualizada.";
        } else {
            return "Essa reserva já esta confirmada ou não existe em nosso banco de dados.";
        }


    }


    public String atualizarReservaUsuario(AtualizacaoReservaUsuarioDTO dados, Long id) {
        Espaco espaco = null;


        if (dados.espacoId() != null) {
            verificarEspaco(dados.espacoId());
            espaco = espacoRepository.findByIdAndDisponivelTrue(dados.espacoId());
        }

        var reserva = reservaRepositoy.existeReservaStatus(id,List.of(
                StatusReserva.PENDENTE,
                StatusReserva.NEGADA));

        if (reserva != null) {

            if(!StatusReserva.CANCELADA.name().equals(dados.status())){
                throw new ValidacaoException("Operação inválida: o status informado não é permitido. Status esperado: CANCELADA.");
            }

            LocalDateTime dataLimiteCancelamento = reserva.getInicio().minusHours(2);
            LocalDateTime hoje = LocalDateTime.now();

            if(hoje.isAfter(dataLimiteCancelamento)){
                throw  new ValidacaoException("As reservas so podem ser canceladas com 2 horas de antecedência");
            }


            validarReserva(dados);
            reserva.atualizarReservaUsuario(dados, espaco);
            reservaRepositoy.save(reserva);
            return "Reserva atualizada.";
            
        } else {
            return "Essa reserva já esta confirmada ou não existe em nosso banco de dados.";
        }


    }

    public Page reservasDoUsuario(Long id, Pageable paginacao){
        var   page = reservaRepositoy.reservasDoUsuario(id, paginacao).
                map(ReservaPaginadaDTO::new);
        return page;
    }

    public Page listaDeReserva(Pageable paginacao){
        var   page = reservaRepositoy.findAll(paginacao).
                map(ReservaPaginadaDTO::new);
        return page;
    }



    @Scheduled(fixedRate = 300000)
    @Transactional
    public void finalizarReservasExpiradas() {
        List<Reserva> reservas = reservaRepositoy
                .findByStatusAndFimBefore(
                        StatusReserva.CONFIRMADO,
                        LocalDateTime.now()
                );

        reservas.forEach(Reserva::finalizar);
    }







}
