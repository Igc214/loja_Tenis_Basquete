package igor.projeto.tp1.service;

import java.util.List;

import igor.projeto.tp1.dto.usuario.CadastroCompletoDTO;
import igor.projeto.tp1.dto.usuario.CadastroSimplesDTO;
import igor.projeto.tp1.dto.usuario.EditarDadosDTO;
import igor.projeto.tp1.dto.usuario.UsuarioRequestDTO;
import igor.projeto.tp1.exception.ValidationException;
import igor.projeto.tp1.model.Perfil;
import igor.projeto.tp1.model.Usuario;
import igor.projeto.tp1.repository.UsuarioRepository;
import igor.projeto.tp1.service.auth.CacheService;
import igor.projeto.tp1.service.auth.HashService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioServiceI {

    @Inject
    UsuarioRepository repository;

    @Inject
    HashService hashService;

    @Inject
    CacheService cacheService;

    @Override
    public List<Usuario> findAll() {
        return repository.findAll().list();
    }

    @Override
    public Usuario findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Usuario findByLogin(String login) {
        return repository.findByLogin(login)
                .orElseThrow(() -> new ValidationException("Usuario nao encontrado", "login"));
    }

    @Override
    @Transactional
    public Usuario create(Usuario usuario) {
        validarLoginDisponivel(usuario.getLogin());

        usuario.setSenhaHash(hashService.bcrypt(usuario.getSenhaHash()));
        usuario.setPerfil(usuario.getPerfil() != null ? usuario.getPerfil() : Perfil.CLIENTE);
        repository.persist(usuario);
        return usuario;
    }

    @Override
    @Transactional
    public Usuario create(CadastroSimplesDTO dto) {
        validarLoginDisponivel(dto.login());

        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setLogin(dto.login());
        usuario.setSenhaHash(hashService.bcrypt(dto.senha()));
        usuario.setPerfil(Perfil.CLIENTE);

        repository.persist(usuario);
        return usuario;
    }

    @Override
    @Transactional
    public Usuario create(CadastroCompletoDTO dto) {
        validarLoginDisponivel(dto.login());

        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setSobrenome(dto.sobrenome());
        usuario.setLogin(dto.login());
        usuario.setSenhaHash(hashService.bcrypt(dto.senha()));
        usuario.setEndereco(dto.endereco());
        usuario.setPerfil(Perfil.CLIENTE);

        repository.persist(usuario);
        return usuario;
    }

    @Override
    @Transactional
    public void update(String login, EditarDadosDTO dto) {
        Usuario usuario = findByLogin(login);

        usuario.setNome(dto.nome());
        usuario.setSobrenome(dto.sobrenome());

        if (dto.endereco() != null && !dto.endereco().isBlank()) {
            usuario.setEndereco(dto.endereco());
        }

        repository.persist(usuario);
    }

    @Override
    @Transactional
    public void setEndereco(String login, String endereco) {
        Usuario usuario = findByLogin(login);

        if (endereco == null || endereco.isBlank()) {
            throw new ValidationException("Endereco nao pode ser vazio", "endereco");
        }

        usuario.setEndereco(endereco);
        repository.persist(usuario);
    }

    @Override
    @Transactional
    public void setPassword(String login, String token, String novaSenha) {
        if (novaSenha == null || novaSenha.isBlank()) {
            throw new ValidationException("Nova senha nao pode ser vazia", "senha");
        }

        if (!cacheService.checkToken(login, token)) {
            throw new ValidationException("Token invalido ou expirado", "token");
        }

        Usuario usuario = findByLogin(login);
        usuario.setSenhaHash(hashService.bcrypt(novaSenha));
        repository.persist(usuario);

        cacheService.invalidateToken(token);
    }

    @Override
    @Transactional
    public void update(Long id, UsuarioRequestDTO dto) {
        Usuario usuario = findById(id);
        if (usuario == null) {
            throw new ValidationException("Usuario nao encontrado", "id");
        }

        if (dto.version() != null && !usuario.getVersion().equals(dto.version())) {
            throw new OptimisticLockException("Versao do usuario foi alterada por outro processo");
        }

        if (!usuario.getLogin().equals(dto.login()) && repository.findByLogin(dto.login()).isPresent()) {
            throw new ValidationException("Login ja existe", "login");
        }

        usuario.setLogin(dto.login());
        usuario.setPerfil(dto.perfil());

        if (dto.senha() != null && !dto.senha().isBlank()) {
            usuario.setSenhaHash(hashService.bcrypt(dto.senha()));
        }

        if (dto.endereco() != null && !dto.endereco().isBlank()) {
            usuario.setEndereco(dto.endereco());
        }

        repository.persist(usuario);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Usuario usuario = findById(id);
        if (usuario == null) {
            throw new ValidationException("Usuario nao encontrado", "id");
        }

        repository.delete(usuario);
    }

    private void validarLoginDisponivel(String login) {
        if (repository.findByLogin(login).isPresent()) {
            throw new ValidationException("Login ja existe", "login");
        }
    }
}
