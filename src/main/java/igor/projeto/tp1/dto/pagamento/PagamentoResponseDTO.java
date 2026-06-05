package igor.projeto.tp1.dto.pagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import igor.projeto.tp1.dto.pedido.PedidoResumoDTO;
import igor.projeto.tp1.model.StatusPagamento;
import igor.projeto.tp1.model.TipoPagamento;

public record PagamentoResponseDTO(
        Long id,
        PedidoResumoDTO pedido,
        LocalDateTime dataCadastro,
        LocalDateTime dataProcessado,
        BigDecimal valor,
        TipoPagamento tipoPagamento,
        StatusPagamento statusPagamento,
        Integer version
) {
}
