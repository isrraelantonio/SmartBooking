package com.SmartBooking.Modelos.Endereco;

import java.util.Objects;

public record DadosDetalhamentoEndereco(Long id, String estado, String cidade, String rua, Integer numero, String cep ) {
  public  DadosDetalhamentoEndereco(Endereco endereco){
      this(endereco.getId(), endereco.getEstado(), endereco.getCidade(), endereco.getRua(), endereco.getNumero(), endereco.getCep());
  }
}
