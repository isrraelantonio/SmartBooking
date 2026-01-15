package com.SmartBooking.Modelos.Usuario.dto;

import com.SmartBooking.Modelos.Usuario.Usuario;

public record DadosDetalhamentoUsuarioDTO(Long id, String nome, String email) {

    public DadosDetalhamentoUsuarioDTO(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}
