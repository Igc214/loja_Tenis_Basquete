package igor.projeto.tp1.mapper;

import igor.projeto.tp1.dto.pagamento.PagamentoEcommerceDTO;
import igor.projeto.tp1.dto.pagamento.PagamentoResponseDTO;
import igor.projeto.tp1.model.Pagamento;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PagamentoMapper {

    @Inject
    PedidoMapper pedidoMapper;

    public PagamentoResponseDTO toResponseDTO(Pagamento pagamento) {
        if (pagamento == null) {
            return null;
        }

        return new PagamentoResponseDTO(
                pagamento.getId(),
                pedidoMapper.toResumoDTO(pagamento.getPedido()),
                pagamento.getDataCadastro(),
                pagamento.getDataProcessado(),
                pagamento.getValor(),
                pagamento.getTipoPagamento(),
                pagamento.getStatusPagamento(),
                pagamento.getVersion()
        );
    }

    public PagamentoEcommerceDTO toEcommerceDTO(Pagamento pagamento) {
        if (pagamento == null) {
            return null;
        }

        return new PagamentoEcommerceDTO(
                pagamento.getId(),
                pagamento.getPedido() != null ? pagamento.getPedido().getId() : null,
                pagamento.getDataProcessado(),
                pagamento.getValor(),
                pagamento.getTipoPagamento(),
                pagamento.getStatusPagamento()
        );
    }
}
