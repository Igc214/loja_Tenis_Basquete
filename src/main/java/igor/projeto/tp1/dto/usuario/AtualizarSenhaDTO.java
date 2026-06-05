package igor.projeto.tp1.dto.usuario;

import jakarta.validation.constraints.NotBlank;

public record AtualizarSenhaDTO(
        @NotBlank(message = "A senha atual nao pode ser vazia")
        String senhaAtual,

        @NotBlank(message = "A nova senha nao pode ser vazia")
        String novaSenha
) {
}
