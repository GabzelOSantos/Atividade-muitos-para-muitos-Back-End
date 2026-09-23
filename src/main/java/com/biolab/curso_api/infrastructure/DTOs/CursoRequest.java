package com.biolab.curso_api.infrastructure.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// Request para pegar apenas informações necessárias para alterar a entidade

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoRequest {
    private String nome;
    private List<Long> aluno_id;
}
