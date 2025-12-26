package com.SmartBooking.Controller;


import com.SmartBooking.Modelos.Reserva.DadosCriarReserva;
import com.SmartBooking.servicos.ServicoReserva;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("reserva")
public class ReservaController {

    @Autowired
    private ServicoReserva servicoReserva;

    @PostMapping
    @Transactional
    public ResponseEntity criarReserva(@RequestBody @Valid DadosCriarReserva dados, UriComponentsBuilder uriBuilder){
            var dto = servicoReserva.criarReserva(dados);
            var uri = uriBuilder.path("reserva/{id}").buildAndExpand(dto.id()).toUri();
            return  ResponseEntity.created(uri).body(dto);
    }


}
