package com.SmartBooking.Modelos.Espaco;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EspacoRepository extends JpaRepository<Espaco, Long> {
    boolean existsByEmail(@NotNull String email);

    @Query("SELECT e FROM Espaco e WHERE e.id = :id AND e.disponivel = true")
    Espaco existeEspaco(@NotNull Long id);

    @Query("SELECT e FROM Espaco e WHERE e.id = :id AND e.disponivel = false")
    Espaco existeEspacoDesativado(@NotNull Long id);

}
