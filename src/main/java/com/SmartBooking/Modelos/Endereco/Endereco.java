package com.SmartBooking.Modelos.Endereco;


import com.SmartBooking.Modelos.Endereco.dto.CriacaoEnderecoDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Endereco")
@Table(name = "enderecos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String estado;
    private String cidade;
    private String cep;
    private Integer numero;
    private String rua;



    public Endereco(CriacaoEnderecoDTO dados) {
        this.estado = dados.estado();
        this.cidade = dados.cidade();
        this.rua = dados.rua();
        this.numero = dados.numero();
        this.cep = dados.cep();
    }


    public void AtualizarEndereco(CriacaoEnderecoDTO dados){
        if(dados.estado() != null){
            this.estado = dados.estado();
        }
        if(dados.cidade() != null){
            this.cidade = dados.cidade();
        }
        if(dados.rua() != null){
            this.rua = dados.rua();
        }
        if(dados.rua() != null){
            this.rua = dados.rua();
        }
    }





}
