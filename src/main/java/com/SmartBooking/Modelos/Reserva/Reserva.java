package com.SmartBooking.Modelos.Reserva;

import com.SmartBooking.Modelos.Espaco.Espaco;
import com.SmartBooking.Modelos.Reserva.dto.AtualizacaoReservaAdmDTO;
import com.SmartBooking.Modelos.Reserva.dto.AtualizacaoReservaUsuarioDTO;
import com.SmartBooking.Modelos.Reserva.dto.CriacaoReservaDTO;
import com.SmartBooking.Modelos.Usuario.Usuario;
import com.SmartBooking.exception.ValidacaoException;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity(name = "Reserva")
@Table(name = "reservas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "espaco_id")
    private Espaco espaco;

    private LocalDateTime inicio;
    private LocalDateTime fim;

    @Enumerated(EnumType.STRING)
    private StatusReserva status = StatusReserva.PENDENTE;

    private String observacao = "";


    public Reserva(CriacaoReservaDTO dados, Usuario usuario, Espaco espaco) {
        this.usuario = usuario;
        this.espaco = espaco;
        this.inicio = dados.inicio();
        this.fim = dados.fim();
    }

    public void atualizarReservaAdm(AtualizacaoReservaAdmDTO dados, Espaco espaco, Usuario usuario){
        if(dados.inicio() != null){
            this.inicio = dados.inicio();
        }if(dados.fim() != null){
            this.fim = dados.fim();
        }if(dados.status() != null){
            this.status = StatusReserva.fromString(dados.status());
        }if(dados.observacao() != null){
            this.observacao = dados.observacao();
        }
        if (espaco != null && !espaco.equals(getEspaco())) {
            this.espaco = espaco;
        } // Aqui tenho uma estrutura de decisão com sobrescrição de equals e hashcode dentro do objeto Espaço.

        if(usuario != null && !usuario.getId().equals(getUsuario().getId())){
            this.usuario = usuario; // Nesse caso usei uma verificação mais simples sem sobrescrições no usuário.
        }

    }

    public void atualizarReservaUsuario(AtualizacaoReservaUsuarioDTO dados, Espaco espaco) {
        if (dados.inicio() != null) {
            this.inicio = dados.inicio();
        }
        if (dados.fim() != null) {
            this.fim = dados.fim();
        }
        if (espaco != null && !espaco.equals(getEspaco())) {
            this.espaco = espaco;
        }

    }

    public void marcarComoNaoCompareceu(LocalDateTime agora) {
        if (agora.isBefore(this.fim)) {
            throw new ValidacaoException(
                    "Não é possível marcar como não compareceu antes do término da reserva"
            );
        }

        this.status = StatusReserva.NAO_COMPARECEU;
    }

    public void finalizar() {
        if (this.status == StatusReserva.CONFIRMADO) {
            this.status = StatusReserva.FINALIZADA;
        }
    }

}
