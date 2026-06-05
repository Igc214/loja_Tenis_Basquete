package igor.projeto.tp1.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record ForgotPasswordDTO(
        @NotBlank(message = "O login e obrigatorio")
        String login,

        @NotBlank(message = "A senha atual e obrigatoria")
        String senhaAtual,

        @NotBlank(message = "A nova senha e obrigatoria")
        String novaSenha
) {
}
