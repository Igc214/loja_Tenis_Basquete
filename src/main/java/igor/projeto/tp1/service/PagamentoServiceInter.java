package igor.projeto.tp1.service;

import java.util.List;

import igor.projeto.tp1.dto.pagamento.PagamentoPatchDTO;
import igor.projeto.tp1.dto.pagamento.PagamentoRequestDTO;
import igor.projeto.tp1.model.Pagamento;

public interface PagamentoServiceInter {

    Pagamento criar(String login, PagamentoRequestDTO dto);

    List<Pagamento> listar(String login, boolean listarTodos);

    Pagamento buscarPorId(String login, Long id, boolean acessoTotal);

    Pagamento atualizarStatus(Long id, PagamentoPatchDTO dto);

    void cancelar(String login, Long id, boolean acessoTotal);
}
