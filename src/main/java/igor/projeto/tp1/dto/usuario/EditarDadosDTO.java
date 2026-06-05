package igor.projeto.tp1.dto.usuario;

import jakarta.validation.constraints.NotBlank;

public record EditarDadosDTO(
    @NotBlank(message = "O nome não pode ser vazio") 
    String nome,
    @NotBlank(message = "O sobrenome não pode ser vazio") 
    String sobrenome,
    String endereco
) {}
