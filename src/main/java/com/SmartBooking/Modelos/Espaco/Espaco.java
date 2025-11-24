package com.SmartBooking.Modelos.Espaco;
import com.SmartBooking.Modelos.Endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Espaco")
@Table(name = "espacos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Espaco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private Integer capacidade;
    private String ambiente;
    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;
    private String andar;
    private String referencia;
    private Boolean disponivel = true;

}
