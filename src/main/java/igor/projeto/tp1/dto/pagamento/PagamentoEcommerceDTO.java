package igor.projeto.tp1.dto.pagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import igor.projeto.tp1.model.StatusPagamento;
import igor.projeto.tp1.model.TipoPagamento;

public record PagamentoEcommerceDTO(
        Long id,
        Long pedidoId,
        LocalDateTime dataProcessado,
        BigDecimal valor,
        TipoPagamento tipoPagamento,
        StatusPagamento statusPagamento
) {
}
