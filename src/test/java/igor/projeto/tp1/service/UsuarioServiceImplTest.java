package igor.projeto.tp1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import igor.projeto.tp1.dto.usuario.CadastroCompletoDTO;
import igor.projeto.tp1.exception.ValidationException;
import igor.projeto.tp1.model.Perfil;
import igor.projeto.tp1.model.Usuario;
import igor.projeto.tp1.repository.UsuarioRepository;
import igor.projeto.tp1.service.auth.HashService;

class UsuarioServiceImplTest {

    private static class StubUsuarioRepository extends UsuarioRepository {
        private final Map<String, Usuario> usuarios = new HashMap<>();
        private Usuario persisted;

        @Override
        public Optional<Usuario> findByLogin(String login) {
            return Optional.ofNullable(usuarios.get(login));
        }

        @Override
        public void persist(Usuario usuario) {
            persisted = usuario;
            usuarios.put(usuario.getLogin(), usuario);
        }
    }

    @Test
    void completarCadastroShouldFormatEnderecoAndKeepClientePerfil() {
        UsuarioServiceImpl service = new UsuarioServiceImpl();
        StubUsuarioRepository repository = new StubUsuarioRepository();
        service.repository = repository;
        service.hashService = new HashService();

        Usuario usuario = new Usuario();
        usuario.setLogin("cliente");
        usuario.setNome("Antigo");
        usuario.setSenhaHash("hash");
        usuario.setPerfil(Perfil.ADMIN);
        repository.persist(usuario);

        CadastroCompletoDTO dto = new CadastroCompletoDTO(
                "Igor",
                "Carvalho",
                "Stephen Curry",
                "Golden State Warriors",
                "Rua das Flores",
                "123",
                "Palmas",
                "TO",
                "77000-000"
        );

        Usuario atualizado = service.completarCadastro("cliente", dto);

        assertEquals("Igor", atualizado.getNome());
        assertEquals("Carvalho", atualizado.getSobrenome());
        assertEquals("Stephen Curry", atualizado.getJogadorFavorito());
        assertEquals("Golden State Warriors", atualizado.getTimeNba());
        assertEquals("Rua das Flores, 123 - Palmas/TO - CEP 77000-000", atualizado.getEndereco());
        assertEquals(Perfil.CLIENTE, atualizado.getPerfil());
        assertEquals(atualizado, repository.persisted);
    }

    @Test
    void alterarSenhaShouldRequireCurrentPasswordAndHashNewPassword() {
        UsuarioServiceImpl service = new UsuarioServiceImpl();
        StubUsuarioRepository repository = new StubUsuarioRepository();
        HashService hashService = new HashService();
        service.repository = repository;
        service.hashService = hashService;

        Usuario usuario = new Usuario();
        usuario.setLogin("cliente");
        usuario.setNome("Cliente");
        usuario.setSenhaHash(hashService.bcrypt("senha-antiga"));
        usuario.setPerfil(Perfil.CLIENTE);
        repository.persist(usuario);

        service.alterarSenha("cliente", "senha-antiga", "senha-nova");

        assertFalse("senha-nova".equals(usuario.getSenhaHash()));
        assertTrue(hashService.verificarSenha("senha-nova", usuario.getSenhaHash()));
    }

    @Test
    void alterarSenhaShouldRejectWrongCurrentPassword() {
        UsuarioServiceImpl service = new UsuarioServiceImpl();
        StubUsuarioRepository repository = new StubUsuarioRepository();
        HashService hashService = new HashService();
        service.repository = repository;
        service.hashService = hashService;

        Usuario usuario = new Usuario();
        usuario.setLogin("cliente");
        usuario.setNome("Cliente");
        usuario.setSenhaHash(hashService.bcrypt("senha-antiga"));
        usuario.setPerfil(Perfil.CLIENTE);
        repository.persist(usuario);

        ValidationException exception = assertThrows(
                ValidationException.class,
                () -> service.alterarSenha("cliente", "errada", "senha-nova")
        );

        assertEquals("senhaAtual", exception.getField());
        assertTrue(hashService.verificarSenha("senha-antiga", usuario.getSenhaHash()));
    }
}
