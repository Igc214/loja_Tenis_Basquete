package igor.projeto.tp1.service;

import igor.projeto.tp1.dto.AuthRequestDTO;
import igor.projeto.tp1.dto.AuthResponseDTO;

public interface AuthService {

    /**
     * Autentica o usuario e retorna um token JWT em caso de sucesso.
     */
    AuthResponseDTO login(AuthRequestDTO dto);
}
