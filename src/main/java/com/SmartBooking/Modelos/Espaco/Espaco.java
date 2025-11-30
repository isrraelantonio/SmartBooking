package com.SmartBooking.Modelos.Espaco;
import com.SmartBooking.Modelos.Endereco.DadosEndereco;
import com.SmartBooking.Modelos.Endereco.Endereco;
import jakarta.persistence.*;
import jakarta.validation.Valid;
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


    public Espaco(CadastrarEspaco dados, Endereco endereco){
        this.nome = dados.nome();
        this.email = dados.email();
        this.capacidade = dados.capacidade();
        this.ambiente = dados.tipodeAmbiente();
        this.endereco = endereco;
        this.andar = dados.andar();
        this.referencia = dados.referencia();

    }

}
