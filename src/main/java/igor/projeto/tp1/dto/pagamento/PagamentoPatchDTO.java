package igor.projeto.tp1.dto.pagamento;

import igor.projeto.tp1.model.StatusPagamento;
import jakarta.validation.constraints.NotNull;

public record PagamentoPatchDTO(
        @NotNull(message = "O status do pagamento e obrigatorio")
        StatusPagamento statusPagamento,

        Integer version
) {
}
