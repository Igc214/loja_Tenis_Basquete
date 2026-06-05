package igor.projeto.tp1.dto.admin;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record TenisPerformanceRequestAdminDTO(
                @NotBlank(message = "O nome e obrigatorio")
                String nome,
                @NotNull(message = "O numero do pe e obrigatorio")
                @Positive(message = "O numero do pe deve ser positivo")
                Integer numeroDoPe,
                @NotBlank(message = "A cor e obrigatoria")
                String cor,
                @NotBlank(message = "A descricao e obrigatoria")
                String descricao,
                String url,
                @NotNull(message = "O preco e obrigatorio")
                @Positive(message = "O preco deve ser positivo")
                BigDecimal preco,
                @NotNull(message = "O estoque e obrigatorio")
                @PositiveOrZero(message = "O estoque nao pode ser negativo")
                Integer estoque,
                Boolean ativo,
                @PositiveOrZero(message = "Edicao limitada nao pode ser negativa")
                int edicaoLimitada,
                boolean autografado,
                @NotNull(message = "A posicao e obrigatoria")
                Long idPosicao,
                @NotNull(message = "O tipo de solado e obrigatorio")
                Long idSolado,
                @NotNull(message = "O fabricante e obrigatorio")
                Long idFabricante) {
}
