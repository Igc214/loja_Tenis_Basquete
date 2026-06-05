package igor.projeto.tp1.repository;

import java.util.List;
import java.util.Optional;

import igor.projeto.tp1.model.Pedido;
import igor.projeto.tp1.model.StatusPedido;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido> {

    public List<Pedido> findByUsuarioLogin(String login) {
        return find("usuario.login = ?1 order by dataCadastro desc", login).list();
    }

    public Optional<Pedido> findByIdAndUsuarioLogin(Long id, String login) {
        return find("id = ?1 and usuario.login = ?2", id, login).firstResultOptional();
    }

    public List<Pedido> findByStatus(StatusPedido status) {
        return find("status", status).list();
    }
}
