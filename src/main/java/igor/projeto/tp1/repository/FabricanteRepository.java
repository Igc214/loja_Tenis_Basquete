package igor.projeto.tp1.repository;

import jakarta.enterprise.context.ApplicationScoped;
import igor.projeto.tp1.model.Fabricante;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class FabricanteRepository implements PanacheRepository<Fabricante> {
}