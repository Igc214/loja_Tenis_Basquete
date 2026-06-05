package igor.projeto.tp1.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import igor.projeto.tp1.dto.pedido.ItemPedidoRequestDTO;
import igor.projeto.tp1.dto.pedido.PedidoRequestDTO;
import igor.projeto.tp1.exception.ValidationException;
import igor.projeto.tp1.model.ItemPedido;
import igor.projeto.tp1.model.Pedido;
import igor.projeto.tp1.model.StatusPedido;
import igor.projeto.tp1.model.TenisPerformance;
import igor.projeto.tp1.model.Usuario;
import igor.projeto.tp1.repository.PedidoRepository;
import igor.projeto.tp1.repository.TenisPerformanceRepository;
import igor.projeto.tp1.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class PedidoService implements PedidoServiceInter {

    @Inject
    PedidoRepository pedidoRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    TenisPerformanceRepository tenisRepository;

    @Override
    @Transactional
    public Pedido criar(String login, PedidoRequestDTO dto) {
        Usuario usuario = buscarUsuario(login);
        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setStatus(StatusPedido.PENDENTE);

        List<ItemPedido> itens = new ArrayList<>();
        BigDecimal valorTotal = BigDecimal.ZERO;

        for (ItemPedidoRequestDTO itemDto : dto.itens()) {
            TenisPerformance produto = buscarProduto(itemDto.produtoId());
            validarEstoque(produto, itemDto.quantidade());

            ItemPedido item = new ItemPedido();
            item.setPedido(pedido);
            item.setTenisPerformance(produto);
            item.setQuantidade(itemDto.quantidade());
            item.setValorUnitario(produto.getPreco());
            item.setValorTotal(produto.getPreco().multiply(BigDecimal.valueOf(itemDto.quantidade())));
            itens.add(item);
            valorTotal = valorTotal.add(item.getValorTotal());

        }

        pedido.setItens(itens);
        pedido.setValorTotal(valorTotal);
        pedidoRepository.persist(pedido);
        return pedido;
    }

    @Override
    public List<Pedido> listar(String login, boolean listarTodos) {
        return listarTodos ? pedidoRepository.findAll().list() : pedidoRepository.findByUsuarioLogin(login);
    }

    @Override
    public Pedido buscarPorId(String login, Long id, boolean acessoTotal) {
        if (acessoTotal) {
            Pedido pedido = pedidoRepository.findById(id);
            if (pedido == null) {
                throw new NotFoundException("Pedido nao encontrado");
            }
            return pedido;
        }

        return pedidoRepository.findByIdAndUsuarioLogin(id, login)
                .orElseThrow(() -> new NotFoundException("Pedido nao encontrado"));
    }

    @Override
    @Transactional
    public Pedido atualizarStatus(Long id, StatusPedido status) {
        Pedido pedido = buscarPorId(null, id, true);
        if (status == StatusPedido.PAGO) {
            throw new ValidationException("Pedido so pode ser marcado como pago por pagamento aprovado", "status");
        }
        if (status == StatusPedido.CANCELADO && pedido.getStatus() == StatusPedido.PAGO && pedido.getPagamento() != null) {
            recomporEstoque(pedido);
            pedido.getPagamento().setStatusPagamento(igor.projeto.tp1.model.StatusPagamento.CANCELADO);
        }
        pedido.setStatus(status);
        pedidoRepository.persist(pedido);
        return pedido;
    }

    @Override
    @Transactional
    public void cancelar(String login, Long id, boolean acessoTotal) {
        Pedido pedido = buscarPorId(login, id, acessoTotal);
        if (pedido.getStatus() == StatusPedido.CANCELADO) {
            throw new ValidationException("Pedido ja esta cancelado", "status");
        }
        if (pedido.getPagamento() != null) {
            if (pedido.getPagamento().getStatusPagamento() == igor.projeto.tp1.model.StatusPagamento.APROVADO) {
                recomporEstoque(pedido);
            }
            pedido.getPagamento().setStatusPagamento(igor.projeto.tp1.model.StatusPagamento.CANCELADO);
        }
        pedido.setStatus(StatusPedido.CANCELADO);
        pedidoRepository.persist(pedido);
    }

    private Usuario buscarUsuario(String login) {
        return usuarioRepository.findByLogin(login)
                .orElseThrow(() -> new NotFoundException("Usuario nao encontrado"));
    }

    private TenisPerformance buscarProduto(Long id) {
        TenisPerformance produto = tenisRepository.findById(id);
        if (produto == null) {
            throw new NotFoundException("Produto nao encontrado");
        }
        if (!produto.isAtivo()) {
            throw new NotFoundException("Produto nao encontrado");
        }
        return produto;
    }

    private void validarEstoque(TenisPerformance produto, Integer quantidade) {
        if (produto.getEstoque() == null || produto.getEstoque() < quantidade) {
            throw new ValidationException("Estoque insuficiente para o produto " + produto.getNome(), "quantidade");
        }
    }

    private void recomporEstoque(Pedido pedido) {
        for (ItemPedido item : pedido.getItens()) {
            TenisPerformance produto = item.getTenisPerformance();
            produto.setEstoque(produto.getEstoque() + item.getQuantidade());
        }
    }
}
