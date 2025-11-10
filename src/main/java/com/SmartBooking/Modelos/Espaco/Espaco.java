package com.SmartBooking.Modelos.Espaco;
import com.SmartBooking.Modelos.Endereco.Endereco;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity(name = "Espaco")
@Table(name = "espacos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Espaco {

    @Gene
    private Long id;
    private String nome;
    private String email;
    private Integer capacidade;
    private String tipodeAmbiente;
    private Endereco endereco;
    private String andar;
    private String referencia;
    private Boolean disponivel = true;

}
