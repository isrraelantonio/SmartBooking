package com.SmartBooking.Modelos.Espaco;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EspacoRepository extends JpaRepository<Espaco, Long> {
    boolean existsByEmail(@NotNull String email);

    Espaco findByIdAndDisponivelTrue(Long id);


    Espaco findByIdAndDisponivelFalse(@NotNull Long id);

}
