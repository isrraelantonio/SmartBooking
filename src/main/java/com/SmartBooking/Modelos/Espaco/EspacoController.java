package com.SmartBooking.Modelos.Espaco;

import com.SmartBooking.Modelos.Espaco.dto.CriacaoEspacoDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/espaco")
public class EspacoController {

    @Autowired
    public EspacoService servicosEspaco;


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public ResponseEntity cadastrarEspaco(@RequestBody @Valid CriacaoEspacoDTO dados, UriComponentsBuilder uribilder) {
            var dto = servicosEspaco.criacaoEspaco(dados);
            var uri = uribilder.path("/espaco/{id}").buildAndExpand(dto.Id()).toUri();
            return ResponseEntity.created(uri).body(dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public ResponseEntity excluirEspaco(@PathVariable Long id) {
        servicosEspaco.desativarEspaco(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public ResponseEntity ativarEspaco(@PathVariable Long id) {
        servicosEspaco.ativarEspaco(id);
        return ResponseEntity.noContent().build();
    }


    }
