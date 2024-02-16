package com.example.todolistspringsecurity.controller;

import com.example.todolistspringsecurity.dto.CadastroTarefaDto;
import com.example.todolistspringsecurity.dto.TarefaDto;
import com.example.todolistspringsecurity.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tarefa")
@CrossOrigin("http://localhost:3000")
public class TarefaController {

    @Autowired
    private TarefaService service;
    @GetMapping
    public ResponseEntity ListarTodasTarefas(){
        try{
            List<TarefaDto> listaTarefas = service.listarTodasTarefas();
            return ResponseEntity.ok(listaTarefas);
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity listarTarefaPorId(@PathVariable Long id){
        try{
            if(service.listarTarefaPorId(id).isPresent()){
                TarefaDto tarefa = new TarefaDto(service.listarTarefaPorId(id).get());
                return ResponseEntity.status(HttpStatus.OK).body(tarefa);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada!");
            }
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity cadastrarTarefa(@RequestBody CadastroTarefaDto dto, UriComponentsBuilder uriBuilder){
        try{
            TarefaDto tarefa = service.cadastrarTarefa(dto);
            URI uri = uriBuilder.path("/tarefa/{id}").buildAndExpand(tarefa.id()).toUri();
            return ResponseEntity.created(uri).body(tarefa);
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity editarTarefa(@RequestBody TarefaDto dto){
        try{
            TarefaDto tarefa = service.editarTarefa(dto);
            return ResponseEntity.status(HttpStatus.OK).body(tarefa);
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarTarefa(@PathVariable Long id){
        try{
            service.deletarTarefa(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
