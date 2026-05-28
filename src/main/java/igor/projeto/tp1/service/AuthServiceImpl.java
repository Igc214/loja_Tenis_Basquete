package igor.projeto.tp1.service;


import igor.projeto.tp1.dto.AuthRequestDTO;
import igor.projeto.tp1.dto.AuthResponseDTO;
import igor.projeto.tp1.model.Usuario;
import igor.projeto.tp1.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response.Status;

@ApplicationScoped
public class AuthServiceImpl implements AuthService {

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    HashService hashService;

    @Inject
    JwtService jwtService;

    @Override
    public AuthResponseDTO login(AuthRequestDTO dto) {
        // Busca o usuario pelo login; retorna 401 se nao encontrado
        Usuario usuario = usuarioRepository.findByLogin(dto.login())
                .orElseThrow(() -> new WebApplicationException("Login ou senha invalidos", Status.UNAUTHORIZED));

        // Verifica se a senha fornecida bate com o hash armazenado
        if (!hashService.verificarBcrypt(dto.senha(), usuario.getSenhaHash())) {
            throw new WebApplicationException("Login ou senha invalidos", Status.UNAUTHORIZED);
        }

        String token = jwtService.gerarToken(usuario);
        return new AuthResponseDTO(token, "Bearer");
    }
}
