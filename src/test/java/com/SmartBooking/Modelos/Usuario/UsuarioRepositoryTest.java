package com.SmartBooking.Modelos.Usuario;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class UsuarioRepositoryTest {


    @Autowired
    private UsuarioRepository usuarioRepository;


    @Autowired
    private TestEntityManager entityManager;
    


    @Test
    @DisplayName("Deve me retornar um usuário ativo caso exita no banco.")
    void existsByIdAndAtivoTrue() {
        //Arange
       Usuario usuario = criarUsuarioPadrao(true);

       // Act
        Boolean resultado = usuarioRepository.existsByIdAndAtivoTrue(usuario.getId());

        //Assert
        assertEquals(resultado, true);
    }


    @Test
    @DisplayName("Não deve me retornar um usuário.")
    void existsByIdAndAtivoTrue2() {
        //Arange
        Usuario usuario = criarUsuarioPadrao(false);

        // Act
        Boolean resultado = usuarioRepository.existsByIdAndAtivoTrue(usuario.getId());

        //Assert
        assertNotEquals(resultado, true);
    }


    @Test
    @DisplayName("Não deve me retornar um usuário de ID diferente.")
    void existsByIdAndAtivoTrue3() {

        //Arange
        Usuario usuario = criarUsuarioPadrao(true);

        // Act
        Boolean resultado = usuarioRepository.existsByIdAndAtivoTrue(400l);

        //Assert
        assertNotEquals(resultado, true);
    }


    @Test
    @DisplayName("Deve me retornar um UserDatail que correpsonda ao mesmo usuário")
    void findByEmail() {
        //Arange
        Usuario usuario = criarUsuarioPadrao(true);

        // Act
        UserDetails resultado = usuarioRepository.findByEmail(usuario.getEmail());

        //Assert
        assertNotNull(resultado);
        assertEquals(resultado.getPassword(), usuario.getPassword());
        assertEquals(resultado.getUsername(), usuario.getUsername());

    }


    @Test
    @DisplayName("Não deve retornar um UserDatail caso o E-mail não exista")
    void findByEmail2() {
        //Arange
        Usuario usuario = criarUsuarioPadrao(true);

        // Act
        UserDetails resultado = usuarioRepository.findByEmail("user" + System.nanoTime() + "@gmail.com");

        //Assert
        assertNull(resultado);
    }


    private Usuario criarUsuarioPadrao(Boolean estado) {

        Usuario usuario = new Usuario();
        usuario.setNome("Israel maravilhoso");
        usuario.setEmail("israelmaravilhoso@gamail.com");
        usuario.setSenha("471319");
        usuario.setAtivo(estado);

        return entityManager.persistAndFlush(usuario);
    }

}