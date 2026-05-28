package igor.projeto.tp1.service;

import java.util.List;

import igor.projeto.tp1.dto.FuncionarioRequestDTO;
import igor.projeto.tp1.model.Funcionario;

public interface FuncionarioService {
    List<Funcionario> findAll();
    Funcionario findById(Long id);
    Funcionario create(Funcionario funcionario);
    void update(Long id, FuncionarioRequestDTO  dto);
    void delete(Long id);
}