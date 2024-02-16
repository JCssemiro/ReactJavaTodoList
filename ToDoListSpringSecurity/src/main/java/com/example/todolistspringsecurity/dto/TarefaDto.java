package com.example.todolistspringsecurity.dto;

import com.example.todolistspringsecurity.model.Tarefa;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.Optional;

public record TarefaDto(
        @NotNull Long id,
        @NotNull String titulo,
        @NotNull String descricao,
        @NotNull Instant criadoEm,
        @NotNull Instant atualizadoEm,
        @NotNull Boolean concluido
){
    public TarefaDto(Tarefa tarefa){
        this(tarefa.getId(), tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getCriadoEm(),tarefa.getAtualizadoEm(),tarefa.getConcluido());
    }

}
