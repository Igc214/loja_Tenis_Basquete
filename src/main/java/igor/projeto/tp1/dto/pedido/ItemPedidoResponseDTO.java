package igor.projeto.tp1.dto.pedido;

import java.math.BigDecimal;

import igor.projeto.tp1.dto.loja.TenisBuscaLojaDTO;

public record ItemPedidoResponseDTO(
        Long id,
        TenisBuscaLojaDTO produto,
        Integer quantidade,
        BigDecimal valorUnitario,
        BigDecimal valorTotal
) {
}
