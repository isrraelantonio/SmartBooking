package com.SmartBooking.Modelos.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);

    boolean existsByIdAndAtivoTrue(Long id);

    UserDetails findByEmail(String login);

}
