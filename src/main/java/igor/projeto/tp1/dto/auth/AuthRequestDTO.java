package igor.projeto.tp1.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record AuthRequestDTO(
        @NotBlank(message = "O login e obrigatorio")
        String login,

        @NotBlank(message = "A senha e obrigatoria")
        String senha
) {
}
