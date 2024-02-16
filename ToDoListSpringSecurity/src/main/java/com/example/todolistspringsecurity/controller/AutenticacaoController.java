package com.example.todolistspringsecurity.controller;

import com.example.todolistspringsecurity.dto.CadastroUsuarioDto;
import com.example.todolistspringsecurity.dto.UsuarioDto;
import com.example.todolistspringsecurity.infra.security.TokenDto;
import com.example.todolistspringsecurity.infra.security.TokenService;
import com.example.todolistspringsecurity.model.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin("http://localhost:3000")
public class AutenticacaoController {

    @Autowired
    AuthenticationManager manager;

    @Autowired
    private TokenService service;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid CadastroUsuarioDto usuario){
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(usuario.login(),usuario.senha());
        Authentication authentication = manager.authenticate(authenticationToken);
        Usuario usuarioLogado = (Usuario)authentication.getPrincipal();
        String tokenJWT = service.gerarToken(usuarioLogado);
        return ResponseEntity.ok(new TokenDto(tokenJWT));
    }
}
