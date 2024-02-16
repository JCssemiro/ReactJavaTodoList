package com.example.todolistspringsecurity.repository;

import com.example.todolistspringsecurity.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa,Long> {

}
