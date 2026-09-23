package com.biolab.curso_api.infrastructure.DTOs;

import com.biolab.curso_api.infrastructure.entity.Curso;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/* Response para retornar com segurança a entidade sem precisar retorná-la
*  diretamente pela entidade
*/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoResponse {
    private Long id;
    private String nome;
    private String email;
    private List<Long> curso_id;
}
