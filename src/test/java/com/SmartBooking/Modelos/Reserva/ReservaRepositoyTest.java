package com.SmartBooking.Modelos.Reserva;

import com.SmartBooking.Modelos.Endereco.Endereco;
import com.SmartBooking.Modelos.Espaco.Espaco;
import com.SmartBooking.Modelos.Usuario.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ReservaRepositoyTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ReservaRepositoy reservaRepositoy;

    @Test
    @DisplayName("Deve retornar a reserva quando o status estiver na lista informada")
    void existeReservaStatus() {

        //Arange
        Reserva reserva = criarReservaPadrao(StatusReserva.CANCELADA);

        //Act
        Reserva resultado = reservaRepositoy.existeReservaStatus(reserva.getId(), List.of(reserva.getStatus()));

        //Assert
        assertNotNull(resultado);
        assertEquals(reserva.getId(), resultado.getId());
        assertEquals(StatusReserva.CANCELADA, resultado.getStatus());




    }

    @Test
    @DisplayName("Deve retornar nulo quando o status da reserva não estiver na lista informada")
    void existeReservaStatus2() {

        //Arange
        Reserva reserva = criarReservaPadrao(StatusReserva.NEGADA);
        Enum status = StatusReserva.CONFIRMADO;

        //Act
        Reserva resultado = reservaRepositoy.existeReservaStatus(reserva.getId(), List.of((StatusReserva) status));

        //Assert
        assertNull(resultado);
        assertNotEquals(status, reserva.getStatus());

    }

    @Test
    @DisplayName("Deve retornar nulo em caso de ID inexistente.")
    void existeReservaStatus3() {

        //Arange
        Reserva reserva = criarReservaPadrao(StatusReserva.NEGADA);

        //Act
        Reserva resultado = reservaRepositoy.existeReservaStatus(900l, List.of(reserva.getStatus()));

        //Assert
        assertNull(resultado);


    }


    @Test
    @DisplayName("Deve retornar apenas as reservas do usuário informado")
    void reservasDoUsuario() {
        //Arange
        Usuario usuario2 = criarUsuarioPadrao2();
        Usuario usuario1 = criarUsuarioPadrao2();
        criarReservasParaUsuario(usuario1, 20, 3);
        criarReservasParaUsuario(usuario2, 22, 2);
        Pageable pageable = PageRequest.of(0, 10);


        //Act
        Page<Reserva> resultado = reservaRepositoy.reservasDoUsuario(usuario1.getId(), pageable);

        //Assert
        assertEquals(3, resultado.getTotalElements());
        resultado.getContent().forEach(reserva ->
                assertEquals(usuario1.getId(), reserva.getUsuario().getId())
        );
        assertFalse(resultado.hasNext());
        assertTrue(resultado.isFirst());

    }

    @Test
    @DisplayName("Não deve retornar as reservas do usuário informado")
    void reservasDoUsuario2() {
        //Arange
        Usuario usuario2 = criarUsuarioPadrao2();
        Usuario usuario1 = criarUsuarioPadrao2();
        criarReservasParaUsuario(usuario1, 20, 3);
        criarReservasParaUsuario(usuario2, 22, 2);
        Pageable pageable = PageRequest.of(0, 10);


        //Act
        Page<Reserva> resultado = reservaRepositoy.reservasDoUsuario(usuario2.getId(), pageable);

        //Assert
        assertNotEquals(3, resultado.getTotalElements());
        resultado.getContent().forEach(reserva ->
                assertNotEquals(usuario1.getId(), reserva.getUsuario().getId())
        );

    }

    @Test
    @DisplayName("Não deve retornar reservas quando o usuário não existir")
    void reservasDoUsuario3() {
        //Arange
        Usuario usuario2 = criarUsuarioPadrao2();
        Usuario usuario1 = criarUsuarioPadrao2();
        criarReservasParaUsuario(usuario1, 20, 3);
        criarReservasParaUsuario(usuario2, 22, 2);
        Pageable pageable = PageRequest.of(0, 10);


        //Act
        Page<Reserva> resultado = reservaRepositoy.reservasDoUsuario(400l, pageable);

        //Assert
        assertTrue(resultado.isEmpty());

//      ou  --- Ambos equivalem
//      assertEquals(0, resultado.getTotalElements());


    }


    @Test
    @DisplayName("Deve retornar uma reserva com data que antecede ao dia de hoje")
    void findByStatusAndFimBefore() {
        //Arang
        Reserva reserva = criarReservaPadrao(StatusReserva.NEGADA);

        LocalDate hoje = LocalDate.now().plusDays(-1);
        LocalDateTime data1 = hoje.atTime(9, 45);
        LocalDateTime data2 = data1.plusMinutes(45);

        reserva.setInicio(data1);
        reserva.setFim(data2);

        //Act
        List<Reserva> resultados = reservaRepositoy.findByStatusAndFimBefore(StatusReserva.NEGADA, LocalDateTime.now());

        //Assert
        assertEquals(resultados.size(), 1);


    }
    @Test
    @DisplayName("Se o status for diferente nenhuma reserva deve ser retornada")
    void findByStatusAndFimBefore2() {
        //Arang
        Reserva reserva = criarReservaPadrao(StatusReserva.NEGADA);

        LocalDate hoje = LocalDate.now().plusDays(1);
        LocalDateTime data1 = hoje.atTime(9, 45);
        LocalDateTime data2 = data1.plusMinutes(45);

        reserva.setInicio(data1);
        reserva.setFim(data2);

        //Act
        List<Reserva> resultados = reservaRepositoy.findByStatusAndFimBefore(StatusReserva.CONFIRMADO, LocalDateTime.now());

        //Assert
        assertEquals(resultados.size(), 0);
    }

    @Test
    @DisplayName("Não deve retornar reserva com data posterior ao dia de hoje")
    void findByStatusAndFimBefore3() {
        //Arang
        Reserva reserva = criarReservaPadrao(StatusReserva.NEGADA);

        LocalDate hoje = LocalDate.now().plusDays(1);
        LocalDateTime data1 = hoje.atTime(9, 45);
        LocalDateTime data2 = data1.plusMinutes(45);

        reserva.setInicio(data1);
        reserva.setFim(data2);

        //Act
        List<Reserva> resultados = reservaRepositoy.findByStatusAndFimBefore(StatusReserva.NEGADA, LocalDateTime.now());

        //Assert
        assertEquals(resultados.size(), 0);


    }



    private void criarReservasParaUsuario(Usuario usuario,int dia,  int quantidade) {

        LocalDate hoje = LocalDate.now().plusDays(1);
        LocalDateTime data1 = hoje.atTime(9, 45);

        for (int i = 0; i < quantidade; i++) {

            LocalDateTime data2 = data1.plusMinutes(40);
            Reserva reserva = criarReservaPadrao2(data1, data2, usuario);
            entityManager.persist(reserva);
            data1 = data2.plusMinutes(10);
        }

        entityManager.flush();

    }



    private Reserva criarReservaPadrao(StatusReserva status){
        Usuario usuario = criarUsuarioPadrao();
        Espaco espaco = criarEspacoPadrao(true);
        Reserva reserva = new Reserva();
        reserva.setUsuario(usuario);
        reserva.setStatus(status);
        reserva.setEspaco(espaco);
        reserva.setInicio(LocalDateTime.of(2026, 1, 31, 11, 40));
        reserva.setFim(LocalDateTime.of(2026, 1, 31, 12, 20));

        return entityManager.persistAndFlush(reserva);
    }

    private Reserva criarReservaPadrao2(LocalDateTime inicio, LocalDateTime fim, Usuario usuario){
        Espaco espaco = criarEspacoPadrao(true);
        Reserva reserva = new Reserva();
        reserva.setUsuario(usuario);
        reserva.setEspaco(espaco);
        reserva.setInicio(inicio);
        reserva.setFim(fim);

        return entityManager.persistAndFlush(reserva);
    }


    private Usuario criarUsuarioPadrao() {

        Usuario usuario = new Usuario();
        usuario.setNome("Israel maravilhoso");
        usuario.setEmail("israelmaravilhoso@gamail.com");
        usuario.setSenha("471319");

        return entityManager.persistAndFlush(usuario);
    }

    private Usuario criarUsuarioPadrao2() {

        Usuario usuario = new Usuario();
        usuario.setNome("Israel maravilhoso");
        usuario.setEmail("user" + System.nanoTime() + "@gmail.com");
        usuario.setSenha("471319");

        return entityManager.persistAndFlush(usuario);
    }



    private Espaco criarEspacoPadrao(boolean disponivel) {
        Endereco endereco = criarEnderecoPadrao();
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

    private Endereco criarEnderecoPadrao() {

        Endereco endereco = new Endereco();
        endereco.setEstado("Paraíba");
        endereco.setCidade("Campina Grande");
        endereco.setRua("Quebra Quilos");
        endereco.setNumero(1);
        endereco.setCep("58-128000");

        return entityManager.persistAndFlush(endereco);
    }
}