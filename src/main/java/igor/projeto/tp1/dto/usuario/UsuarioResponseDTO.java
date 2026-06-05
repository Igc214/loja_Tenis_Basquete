package igor.projeto.tp1.dto.usuario;

import igor.projeto.tp1.model.Perfil;

public record UsuarioResponseDTO(
    Long id,
    String login,
    String nome,
    String sobrenome,
    String jogadorFavorito,
    String timeNba,
    Perfil perfil,
    String endereco
) {}
