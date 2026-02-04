package com.SmartBooking.Modelos.Usuario;

import com.SmartBooking.Modelos.Reserva.Reserva;
import com.SmartBooking.Modelos.Usuario.dto.AtualizacaoUsuarioDTO;
import com.SmartBooking.Modelos.Usuario.dto.DadosCriacaoUsuarioDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hibernate.engine.internal.Cascade.cascade;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario  implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    @Enumerated(EnumType.STRING)
    private PerfisUsuario perfil = PerfisUsuario.USUARIO;
    private Boolean ativo = true;


    public Usuario(@Valid DadosCriacaoUsuarioDTO dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = dados.senha();

    }

    public void atulizarDados(@Valid AtualizacaoUsuarioDTO dados) {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+ perfil.name()));
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() { return true;}

    @Override
    public boolean isAccountNonLocked() { return true;}

    @Override
    public boolean isCredentialsNonExpired() { return true;}

    @Override
    public boolean isEnabled() { return true;}
}
