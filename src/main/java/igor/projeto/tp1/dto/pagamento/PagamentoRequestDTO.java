package igor.projeto.tp1.dto.pagamento;

import java.math.BigDecimal;

import igor.projeto.tp1.model.TipoPagamento;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PagamentoRequestDTO(
        @NotNull(message = "O ID do pedido e obrigatorio")
        Long pedidoId,

        @NotNull(message = "O valor e obrigatorio")
        @Positive(message = "O valor deve ser positivo")
        BigDecimal valor,

        @NotNull(message = "O tipo de pagamento e obrigatorio")
        TipoPagamento tipoPagamento,

        Integer version
) {
}
