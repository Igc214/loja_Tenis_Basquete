package igor.projeto.tp1.mapper;

import igor.projeto.tp1.dto.pedido.ItemPedidoResponseDTO;
import igor.projeto.tp1.model.ItemPedido;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ItemPedidoMapper {

    public ItemPedidoResponseDTO toResponseDTO(ItemPedido item) {
        if (item == null) {
            return null;
        }

        return new ItemPedidoResponseDTO(
                item.getId(),
                TenisEcommerceMapper.toBuscaDTO(item.getTenisPerformance()),
                item.getQuantidade(),
                item.getValorUnitario(),
                item.getValorTotal()
        );
    }
}
