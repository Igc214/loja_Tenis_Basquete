package igor.projeto.tp1.service;

import java.util.List;

import igor.projeto.tp1.dto.pedido.PedidoRequestDTO;
import igor.projeto.tp1.model.Pedido;
import igor.projeto.tp1.model.StatusPedido;

public interface PedidoServiceInter {

    Pedido criar(String login, PedidoRequestDTO dto);

    List<Pedido> listar(String login, boolean listarTodos);

    Pedido buscarPorId(String login, Long id, boolean acessoTotal);

    Pedido atualizarStatus(Long id, StatusPedido status);

    void cancelar(String login, Long id, boolean acessoTotal);
}
