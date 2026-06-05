package igor.projeto.tp1.dto.pedido;

import java.math.BigDecimal;

import igor.projeto.tp1.model.StatusPedido;

public record PedidoResumoDTO(
        Long id,
        StatusPedido status,
        BigDecimal valorTotal
) {
}
