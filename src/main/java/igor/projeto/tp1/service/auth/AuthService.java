package igor.projeto.tp1.service.auth;

import org.eclipse.microprofile.jwt.JsonWebToken;

import igor.projeto.tp1.dto.auth.AuthRequestDTO;
import igor.projeto.tp1.dto.auth.AuthResponseDTO;
import igor.projeto.tp1.dto.auth.ForgotPasswordDTO;
import igor.projeto.tp1.dto.usuario.UsuarioResponseDTO;
import igor.projeto.tp1.exception.AuthorizationException;
import igor.projeto.tp1.exception.ValidationException;
import igor.projeto.tp1.mapper.UsuarioMapper;
import igor.projeto.tp1.model.Usuario;
import igor.projeto.tp1.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response.Status;

@ApplicationScoped
public class AuthService implements AuthServiceInter {

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    UsuarioMapper usuarioMapper;

    @Inject
    HashService hashService;

    @Inject
    JwtService jwtService;

    @Override
    public AuthResponseDTO login(AuthRequestDTO dto) {
        Usuario usuario = usuarioRepository.findByLogin(dto.login())
                .orElseThrow(() -> credenciaisInvalidas());

        if (!hashService.verificarSenha(dto.senha(), usuario.getSenhaHash())) {
            throw credenciaisInvalidas();
        }

        return new AuthResponseDTO(jwtService.gerarToken(usuario), "Bearer");
    }

    @Override
    public UsuarioResponseDTO info(JsonWebToken jwt) {
        String login = jwt.getClaim("upn");
        Usuario usuario = usuarioRepository.findByLogin(login)
                .orElseThrow(() -> new ValidationException("Usuario nao encontrado", "login"));

        return usuarioMapper.toResponseDTO(usuario);
    }

    @Override
    @Transactional
    public String alterarSenha(ForgotPasswordDTO dto) {
        Usuario usuario = usuarioRepository.findByLogin(dto.login())
                .orElseThrow(() -> new ValidationException("Login ou senha atual incorreto(s)", "login"));

        if (!hashService.verificarSenha(dto.senhaAtual(), usuario.getSenhaHash())) {
            throw new ValidationException("Login ou senha atual incorreto(s)", "senhaAtual");
        }

        usuario.setSenhaHash(hashService.bcrypt(dto.novaSenha()));
        usuarioRepository.persist(usuario);
        return "Senha alterada com sucesso";
    }

    private AuthorizationException credenciaisInvalidas() {
        return new AuthorizationException("Login ou senha invalidos", "auth/login", Status.UNAUTHORIZED);
    }
}
