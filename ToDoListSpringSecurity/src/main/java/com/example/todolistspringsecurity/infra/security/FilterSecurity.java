package com.example.todolistspringsecurity.infra.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.todolistspringsecurity.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FilterSecurity extends OncePerRequestFilter {


    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenJWT = recuperaToken(request);
        if(tokenJWT != null) {

            try{
                String subject = tokenService.getSubject(tokenJWT);
                UserDetails usuario = usuarioRepository.findByLogin(subject);
                Authentication authentication = new UsernamePasswordAuthenticationToken(usuario,null,usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }catch(JWTVerificationException e){
                throw new JWTVerificationException("Token Inválido");
            }

        }
        filterChain.doFilter(request,response);
    }

    private String recuperaToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null){
            return authorizationHeader.replace("Bearer","");
        }
        return null;
    }
}
