package com.biolab.curso_api.service;

import com.biolab.curso_api.infrastructure.DTOs.AlunoDTO;
import com.biolab.curso_api.infrastructure.DTOs.AlunoRequest;
import com.biolab.curso_api.infrastructure.DTOs.AlunoResponse;
import com.biolab.curso_api.infrastructure.entity.Aluno;
import com.biolab.curso_api.infrastructure.entity.Curso;
import com.biolab.curso_api.infrastructure.repository.AlunoRepository;
import com.biolab.curso_api.infrastructure.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// Service para as Regras de negócio

@Service
public class AlunoService {

    // Os dois repositórios necessários para esse service
    private final AlunoRepository alunoRepository;
    private final CursoRepository cursoRepository;

    // Construtor
    public AlunoService(AlunoRepository alunoRepository, CursoRepository cursoRepository) {
        this.alunoRepository = alunoRepository;
        this.cursoRepository = cursoRepository;
    }

    // Método de criar aluno
    public String criarAluno(AlunoDTO dto){
        Aluno a = new Aluno();

        a.setNome(dto.getNome());
        a.setEmail(dto.getEmail());

        /* Prevenção para caso o id do curso não seja nulo,
        * e caso ele não seja, ele irá adicionar cada curso
        * que o usuário colocou para o aluno
        */
        if (dto.getCurso_id() != null) {
            for (Long cursoId : dto.getCurso_id()) {
                Curso curso = cursoRepository.findById(cursoId).orElseThrow();
                a.getCursos().add(curso);
            }
        }

        alunoRepository.save(a);
        return "Aluno criado com sucesso!";
    }

    // Método para mostrar todos
    public List<AlunoResponse> mostrarTodos(){
        return alunoRepository.findAll().stream().map(
                aluno -> new AlunoResponse(
                        aluno.getId(),
                        aluno.getNome(),
                        aluno.getEmail(),
                        // Nessa parte os cursos são mapeados pelo seu id
                        aluno.getCursos()
                                .stream()
                                .map(Curso::getId)
                                .toList())
        ).toList();
    }

    // Método para mostrar apenas pelo id
    public AlunoDTO mostrarPeloId(Long id){
        Aluno aluno = alunoRepository.findById(id).orElseThrow();
        AlunoDTO dto = new AlunoDTO();

        dto.setId(aluno.getId());
        dto.setNome(aluno.getNome());
        dto.setEmail(aluno.getEmail());
        // Nessa parte os cursos são mapeados pelo seu id
        dto.setCurso_id(
                aluno.getCursos()
                        .stream()
                        .map(Curso::getId)
                        .toList()
        );

        return dto;
    }

    // Método para alterar o aluno
    public String alterarAluno(Long id, AlunoRequest req){
        Aluno a = alunoRepository.findById(id).orElseThrow();

        a.setNome(req.getNome());
        a.setEmail(req.getEmail());

        /*Prevenção para caso não tenha curso, e se tiver
        * ele irá mapear e subistituir os cursos antigos pelos
        * novos
        */
        if (req.getCurso_id() != null) {
            for (Long cursoId : req.getCurso_id()) {
                Curso curso = cursoRepository.findById(cursoId).orElseThrow();

                a.getCursos().clear();
                a.getCursos().add(curso);
            }
        }

        alunoRepository.save(a);
        return "Aluno alterado com sucesso!";
    }

    // Método de deletar aluno
    public String deletarAluno(Long id){
        alunoRepository.deleteById(id);

        return "Aluno deletado com sucesso.";
    }
}
