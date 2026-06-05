package igor.projeto.tp1.service.auth;

import org.eclipse.microprofile.jwt.JsonWebToken;

import igor.projeto.tp1.dto.auth.AuthRequestDTO;
import igor.projeto.tp1.dto.auth.AuthResponseDTO;
import igor.projeto.tp1.dto.auth.ForgotPasswordDTO;
import igor.projeto.tp1.dto.usuario.UsuarioResponseDTO;

public interface AuthServiceInter {

    AuthResponseDTO login(AuthRequestDTO dto);

    UsuarioResponseDTO info(JsonWebToken jwt);

    String alterarSenha(ForgotPasswordDTO dto);
}
