package igor.projeto.tp1.dto.listadesejo;

import jakarta.validation.constraints.NotNull;

public record ListaDesejoRequestDTO(
        @NotNull(message = "O id do produto e obrigatorio")
        Long idProduto
) {
}
