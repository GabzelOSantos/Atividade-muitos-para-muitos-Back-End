package com.biolab.curso_api.infrastructure.repository;

import com.biolab.curso_api.infrastructure.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
