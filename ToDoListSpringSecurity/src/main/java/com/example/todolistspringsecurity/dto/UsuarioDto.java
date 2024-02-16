package com.example.todolistspringsecurity.dto;

import com.example.todolistspringsecurity.model.Usuario;
import jakarta.validation.constraints.NotNull;

public record UsuarioDto(
        @NotNull Long id,
        @NotNull String login,
        @NotNull String senha
) {
    public UsuarioDto(Usuario usuario){
        this(usuario.getId(),usuario.getLogin(), usuario.getSenha());
    }
}
