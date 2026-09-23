package com.biolab.curso_api.controller;

import com.biolab.curso_api.infrastructure.DTOs.CursoDTO;
import com.biolab.curso_api.infrastructure.DTOs.CursoRequest;
import com.biolab.curso_api.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("curso")
public class CursoController {

    // Service
    private final CursoService cursoService;

    // Construtor
    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    // Post de criar curso
    @PostMapping
    public ResponseEntity<?> criarCurso(@RequestBody CursoDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cursoService.criarCurso(dto));
    }

    // Get para mostrar todos os cursos
    @GetMapping
    public ResponseEntity<?> mostrarTodos(){
        return ResponseEntity.ok(cursoService.mostrarTodos());
    }

    // Get para mostrar cursos pelo id
    @GetMapping("/{id}")
    public ResponseEntity<?> mostrarPeloId(@PathVariable Long id){
        return ResponseEntity.ok(cursoService.mostrarPeloId(id));
    }

    // Put para alterar os cursos
    @PutMapping("/{id}")
    public ResponseEntity<?> alterarCurso(@PathVariable Long id,@Valid @RequestBody CursoRequest request){
        return ResponseEntity.ok(cursoService.alterarCurso(id, request));
    }

    // Delete para deletar os cursos
    @DeleteMapping ("/{id}")
    public ResponseEntity<?> deletarCurso(@PathVariable long id){
        cursoService.deletarCurso(id);
        return ResponseEntity.noContent().build();
    }
}
