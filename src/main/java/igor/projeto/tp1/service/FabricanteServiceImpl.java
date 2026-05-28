package igor.projeto.tp1.service;

import java.util.List;

import igor.projeto.tp1.dto.FabricanteRequestDTO;
import igor.projeto.tp1.exception.ValidationException;
import igor.projeto.tp1.model.Fabricante;
import igor.projeto.tp1.repository.FabricanteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class FabricanteServiceImpl implements FabricanteService {

    @Inject
    FabricanteRepository repository;

    @Override
    public List<Fabricante> findAll() {
        return repository.findAll().list();
    }

    @Override
    public Fabricante findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Fabricante create(Fabricante fabricante) {
        if (fabricante.getMarca() == null || fabricante.getMarca().isEmpty() ||
            fabricante.getCnpj() == null || fabricante.getCnpj().isEmpty() ||
            fabricante.getFornecedor() == null || fabricante.getFornecedor().getId() == null || 
            fabricante.getFornecedor().getId() == 0) {
            throw new ValidationException("Precisa preencher todos os campos obrigatórios");
        }

        repository.persist(fabricante);
        return fabricante;
    }

    @Override
    @Transactional
    public void update(Long id, FabricanteRequestDTO dto) {
        Fabricante e = findById(id);

        if (e == null) {
            throw new ValidationException("Fabricante não encontrado para o ID: " + id);
        }

        if (dto.marca() == null || dto.marca().isEmpty() ||
            dto.cnpj() == null || dto.cnpj().isEmpty() ||
            dto.idFornecedor() == null || dto.idFornecedor() == 0) {
            throw new ValidationException("Precisa preencher todos os campos obrigatórios");
        }

        e.setMarca(dto.marca());
        e.setCnpj(dto.cnpj());
       
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}