package igor.projeto.tp1.service;

import java.util.List;

import igor.projeto.tp1.dto.endereco.EnderecoRequestDTO;
import igor.projeto.tp1.exception.ValidationException;
import igor.projeto.tp1.mapper.EnderecoMapper;
import igor.projeto.tp1.model.Endereco;
import igor.projeto.tp1.model.Usuario;
import igor.projeto.tp1.repository.EnderecoRepository;
import igor.projeto.tp1.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class EnderecoService implements EnderecoServiceInter {

    @Inject
    EnderecoRepository repository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    EnderecoMapper mapper;

    @Override
    @Transactional
    public Endereco cadastrar(String login, EnderecoRequestDTO dto) {
        Usuario usuario = buscarUsuario(login);
        Endereco endereco = mapper.toEntity(dto);
        endereco.setUsuario(usuario);

        repository.persist(endereco);
        return endereco;
    }

    @Override
    public List<Endereco> listar(String login) {
        buscarUsuario(login);
        return repository.findByUsuarioLogin(login);
    }

    @Override
    public List<Endereco> listarPorUsuarioId(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId);
        if (usuario == null) {
            throw new NotFoundException("Usuario nao encontrado");
        }
        return repository.findByUsuarioId(usuarioId);
    }

    @Override
    public Endereco buscarPorId(String login, Long id) {
        return repository.findByIdAndUsuarioLogin(id, login)
                .orElseThrow(() -> new NotFoundException("Endereco nao encontrado"));
    }

    @Override
    @Transactional
    public Endereco atualizar(String login, Long id, EnderecoRequestDTO dto) {
        Endereco endereco = buscarPorId(login, id);

        if (dto.version() != null && !dto.version().equals(endereco.getVersion())) {
            throw new ValidationException("Conflito de concorrencia: o endereco foi alterado por outra transacao.", "version");
        }

        mapper.copyToEntity(dto, endereco);
        repository.persist(endereco);
        return endereco;
    }

    @Override
    @Transactional
    public void remover(String login, Long id) {
        Endereco endereco = buscarPorId(login, id);
        repository.delete(endereco);
    }

    private Usuario buscarUsuario(String login) {
        return usuarioRepository.findByLogin(login)
                .orElseThrow(() -> new NotFoundException("Usuario nao encontrado"));
    }
}
