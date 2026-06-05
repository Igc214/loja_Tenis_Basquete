package igor.projeto.tp1.repository;

import java.util.List;
import java.util.Optional;

import igor.projeto.tp1.model.ListaDesejo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ListaDesejoRepository implements PanacheRepository<ListaDesejo> {

    public List<ListaDesejo> findByUsuarioLogin(String login) {
        return find("usuario.login", login).list();
    }

    public Optional<ListaDesejo> findByIdAndUsuarioLogin(Long id, String login) {
        return find("id = ?1 and usuario.login = ?2", id, login).firstResultOptional();
    }

    public Optional<ListaDesejo> findByUsuarioLoginAndProdutoId(String login, Long produtoId) {
        return find("usuario.login = ?1 and tenisPerformance.id = ?2", login, produtoId).firstResultOptional();
    }

    public boolean existsByUsuarioLoginAndProdutoId(String login, Long produtoId) {
        return count("usuario.login = ?1 and tenisPerformance.id = ?2", login, produtoId) > 0;
    }
}
