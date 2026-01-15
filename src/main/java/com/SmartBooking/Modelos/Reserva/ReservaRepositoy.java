package com.SmartBooking.Modelos.Reserva;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservaRepositoy extends JpaRepository<Reserva, Long> {

    @Query("""
    SELECT r FROM Reserva r
    WHERE r.id = :id
    AND r.status IN :status
""")
    Reserva existeReservaDesativadaNegada(
            @Param("id") Long id,
            @Param("status") List<StatusReserva> status
    );


    List<Reserva> findByStatusAndFimBefore(StatusReserva statusReserva, LocalDateTime now);
}


