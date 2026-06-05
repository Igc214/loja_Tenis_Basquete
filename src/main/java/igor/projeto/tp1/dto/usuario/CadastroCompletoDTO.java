package igor.projeto.tp1.dto.usuario;

import jakarta.validation.constraints.NotBlank;

public record CadastroCompletoDTO(
    @NotBlank(message = "O nome não pode ser vazio") 
    String nome,
    @NotBlank(message = "O sobrenome não pode ser vazio") 
    String sobrenome,
    @NotBlank(message = "O login não pode ser vazio") 
    String login,
    @NotBlank(message = "A senha não pode ser vazia") 
    String senha,
    String endereco
) {}
