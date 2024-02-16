package com.example.todolistspringsecurity.model;


import com.example.todolistspringsecurity.dto.CadastroTarefaDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name="tb_tarefas")
public class Tarefa {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @Column(columnDefinition = "TEXT")
    private String descricao;

    private Instant criadoEm;

    private Instant atualizadoEm;

    private Boolean concluido;

    public Tarefa(CadastroTarefaDto tarefa) {
        this.titulo = tarefa.titulo();
        this.descricao = tarefa.descricao();
        this.criadoEm = Instant.now();
        this.atualizadoEm = Instant.now();
        this.concluido = false;
    }

    public Tarefa() {

    }
}
