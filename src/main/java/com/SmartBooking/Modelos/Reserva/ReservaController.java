package com.SmartBooking.Modelos.Reserva;


import com.SmartBooking.Modelos.Reserva.dto.AtualizacaoReservaAdmDTO;
import com.SmartBooking.Modelos.Reserva.dto.AtualizacaoReservaUsuarioDTO;
import com.SmartBooking.Modelos.Reserva.dto.CriacaoReservaDTO;
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
    private ReservaSevice servicoReserva;

    @PostMapping
    @Transactional
    public ResponseEntity criarReserva(@RequestBody @Valid CriacaoReservaDTO dados, UriComponentsBuilder uriBuilder){
            var dto = servicoReserva.criarReserva(dados);
            var uri = uriBuilder.path("reserva/{id}").buildAndExpand(dto.id()).toUri();
            return  ResponseEntity.created(uri).body(dto);
    }

    @PatchMapping("adm/{id}")
    @Transactional
    public ResponseEntity atualizarReservaAdm( @PathVariable Long id, @RequestBody AtualizacaoReservaAdmDTO dados){
        var dto = servicoReserva.atualizarReservaAdm(dados, id);
        return ResponseEntity.ok(dto);
    }

    @PatchMapping("usuario/{id}")
    @Transactional
    public ResponseEntity atualizarReservaUsuario( @PathVariable Long id, @RequestBody AtualizacaoReservaUsuarioDTO dados){
        var dto = servicoReserva.atualizarReservaUsuario(dados, id);
        return ResponseEntity.ok(dto);
    }
    

}
