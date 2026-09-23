package com.biolab.curso_api.service;

import com.biolab.curso_api.infrastructure.DTOs.*;
import com.biolab.curso_api.infrastructure.entity.Aluno;
import com.biolab.curso_api.infrastructure.entity.Curso;
import com.biolab.curso_api.infrastructure.repository.AlunoRepository;
import com.biolab.curso_api.infrastructure.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// Service para as Regras de negócio

@Service
public class CursoService {

    // Os dois repositórios necessários para esse service
    private final CursoRepository cursoRepository;
    private final AlunoRepository alunoRepository;

    // Construtor
    public CursoService(CursoRepository cursoRepository, AlunoRepository alunoRepository) {
        this.cursoRepository = cursoRepository;
        this.alunoRepository = alunoRepository;
    }

    // Método para criar o curso
    public String criarCurso(CursoDTO dto){
        Curso c = new Curso();

        c.setNome(dto.getNome());

        cursoRepository.save(c);
        return "Curso criado com sucesso!";
    }

    // Método para mostrar todos os cursos
    public List<CursoResponse> mostrarTodos() {

        return cursoRepository.findAll()
                .stream()
                .map(curso -> new CursoResponse(
                        curso.getId(),
                        curso.getNome(),
                        /*Mapeando os alunos pelo id para adicionar
                        * no curso e não dar problema
                        */
                        curso.getAlunos()
                                .stream()
                                .map(Aluno::getId)
                                .toList()
                ))
                .toList();
    }

    // Método de mostrar curso apenas pelo id
    public CursoDTO mostrarPeloId(Long id){
        Curso curso = cursoRepository.findById(id).orElseThrow();
        CursoDTO dto = new CursoDTO();

        dto.setId(curso.getId());
        dto.setNome(curso.getNome());

        /*Mapeando os alunos pelo id para adicionar
         * no curso e não dar problema
         */
        dto.setAluno_id(
                curso.getAlunos()
                        .stream()
                        .map(Aluno::getId)
                        .toList()
        );

        return dto;
    }

    // Método de alterar curso
    public String alterarCurso(Long id, CursoRequest req){
        Curso c = cursoRepository.findById(id).orElseThrow();

        c.setNome(req.getNome());

        cursoRepository.save(c);
        return "Curso alterado com sucesso!";
    }

    // Método de deletar o curso
    public String deletarCurso(Long id){
        cursoRepository.deleteById(id);

        return "Curso deletado com sucesso.";
    }
}
