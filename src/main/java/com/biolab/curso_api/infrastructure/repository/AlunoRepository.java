package com.biolab.curso_api.infrastructure.repository;

import com.biolab.curso_api.infrastructure.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
