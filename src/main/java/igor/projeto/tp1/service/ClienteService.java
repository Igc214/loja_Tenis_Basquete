package igor.projeto.tp1.service;

import java.util.List;

import igor.projeto.tp1.dto.ClienteRequestDTO;
import igor.projeto.tp1.model.Cliente;

public interface ClienteService {
    List<Cliente> findAll();
    Cliente findById(Long id);
    Cliente create(Cliente cliente);
    void update(Long id, ClienteRequestDTO dto);
    void delete(Long id);
}