package igor.projeto.tp1.service;

import java.util.List;

import igor.projeto.tp1.dto.endereco.EnderecoRequestDTO;
import igor.projeto.tp1.model.Endereco;

public interface EnderecoServiceInter {

    Endereco cadastrar(String login, EnderecoRequestDTO dto);

    List<Endereco> listar(String login);

    List<Endereco> listarPorUsuarioId(Long usuarioId);

    Endereco buscarPorId(String login, Long id);

    Endereco atualizar(String login, Long id, EnderecoRequestDTO dto);

    void remover(String login, Long id);
}
