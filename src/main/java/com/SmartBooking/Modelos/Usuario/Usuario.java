package com.SmartBooking.Modelos.Usuario;

import com.SmartBooking.Modelos.Reserva.Reserva;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.hibernate.engine.internal.Cascade.cascade;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    @Enumerated(EnumType.STRING)
    private Perfil perfil = Perfil.USUARIO;
    private Boolean ativo = true;


    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Reserva> reservas = new ArrayList<>();


    public Usuario(@Valid DadosCriacaoUsuario dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = dados.senha();

    }

    public void atulizarDados(@Valid DadosAtualizacaoDoUsuario dados) {
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.email() != null){
           this.email = dados.email();
        }
        if(dados.senha() != null){
            this.senha = dados.senha();
        }
    }
    public void excluir(){
        this.ativo = false;
    }

}
