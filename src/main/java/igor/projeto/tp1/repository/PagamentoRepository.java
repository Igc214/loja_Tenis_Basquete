package igor.projeto.tp1.repository;

import java.util.List;
import java.util.Optional;

import igor.projeto.tp1.model.Pagamento;
import igor.projeto.tp1.model.StatusPagamento;
import igor.projeto.tp1.model.TipoPagamento;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PagamentoRepository implements PanacheRepository<Pagamento> {

    public Optional<Pagamento> findByPedidoId(Long pedidoId) {
        return find("pedido.id", pedidoId).firstResultOptional();
    }

    public Optional<Pagamento> findByIdAndUsuarioLogin(Long id, String login) {
        return find("id = ?1 and pedido.usuario.login = ?2", id, login).firstResultOptional();
    }

    public List<Pagamento> findByUsuarioLogin(String login) {
        return find("pedido.usuario.login = ?1 order by dataCadastro desc", login).list();
    }

    public List<Pagamento> findByStatusPagamento(StatusPagamento statusPagamento) {
        return find("statusPagamento", statusPagamento).list();
    }

    public List<Pagamento> findByTipoPagamento(TipoPagamento tipoPagamento) {
        return find("tipoPagamento", tipoPagamento).list();
    }
}
