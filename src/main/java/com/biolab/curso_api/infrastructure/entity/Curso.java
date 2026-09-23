package com.biolab.curso_api.infrastructure.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

// Criando entidade de Curso

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Curso {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String nome;

    // Declaração muitos para muitos sendo mapeada pela entidade Aluno

    @ManyToMany(mappedBy = "cursos")
    private Set<Aluno> alunos = new HashSet<>();
}
