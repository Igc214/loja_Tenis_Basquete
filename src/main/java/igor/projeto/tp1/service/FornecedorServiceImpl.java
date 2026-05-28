package igor.projeto.tp1.service;

import java.util.List;

import igor.projeto.tp1.dto.FornecedorRequestDTO;
import igor.projeto.tp1.exception.ValidationException;
import igor.projeto.tp1.model.Fornecedor;
import igor.projeto.tp1.repository.FornecedorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class FornecedorServiceImpl implements FornecedorService {

    @Inject
    FornecedorRepository repository;

    @Override
    public List<Fornecedor> findAll() {
        return repository.findAll().list();
    }

    @Override
    public Fornecedor findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Fornecedor create(Fornecedor fornecedor) {
        if (fornecedor.getApelido() == null || fornecedor.getApelido().isEmpty() ||
            fornecedor.getTelefone() == null || fornecedor.getTelefone().isEmpty() ||
            fornecedor.getEndereco() == null || fornecedor.getEndereco().isEmpty() ||
            fornecedor.getCnpj() == null || fornecedor.getCnpj().isEmpty()) {
            throw new ValidationException("Precisa preencher todos os campos obrigatórios");
        }

        repository.persist(fornecedor);
        return fornecedor;
    }

    @Override
    @Transactional
    public void update(Long id, FornecedorRequestDTO dto) {
        Fornecedor e = findById(id);

        if (e == null) {
            throw new ValidationException("Fornecedor não encontrado para o ID: " + id);
        }

        if (dto.apelido() == null || dto.apelido().isEmpty() ||
            dto.telefone() == null || dto.telefone().isEmpty() ||
            dto.endereco() == null || dto.endereco().isEmpty() ||
            dto.cnpj() == null || dto.cnpj().isEmpty()) {
            throw new ValidationException("Precisa preencher todos os campos obrigatórios");
        }

        e.setApelido(dto.apelido());
        e.setTelefone(dto.telefone());
        e.setEndereco(dto.endereco());
        e.setCnpj(dto.cnpj());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}