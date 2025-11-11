package com.SmartBooking.Modelos.Endereco;


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
    private String rua;
    private Integer numero;

}
