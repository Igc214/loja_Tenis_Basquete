package igor.projeto.tp1.service;

import java.util.List;

import igor.projeto.tp1.dto.FabricanteRequestDTO;
import igor.projeto.tp1.model.Fabricante;

public interface FabricanteService {
    List<Fabricante> findAll();

    Fabricante findById(Long id);

    Fabricante create(Fabricante fabricante);

    void update(Long id, FabricanteRequestDTO dto);

    void delete(Long id);
}