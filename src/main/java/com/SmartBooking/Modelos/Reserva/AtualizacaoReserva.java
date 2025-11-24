package com.SmartBooking.Modelos.Reserva;

import com.SmartBooking.Modelos.Espaco.Espaco;
import com.SmartBooking.Modelos.Usuario.Usuario;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public record AtualizacaoReserva(
         Long id,
         Long usuarioId,
         Long espacoId,
         LocalDateTime inicio,
         LocalDateTime fim,
         StatusConfirmacao status
) {
}
