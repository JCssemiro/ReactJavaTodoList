package com.example.todolistspringsecurity.dto;

import jakarta.validation.constraints.NotNull;

public record CadastroUsuarioDto(
        @NotNull String login,
        @NotNull String senha
) {
}
