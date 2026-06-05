package igor.projeto.tp1.dto.pedido;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ItemPedidoRequestDTO(
        @NotNull(message = "O id do produto e obrigatorio")
        Long produtoId,

        @NotNull(message = "A quantidade e obrigatoria")
        @Min(value = 1, message = "A quantidade deve ser maior que zero")
        Integer quantidade
) {
}
