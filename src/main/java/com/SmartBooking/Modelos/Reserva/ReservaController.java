package com.SmartBooking.Modelos.Reserva;


import com.SmartBooking.Modelos.Reserva.dto.AtualizacaoReservaAdmDTO;
import com.SmartBooking.Modelos.Reserva.dto.AtualizacaoReservaUsuarioDTO;
import com.SmartBooking.Modelos.Reserva.dto.CriacaoReservaDTO;
import com.SmartBooking.Modelos.Reserva.dto.ReservaPaginadaDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ADMIN')")
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

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity <Page<ReservaPaginadaDTO>> reservasDoUsuario(@PathVariable Long id, @PageableDefault(size = 3, sort = "inicio") Pageable paginacao){
        var page =  servicoReserva.reservasDoUsuario(id, paginacao);
        return ResponseEntity.ok(page);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public ResponseEntity <Page<ReservaPaginadaDTO>> todasReservas(@PageableDefault(size = 3, sort = "inicio") Pageable paginacao){
        var page =  servicoReserva.listaDeReserva(paginacao);
        return ResponseEntity.ok(page);
    }


}
