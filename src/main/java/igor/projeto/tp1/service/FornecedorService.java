package igor.projeto.tp1.service;

import java.util.List;

import igor.projeto.tp1.dto.FornecedorRequestDTO;
import igor.projeto.tp1.model.Fornecedor;

public interface FornecedorService {
    List<Fornecedor> findAll();
    Fornecedor findById(Long id);
    Fornecedor create(Fornecedor fornecedor);
    void update(Long id, FornecedorRequestDTO dto);
    void delete(Long id);
}