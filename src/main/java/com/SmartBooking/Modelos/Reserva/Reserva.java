package com.SmartBooking.Modelos.Reserva;

import com.SmartBooking.Modelos.Espaco.Espaco;
import com.SmartBooking.Modelos.Usuario.Usuario;
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

    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    
    @Enumerated(EnumType.STRING)
    private StatusConfirmacao statusConfirmacao;
}
