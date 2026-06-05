package igor.projeto.tp1.dto.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EnderecoRequestDTO(
        @NotBlank(message = "A rua e obrigatoria")
        @Size(min = 3, max = 100, message = "A rua deve ter entre 3 e 100 caracteres")
        String rua,

        @NotBlank(message = "O numero e obrigatorio")
        @Size(min = 1, max = 10, message = "O numero deve ter entre 1 e 10 caracteres")
        String numero,

        @NotBlank(message = "A cidade e obrigatoria")
        @Size(min = 2, max = 50, message = "A cidade deve ter entre 2 e 50 caracteres")
        String cidade,

        @NotBlank(message = "O estado e obrigatorio")
        @Size(min = 2, max = 50, message = "O estado deve ter entre 2 e 50 caracteres")
        String estado,

        @NotBlank(message = "O CEP e obrigatorio")
        @Pattern(regexp = "^\\d{5}-\\d{3}$", message = "O CEP deve estar no formato XXXXX-XXX")
        String cep,

        Integer version
) {
}
