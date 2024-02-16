package com.example.todolistspringsecurity.service;

import com.example.todolistspringsecurity.dto.CadastroUsuarioDto;
import com.example.todolistspringsecurity.dto.UsuarioDto;
import com.example.todolistspringsecurity.model.Usuario;
import com.example.todolistspringsecurity.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UsuarioDto> listarTodosUsuarios(){
        try {
            return repository.findAll().stream().map(UsuarioDto::new).toList();
        }catch(RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public Optional<Usuario> listarUsuarioPorId(Long id){
        try{
            return repository.findById(id);
        }catch(RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public UsuarioDto cadastrarUsuario(CadastroUsuarioDto dto){
        try{
            if(!repository.existsByLogin(dto.login())) {
                Usuario usuario = new Usuario(dto);
                usuario.setSenha(passwordEncoder.encode(dto.senha()));
                return new UsuarioDto(repository.save(usuario));
            }else{
                throw new RuntimeException("Já existe um usuário com esse login");
            }
        }catch(RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public UsuarioDto editarUsuario(UsuarioDto dto){
        try{
            if(!repository.existsByLogin(dto.login())) {
                Usuario usuario = repository.findById(dto.id()).get();
                usuario.setLogin(dto.login());
                usuario.setSenha(passwordEncoder.encode(dto.senha()));
                return new UsuarioDto(repository.save(usuario));
            }else{
                throw new RuntimeException("Já existe um usuário com esse login");
            }
        }catch(RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deletarUsuario(Long id){
        try{
            repository.deleteById(id);
        }catch(RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
