package igor.projeto.tp1.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthRequestDTO(

    @NotBlank(message = "O login é obrigatório")
    String login,

    @NotBlank(message = "A senha é obrigatória")
    String senha
) {}
