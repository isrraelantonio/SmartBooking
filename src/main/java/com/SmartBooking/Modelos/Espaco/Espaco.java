package com.SmartBooking.Modelos.Espaco;
import com.SmartBooking.Modelos.Endereco.DadosEndereco;
import com.SmartBooking.Modelos.Endereco.Endereco;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalTime;

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
    private LocalTime abertura;
    private LocalTime fechamento;
    private Boolean disponivel = true;


    public Espaco(CadastrarEspaco dados, Endereco endereco){
        this.nome = dados.nome();
        this.email = dados.email();
        this.capacidade = dados.capacidade();
        this.ambiente = dados.tipodeAmbiente();
        this.endereco = endereco;
        this.andar = dados.andar();
        this.referencia = dados.referencia();
        this.abertura = dados.abertura();
        this.fechamento = dados.fechamento();



    }

    public void desativarespaco(){
        this.disponivel = false;

    }

    public void ativarespaco(){
        this.disponivel = true;

    }


}
