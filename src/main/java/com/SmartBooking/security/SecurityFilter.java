package com.SmartBooking.security;


import com.SmartBooking.Modelos.Usuario.UsuarioRepository;
import com.SmartBooking.exception.ValidacaoException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        var tokenJWT = recuperarToken(request);


        if (tokenJWT != null){
            var subject = tokenService.getsubject(tokenJWT);
            var usuario = repository.findByEmail(subject);
//            var usuario = repository.findById(Long.valueOf(subject))
//                    .orElseThrow(() -> new ValidacaoException("Usuário não encontrado"));

            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("LOGADO NA REQUISIÇÃO");
        }

        filterChain.doFilter(request, response);
    }


    private String recuperarToken(HttpServletRequest request){

        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || authorizationHeader.isBlank()) {
            return null;
        }

        if (!authorizationHeader.startsWith("Bearer ")) {
            return null;
        }

        return authorizationHeader.substring(7);
    }

    /***
     * versão alternativa que também pode ser usada como recuperador de token.
     * O código acima é uma versão melhorada do mesmo.
     */
//    private String recuperarToken(HttpServletRequest request) {
//        var autorizationHeader = request.getHeader("Authorization");
//        if (autorizationHeader != null) {
//            return autorizationHeader.replace("Bearer ", "");
//        }
//        return null;
//
//    }
}
