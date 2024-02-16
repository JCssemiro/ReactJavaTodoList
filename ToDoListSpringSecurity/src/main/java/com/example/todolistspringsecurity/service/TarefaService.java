package com.example.todolistspringsecurity.service;


import com.example.todolistspringsecurity.dto.CadastroTarefaDto;
import com.example.todolistspringsecurity.dto.TarefaDto;
import com.example.todolistspringsecurity.model.Tarefa;
import com.example.todolistspringsecurity.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository repository;

    public List<TarefaDto> listarTodasTarefas(){
        try {
            return repository.findAll().stream().map(TarefaDto::new).toList();
        }catch(RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public Optional<Tarefa> listarTarefaPorId(Long id){
        try{
            return repository.findById(id);
        }catch(RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public TarefaDto editarTarefa(TarefaDto tarefaEditada){
        try{
        Tarefa tarefa = repository.findById(tarefaEditada.id()).get();
        tarefa.setTitulo(tarefaEditada.titulo());
        tarefa.setDescricao(tarefaEditada.descricao());
        tarefa.setConcluido(tarefaEditada.concluido());
        tarefa.setAtualizadoEm(Instant.now());
        return new TarefaDto(repository.save(tarefa));
        }catch(RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public TarefaDto cadastrarTarefa(CadastroTarefaDto tarefa){
        try {
            Tarefa tarefaCadastrada = new Tarefa(tarefa);
            repository.save(tarefaCadastrada);
            return new TarefaDto(tarefaCadastrada);
        }catch(RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deletarTarefa(Long id){try{repository.deleteById(id);}catch(RuntimeException e){throw new RuntimeException(e.getMessage());}}

}
