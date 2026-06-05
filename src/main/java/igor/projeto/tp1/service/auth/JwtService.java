package igor.projeto.tp1.service.auth;

import java.util.Set;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import igor.projeto.tp1.model.Usuario;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JwtService {

    private static final long EXPIRACAO_SEGUNDOS = 3600L;

    @ConfigProperty(name = "mp.jwt.verify.issuer", defaultValue = "sga-api")
    String issuer;

    public String gerarToken(Usuario usuario) {
        return Jwt.issuer(issuer)
                .upn(usuario.getLogin())
                .groups(Set.of(usuario.getPerfil().name()))
                .expiresIn(EXPIRACAO_SEGUNDOS)
                .sign();
    }
}
