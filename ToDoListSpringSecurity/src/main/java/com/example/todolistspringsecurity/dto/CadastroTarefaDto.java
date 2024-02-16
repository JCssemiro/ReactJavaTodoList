package com.example.todolistspringsecurity.dto;

import jakarta.validation.constraints.NotNull;

public record CadastroTarefaDto(
        @NotNull String titulo,
        @NotNull String descricao,

        @NotNull Boolean concluido
){}
