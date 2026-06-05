package igor.projeto.tp1.dto.pedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import igor.projeto.tp1.dto.usuario.UsuarioResponseDTO;
import igor.projeto.tp1.model.StatusPagamento;
import igor.projeto.tp1.model.StatusPedido;

public record PedidoResponseDTO(
        Long id,
        UsuarioResponseDTO usuario,
        String enderecoEntrega,
        String jogadorFavorito,
        String timeNba,
        StatusPedido status,
        BigDecimal valorTotal,
        StatusPagamento statusPagamento,
        List<ItemPedidoResponseDTO> itens,
        LocalDateTime dataCadastro,
        Integer version
) {
}
