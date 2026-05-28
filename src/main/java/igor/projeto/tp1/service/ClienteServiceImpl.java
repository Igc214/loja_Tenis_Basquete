package igor.projeto.tp1.service;

import java.util.List;

import igor.projeto.tp1.dto.ClienteRequestDTO;
import igor.projeto.tp1.exception.ValidationException;
import igor.projeto.tp1.model.Cliente;
import igor.projeto.tp1.repository.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService {

    @Inject
    ClienteRepository repository;

    @Override
    public List<Cliente> findAll() {
        return repository.findAll().list();
    }

    @Override
    public Cliente findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Cliente create(Cliente cliente) {
        if (cliente.getApelido() == null || cliente.getApelido().isEmpty() ||
            cliente.getTelefone() == null || cliente.getTelefone().isEmpty() ||
            cliente.getEndereco() == null || cliente.getEndereco().isEmpty() ||
            cliente.getCpf() == null || cliente.getCpf().isEmpty() ||
            cliente.getEmail() == null || cliente.getEmail().isEmpty() ||
            cliente.getNumeroDeCompra() == 0) {
            throw new ValidationException("Precisa preencher todos os campos obrigatórios");
        }

        repository.persist(cliente);
        return cliente;
    }

    @Override
    @Transactional
    //Você simplesmente atualiza os campos que o Cliente herdou.
    // O clienteRequestDTO tem os campos do cliente, e o cliente tem os campos do clienteRequestDTO,
    // então é só atualizar os campos do cliente com os campos do clienteRequestDTO.
    public void update(Long id, ClienteRequestDTO dto) {
        Cliente e = findById(id);

        if (e == null) {
            throw new ValidationException("Cliente não encontrado para o ID: " + id);
        }

        if (dto.apelido() == null || dto.apelido().isEmpty() ||
            dto.telefone() == null || dto.telefone().isEmpty() ||
            dto.endereco() == null || dto.endereco().isEmpty() ||
            dto.cpf() == null || dto.cpf().isEmpty() ||
            dto.email() == null || dto.email().isEmpty() ||
            dto.numeroDeCompra() == null) {
            throw new ValidationException("Precisa preencher todos os campos obrigatórios");
        }

        e.setApelido(dto.apelido());
        e.setTelefone(dto.telefone());
        e.setEndereco(dto.endereco());
        e.setCpf(dto.cpf());
        e.setEmail(dto.email());
        e.setNumeroDeCompra(dto.numeroDeCompra());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}