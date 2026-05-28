package igor.projeto.tp1.dto;

import igor.projeto.tp1.model.Perfil;

public record UsuarioResponseDTO(
    Long id,
    String login,
    Perfil perfil
) {}
