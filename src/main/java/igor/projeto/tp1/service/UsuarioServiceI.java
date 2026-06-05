package igor.projeto.tp1.service;

import igor.projeto.tp1.dto.usuario.CadastroCompletoDTO;
import igor.projeto.tp1.dto.usuario.CadastroSimplesDTO;
import igor.projeto.tp1.dto.usuario.EditarDadosDTO;
import igor.projeto.tp1.dto.usuario.UsuarioRequestDTO;
import igor.projeto.tp1.model.Usuario;
import java.util.List;

public interface UsuarioServiceI {
    List<Usuario> findAll();
    Usuario findById(Long id);
    Usuario findByLogin(String login);
    Usuario create(Usuario usuario);
    Usuario create(CadastroSimplesDTO dto);
    Usuario completarCadastro(String login, CadastroCompletoDTO dto);
    void validarCadastroCompleto(Usuario usuario);
    void validarEnderecoEntrega(Usuario usuario);
    void update(String login, EditarDadosDTO dto);
    void setEndereco(String login, String endereco);
    void alterarSenha(String login, String senhaAtual, String novaSenha);
    void setPassword(String login, String token, String novaSenha);
    void update(Long id, UsuarioRequestDTO dto);
    void delete(Long id);
}
