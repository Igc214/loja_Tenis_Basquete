package igor.projeto.tp1.mapper;

import igor.projeto.tp1.dto.pedido.PedidoResponseDTO;
import igor.projeto.tp1.dto.pedido.PedidoResumoDTO;
import igor.projeto.tp1.model.Pedido;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PedidoMapper {

    @Inject
    UsuarioMapper usuarioMapper;

    @Inject
    ItemPedidoMapper itemPedidoMapper;

    public PedidoResumoDTO toResumoDTO(Pedido pedido) {
        if (pedido == null) {
            return null;
        }

        return new PedidoResumoDTO(
                pedido.getId(),
                pedido.getStatus(),
                pedido.getValorTotal()
        );
    }

    public PedidoResponseDTO toResponseDTO(Pedido pedido) {
        if (pedido == null) {
            return null;
        }

        return new PedidoResponseDTO(
                pedido.getId(),
                usuarioMapper.toResponseDTO(pedido.getUsuario()),
                pedido.getStatus(),
                pedido.getValorTotal(),
                pedido.getPagamento() != null ? pedido.getPagamento().getStatusPagamento() : null,
                pedido.getItens() != null
                        ? pedido.getItens().stream().map(itemPedidoMapper::toResponseDTO).toList()
                        : null,
                pedido.getDataCadastro(),
                pedido.getVersion()
        );
    }
}
