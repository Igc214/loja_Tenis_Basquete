package igor.projeto.tp1.service;

import java.util.List;

import igor.projeto.tp1.dto.listadesejo.ListaDesejoRequestDTO;
import igor.projeto.tp1.model.ListaDesejo;

public interface ListaDesejoService {

    ListaDesejo adicionar(String login, ListaDesejoRequestDTO dto);

    List<ListaDesejo> listar(String login);

    ListaDesejo buscarPorId(String login, Long id);

    void remover(String login, Long id);

    void removerPorProduto(String login, Long produtoId);
}
