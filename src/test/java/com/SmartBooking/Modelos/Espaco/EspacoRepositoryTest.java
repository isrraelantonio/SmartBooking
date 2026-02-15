package com.SmartBooking.Modelos.Espaco;

import com.SmartBooking.Modelos.Endereco.Endereco;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class EspacoRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EspacoRepository repository;

    @Test
    @DisplayName("Deve retornar o espaço quando estiver disponível")
    void findByIdAndDisponivelTrue() {

        // Arrange
        Espaco espaco  = criarEspacoPadrao(true);

        // Act
        Espaco resultado =
                repository.findByIdAndDisponivelTrue(espaco.getId());

        // Assert
        assertNotNull(resultado);
        assertEquals(espaco.getId(), resultado.getId());

    }

    @Test
    @DisplayName("Deve retornar um resultado nulo para espaço não encontrado")
    void findByIdAndDisponivelTrue2() {
        // Arrange
        Espaco espaco  = criarEspacoPadrao(false);

        // Act
        Espaco resultado = repository.findByIdAndDisponivelTrue(espaco.getId());

        // Assert
        assertNull(resultado);

    }

    @Test
    @DisplayName("Deve retornar um resultado nulo para ID inexistente")
    void findByIdAndDisponivelTrue3() {

        // Arrange
        Espaco espaco  = criarEspacoPadrao(true);

        // Act
        Espaco resultado = repository.findByIdAndDisponivelTrue(600l);

        // Assert
        assertNull(resultado);

    }








    @Test
    @DisplayName("Deve retornar o espaço quando estiver indisponível")
    void findByIdAndDisponivelFalse() {

        // Arrange
        Espaco espaco  = criarEspacoPadrao(false);

        // Act
        Espaco resultado =
                repository.findByIdAndDisponivelFalse(espaco.getId());

        // Assert
        assertNotNull(resultado);
        assertEquals(espaco.getId(), resultado.getId());

    }


    @Test
    @DisplayName("Deve retornar um resultado nulo para espaço não encontrado")
    void findByIdAndDisponivelFalse2() {
        // Arrange
        Espaco espaco  = criarEspacoPadrao(true);

        // Act
        Espaco resultado = repository.findByIdAndDisponivelFalse(espaco.getId());

        // Assert
        assertNull(resultado);

    }
    @Test
    @DisplayName("Deve retornar um resultado nulo para ID inexistente")
    void findByIdAndDisponivelFalse3() {

        // Arrange
        Espaco espaco  = criarEspacoPadrao(false);

        // Act
        Espaco resultado = repository.findByIdAndDisponivelFalse(600l);

        // Assert
        assertNull(resultado);

    }




    private Espaco criarEspacoPadrao(boolean disponivel) {
        Endereco endereco = criarEnderecoPadraoPersistido();
        Espaco espaco = new Espaco();
        espaco.setNome("Lounge das Bloguiras");
        espaco.setEmail("blogueiraslounge@gmail.com");
        espaco.setCapacidade(100);
        espaco.setEndereco(endereco);
        espaco.setAmbiente("auditório");
        espaco.setAndar("3° andar");
        espaco.setReferencia("Ao lado da clinica soutaime");
        espaco.setAbertura(LocalTime.of(9, 0));
        espaco.setFechamento(LocalTime.of(20, 0));
        espaco.setDisponivel(disponivel);

        return  entityManager.persistAndFlush(espaco);
    }

    private Endereco criarEnderecoPadraoPersistido() {

        Endereco endereco = new Endereco();
        endereco.setEstado("Paraíba");
        endereco.setCidade("Campina Grande");
        endereco.setRua("Quebra Quilos");
        endereco.setNumero(1);
        endereco.setCep("58-128000");

        return entityManager.persistAndFlush(endereco);
    }
}