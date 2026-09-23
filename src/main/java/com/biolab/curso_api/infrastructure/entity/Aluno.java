package com.biolab.curso_api.infrastructure.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

// Criando a entidade do Aluno

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String nome;

    @NotBlank
    @Email
    private String email;

    // Declaração muitos para muitos, juntando com a tabela de cursos

    @ManyToMany
    @JoinTable(
            name = "aluno_curso",
            joinColumns = @JoinColumn(name = "aluno_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id")
    )
    private Set<Curso> cursos = new HashSet<>();
}
