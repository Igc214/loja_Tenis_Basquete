package igor.projeto.tp1.dto.usuario;

import igor.projeto.tp1.model.Perfil;

public record UsuarioResponseDTO(
    Long id,
    String login,
    String nome,
    Perfil perfil,
    String endereco
) {}
