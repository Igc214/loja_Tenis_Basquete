package igor.projeto.tp1.repository;

import jakarta.enterprise.context.ApplicationScoped;
import igor.projeto.tp1.model.Funcionario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class FuncionarioRepository implements PanacheRepository<Funcionario> {
}