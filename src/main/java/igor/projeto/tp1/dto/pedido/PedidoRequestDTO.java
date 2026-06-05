package igor.projeto.tp1.dto.pedido;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

public record PedidoRequestDTO(
        @NotEmpty(message = "O pedido deve possuir ao menos um item")
        List<@Valid ItemPedidoRequestDTO> itens,

        Integer version
) {
}
