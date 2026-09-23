package com.biolab.curso_api.controller;

import com.biolab.curso_api.infrastructure.DTOs.AlunoDTO;
import com.biolab.curso_api.infrastructure.DTOs.AlunoRequest;
import com.biolab.curso_api.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("aluno")
public class AlunoController {

    // Service
    private final AlunoService alunoService;

    // Construtor
    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    // Post para criar aluno
    @PostMapping
    public ResponseEntity<?> criarAluno(@RequestBody AlunoDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(alunoService.criarAluno(dto));
    }

    // Get para mostrar todos os alunos
    @GetMapping
    public ResponseEntity<?> mostrarTodos(){
        return ResponseEntity.ok(alunoService.mostrarTodos());
    }

    // Get para mostrar os alunos pelo id
    @GetMapping("/{id}")
    public ResponseEntity<?> mostrarPeloId(@PathVariable Long id){
        return ResponseEntity.ok(alunoService.mostrarPeloId(id));
    }

    // Put para alterar aluno e/ou o seu curso
    @PutMapping ("/{id}")
    public ResponseEntity<?> alterarAluno(@PathVariable Long id,@Valid @RequestBody AlunoRequest request){
        return ResponseEntity.ok(alunoService.alterarAluno(id, request));
    }

    // Delete para deletar o aluno
    @DeleteMapping ("/{id}")
    public ResponseEntity<?> deletarAluno(@PathVariable Long id){
        alunoService.deletarAluno(id);
        return ResponseEntity.noContent().build();
    }
}
