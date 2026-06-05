package igor.projeto.tp1.dto.usuario;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioCadastroRequestCompletoDTO(
        @NotBlank(message = "O nome é obrigatório")
        String nome,
        @NotBlank(message = "O email é obrigatório")
        @Email(message = "Email inválido")
        String email,
        @NotBlank(message = "A senha é obrigatória")
        String senha,
        String cpf,
        String telefone,
        LocalDate dataNascimento,
        String timeNba,
        String jogadorFavorito) {
}
