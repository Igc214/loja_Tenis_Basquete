package igor.projeto.tp1.service;

import java.time.LocalDateTime;
import java.util.List;

import igor.projeto.tp1.dto.pagamento.PagamentoPatchDTO;
import igor.projeto.tp1.dto.pagamento.PagamentoRequestDTO;
import igor.projeto.tp1.exception.ValidationException;
import igor.projeto.tp1.model.Pagamento;
import igor.projeto.tp1.model.Pedido;
import igor.projeto.tp1.model.StatusPagamento;
import igor.projeto.tp1.model.StatusPedido;
import igor.projeto.tp1.repository.PagamentoRepository;
import igor.projeto.tp1.repository.PedidoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class PagamentoService implements PagamentoServiceInter {

    @Inject
    PagamentoRepository pagamentoRepository;

    @Inject
    PedidoRepository pedidoRepository;

    @Override
    @Transactional
    public Pagamento criar(String login, PagamentoRequestDTO dto) {
        Pedido pedido = pedidoRepository.findByIdAndUsuarioLogin(dto.pedidoId(), login)
                .orElseThrow(() -> new NotFoundException("Pedido nao encontrado"));

        validarPedidoPodeSerPago(pedido);

        if (pagamentoRepository.findByPedidoId(pedido.getId()).isPresent()) {
            throw new ValidationException("Pedido ja possui pagamento", "pedidoId");
        }

        if (pedido.getValorTotal().compareTo(dto.valor()) != 0) {
            throw new ValidationException("Valor do pagamento diferente do valor total do pedido", "valor");
        }

        Pagamento pagamento = new Pagamento();
        pagamento.setPedido(pedido);
        pagamento.setValor(dto.valor());
        pagamento.setTipoPagamento(dto.tipoPagamento());
        pagamento.setStatusPagamento(StatusPagamento.APROVADO);
        pagamento.setDataProcessado(LocalDateTime.now());

        pedido.setPagamento(pagamento);
        debitarEstoque(pedido);
        sincronizarStatusPedido(pagamento);
        pagamentoRepository.persist(pagamento);
        return pagamento;
    }

    @Override
    public List<Pagamento> listar(String login, boolean listarTodos) {
        return listarTodos ? pagamentoRepository.findAll().list() : pagamentoRepository.findByUsuarioLogin(login);
    }

    @Override
    public Pagamento buscarPorId(String login, Long id, boolean acessoTotal) {
        if (acessoTotal) {
            Pagamento pagamento = pagamentoRepository.findById(id);
            if (pagamento == null) {
                throw new NotFoundException("Pagamento nao encontrado");
            }
            return pagamento;
        }

        return pagamentoRepository.findByIdAndUsuarioLogin(id, login)
                .orElseThrow(() -> new NotFoundException("Pagamento nao encontrado"));
    }

    @Override
    @Transactional
    public Pagamento atualizarStatus(Long id, PagamentoPatchDTO dto) {
        Pagamento pagamento = buscarPorId(null, id, true);

        if (dto.version() != null && !dto.version().equals(pagamento.getVersion())) {
            throw new ValidationException("Conflito de concorrencia: o pagamento foi alterado por outra transacao.", "version");
        }

        StatusPagamento statusAnterior = pagamento.getStatusPagamento();
        pagamento.setStatusPagamento(dto.statusPagamento());
        pagamento.setDataProcessado(LocalDateTime.now());
        ajustarEstoquePorTransicao(pagamento, statusAnterior, dto.statusPagamento());
        sincronizarStatusPedido(pagamento);
        pagamentoRepository.persist(pagamento);
        return pagamento;
    }

    @Override
    @Transactional
    public void cancelar(String login, Long id, boolean acessoTotal) {
        Pagamento pagamento = buscarPorId(login, id, acessoTotal);
        if (pagamento.getStatusPagamento() == StatusPagamento.CANCELADO) {
            throw new ValidationException("Pagamento ja esta cancelado", "statusPagamento");
        }
        if (pagamento.getStatusPagamento() == StatusPagamento.APROVADO && !acessoTotal) {
            throw new ValidationException("Pagamento aprovado nao pode ser cancelado pelo cliente", "statusPagamento");
        }

        StatusPagamento statusAnterior = pagamento.getStatusPagamento();
        pagamento.setStatusPagamento(StatusPagamento.CANCELADO);
        pagamento.setDataProcessado(LocalDateTime.now());
        ajustarEstoquePorTransicao(pagamento, statusAnterior, StatusPagamento.CANCELADO);
        sincronizarStatusPedido(pagamento);
        pagamentoRepository.persist(pagamento);
    }

    private void validarPedidoPodeSerPago(Pedido pedido) {
        if (pedido.getStatus() == StatusPedido.CANCELADO) {
            throw new ValidationException("Pedido cancelado nao pode ser pago", "pedidoId");
        }
        if (pedido.getStatus() == StatusPedido.PAGO) {
            throw new ValidationException("Pedido ja esta pago", "pedidoId");
        }
        validarEstoqueDisponivel(pedido);
    }

    private void validarEstoqueDisponivel(Pedido pedido) {
        for (igor.projeto.tp1.model.ItemPedido item : pedido.getItens()) {
            if (!item.getTenisPerformance().isAtivo()) {
                throw new ValidationException("Produto inativo nao pode ser pago: " + item.getTenisPerformance().getNome(), "pedidoId");
            }
            if (item.getTenisPerformance().getEstoque() == null
                    || item.getTenisPerformance().getEstoque() < item.getQuantidade()) {
                throw new ValidationException("Estoque insuficiente para o produto " + item.getTenisPerformance().getNome(), "pedidoId");
            }
        }
    }

    private void ajustarEstoquePorTransicao(Pagamento pagamento, StatusPagamento statusAnterior, StatusPagamento statusNovo) {
        if (statusAnterior != StatusPagamento.APROVADO && statusNovo == StatusPagamento.APROVADO) {
            validarEstoqueDisponivel(pagamento.getPedido());
            debitarEstoque(pagamento.getPedido());
            return;
        }

        if (statusAnterior == StatusPagamento.APROVADO && statusNovo != StatusPagamento.APROVADO) {
            recomporEstoque(pagamento.getPedido());
        }
    }

    private void debitarEstoque(Pedido pedido) {
        for (igor.projeto.tp1.model.ItemPedido item : pedido.getItens()) {
            item.getTenisPerformance().setEstoque(
                    item.getTenisPerformance().getEstoque() - item.getQuantidade()
            );
        }
    }

    private void recomporEstoque(Pedido pedido) {
        for (igor.projeto.tp1.model.ItemPedido item : pedido.getItens()) {
            item.getTenisPerformance().setEstoque(
                    item.getTenisPerformance().getEstoque() + item.getQuantidade()
            );
        }
    }

    private void sincronizarStatusPedido(Pagamento pagamento) {
        if (pagamento.getStatusPagamento() == StatusPagamento.APROVADO) {
            pagamento.getPedido().setStatus(StatusPedido.PAGO);
            return;
        }

        if (pagamento.getStatusPagamento() == StatusPagamento.CANCELADO
                || pagamento.getStatusPagamento() == StatusPagamento.RECUSADO) {
            pagamento.getPedido().setStatus(StatusPedido.CANCELADO);
            return;
        }

        pagamento.getPedido().setStatus(StatusPedido.PENDENTE);
    }
}
