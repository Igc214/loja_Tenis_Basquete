package igor.projeto.tp1.service;

import java.util.List;

import igor.projeto.tp1.dto.FuncionarioRequestDTO;
import igor.projeto.tp1.exception.ValidationException;
import igor.projeto.tp1.model.Funcionario;
import igor.projeto.tp1.repository.FuncionarioRepository;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;

@ApplicationScoped
public class FuncionarioServiceImpl implements FuncionarioService {

    @Inject
    FuncionarioRepository repository;

    @GET
    @RolesAllowed("ADMIN")// Apenas usuários com a role "ADMIN" podem acessar este endpoint
    @Override
    public List<Funcionario> findAll() {
        return repository.findAll().list();
    }

    @Override
    public Funcionario findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Funcionario create(Funcionario funcionario) {
        if (funcionario.getApelido() == null || funcionario.getApelido().isEmpty() ||
            funcionario.getTelefone() == null || funcionario.getTelefone().isEmpty() ||
            funcionario.getEndereco() == null || funcionario.getEndereco().isEmpty() ||
            funcionario.getCpf() == null || funcionario.getCpf().isEmpty() ||
            funcionario.getCargo() == null || funcionario.getCargo().isEmpty() ||
            funcionario.getSalario() == 0) {
            throw new ValidationException("Precisa preencher todos os campos obrigatórios");
        }

        repository.persist(funcionario);
        return funcionario;
    }

    @Override
    @Transactional
    public void update(Long id, FuncionarioRequestDTO dto) {
        Funcionario e = findById(id);

        if (e == null) {
            throw new ValidationException("Funcionário não encontrado para o ID: " + id);
        }

        if (dto.apelido() == null || dto.apelido().isEmpty() ||
            dto.telefone() == null || dto.telefone().isEmpty() ||
            dto.endereco() == null || dto.endereco().isEmpty() ||
            dto.cpf() == null || dto.cpf().isEmpty() ||
            dto.cargo() == null || dto.cargo().isEmpty() ||
            dto.salario() == null) {
            throw new ValidationException("Precisa preencher todos os campos obrigatórios");
        }

        e.setApelido(dto.apelido());
        e.setTelefone(dto.telefone());
        e.setEndereco(dto.endereco());
        e.setCpf(dto.cpf());
        e.setCargo(dto.cargo());
        e.setSalario(dto.salario());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}