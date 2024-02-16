package com.example.todolistspringsecurity.controller;


import com.example.todolistspringsecurity.dto.CadastroUsuarioDto;
import com.example.todolistspringsecurity.dto.UsuarioDto;
import com.example.todolistspringsecurity.model.Usuario;
import com.example.todolistspringsecurity.service.UsuarioService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("http://localhost:3000")
public class UsuarioController {

    @Autowired
    private UsuarioService service;
    @GetMapping
    public ResponseEntity listarTodosUsuarios(){
        try {
            List<UsuarioDto> listaUsuarios = service.listarTodosUsuarios();
            return ResponseEntity.status(HttpStatus.OK).body(listaUsuarios);
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity listarUsuarioPorId(@PathVariable Long id){
        try{
            if(service.listarUsuarioPorId(id).isPresent()){
                Usuario usuario = service.listarUsuarioPorId(id).get();
                return ResponseEntity.status(HttpStatus.OK).body(new UsuarioDto(usuario));
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário Não encontrado!");
            }
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity editarUsuario(@RequestBody UsuarioDto dto){
        try{
            service.editarUsuario(dto);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity cadastrarUsuario(@RequestBody CadastroUsuarioDto dto, UriComponentsBuilder uriBuilder){
        try{
            UsuarioDto usuario = service.cadastrarUsuario(dto);
            URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.id()).toUri();
            return ResponseEntity.created(uri).body(usuario);
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarUsuario(@PathVariable Long id){
        try{
            service.deletarUsuario(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
