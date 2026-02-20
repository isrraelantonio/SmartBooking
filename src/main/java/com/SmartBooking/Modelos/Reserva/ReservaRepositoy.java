package com.SmartBooking.Modelos.Reserva;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservaRepositoy extends JpaRepository<Reserva, Long> {

    @Query("""
    SELECT r FROM Reserva r
    WHERE r.id = :id
    AND r.status IN :status
""")
    Reserva existeReservaStatus(
            @Param("id") Long id,
            @Param("status") List<StatusReserva> status
    );

    @Query("""
            SELECT r FROM Reserva r
            WHERE r.usuario.id = :id
            """)
    Page<Reserva> reservasDoUsuario(Long id, Pageable paginacao);


    Page<Reserva> findAll(Pageable paginacao);

    List<Reserva> findByStatusAndFimBefore(StatusReserva statusReserva, LocalDateTime now);

}


