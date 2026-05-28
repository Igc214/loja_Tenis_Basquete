
package igor.projeto.tp1.dto;

import igor.projeto.tp1.model.Perfil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioRequestDTO(

        @NotBlank(message = "O login é obrigatório")
        @Size(min = 3, max = 50, message = "O login deve conter entre 3 e 50 caracteres")
        String login,
        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 6, message = "A senha deve conter no mínimo 6 caracteres") 
        String senha,
        @NotBlank(message = "O perfil é obrigatório")
        Perfil perfil
    ) {
}
