package com.SmartBooking.Modelos.Reserva;

import com.SmartBooking.Modelos.Espaco.Espaco;
import com.SmartBooking.Modelos.Usuario.Usuario;
import java.time.LocalDateTime;

public class Reserva {
    private Long id;
    private Usuario usuario;
    private Espaco espaco;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private StatusConfirmacao statusConfirmacao;
}
