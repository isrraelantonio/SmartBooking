package com.SmartBooking.Controller;

import com.SmartBooking.Modelos.Espaco.*;
import com.SmartBooking.servicos.ServicosEspaco;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/espaco")
public class EspacoController {

    @Autowired
    public ServicosEspaco servicosEspaco;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarEspaco(@RequestBody @Valid CadastrarEspaco dados, UriComponentsBuilder uribilder) {
            var dto = servicosEspaco.criacaoEspaco(dados);
            var uri = uribilder.path("/espaco/{id}").buildAndExpand(dto.Id()).toUri();
            return ResponseEntity.created(uri).body(dto);
        }
    }

