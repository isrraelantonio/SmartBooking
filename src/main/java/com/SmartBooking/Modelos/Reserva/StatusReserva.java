package com.SmartBooking.Modelos.Reserva;

import com.SmartBooking.exception.ValidacaoException;

import java.util.Arrays;

public enum StatusReserva {
    CONFIRMADO,
    PENDENTE,
    CANCELADA,
    FINALIZADA, // Reserva confirmada cujo período já terminou
    NEGADA,
    NAO_COMPARECEU;


    public static StatusReserva fromString(String valor) {
        try {
            return StatusReserva.valueOf(valor.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new ValidacaoException("Status inválido. Valores aceitos: " + Arrays.toString(values()));
        }
    }
}
