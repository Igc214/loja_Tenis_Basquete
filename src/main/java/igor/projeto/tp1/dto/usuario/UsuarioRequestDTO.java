package igor.projeto.tp1.dto.usuario;

import igor.projeto.tp1.model.Perfil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioRequestDTO(
    @NotBlank(message = "O login nao pode ser vazio")
    String login,
    String senha,
    @NotNull(message = "O perfil e obrigatorio")
    Perfil perfil,
    String endereco,
    Integer version
) {}
