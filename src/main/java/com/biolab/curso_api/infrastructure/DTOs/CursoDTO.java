package com.biolab.curso_api.infrastructure.DTOs;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// DTO para criar novos cursos

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    // Retornando em lista para que não retorne um erro
    private List<Long> aluno_id;
}
