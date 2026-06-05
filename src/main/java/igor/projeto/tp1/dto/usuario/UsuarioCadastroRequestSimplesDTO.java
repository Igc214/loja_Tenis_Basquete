package igor.projeto.tp1.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioCadastroRequestSimplesDTO(
        @NotBlank(message = "O nome é obrigatório")
        String nome,
        @NotBlank(message = "O email é obrigatório")
        @Email(message = "Email inválido")
        String email,
        @NotBlank(message = "A senha é obrigatória")
        String senha) {
}
