package com.SmartBooking.Modelos.Endereco.dto;

import com.SmartBooking.Modelos.Endereco.Endereco;

public record DetalhamentoEnderecoDTO(Long id, String estado, String cidade, String rua, Integer numero, String cep ) {
  public DetalhamentoEnderecoDTO(Endereco endereco){
      this(endereco.getId(), endereco.getEstado(), endereco.getCidade(), endereco.getRua(), endereco.getNumero(), endereco.getCep());
  }
}
