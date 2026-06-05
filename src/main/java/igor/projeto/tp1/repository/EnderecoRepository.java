package igor.projeto.tp1.repository;

import java.util.List;
import java.util.Optional;

import igor.projeto.tp1.model.Endereco;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnderecoRepository implements PanacheRepository<Endereco> {

    public List<Endereco> findByUsuarioLogin(String login) {
        return find("usuario.login", login).list();
    }

    public List<Endereco> findByUsuarioId(Long usuarioId) {
        return find("usuario.id", usuarioId).list();
    }

    public Optional<Endereco> findByIdAndUsuarioLogin(Long id, String login) {
        return find("id = ?1 and usuario.login = ?2", id, login).firstResultOptional();
    }
}
