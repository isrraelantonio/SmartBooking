package com.SmartBooking.Modelos.Espaco;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspacoRepository extends JpaRepository<Espaco, Long> {
    boolean existsByEmail(@NotNull String email);


}
