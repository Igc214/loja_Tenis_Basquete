package igor.projeto.tp1.dto.loja;

import java.math.BigDecimal;

public record TenisDetalhesLimitadosDTO(
    Long id,
    String nome,
    BigDecimal preco,
    String url,
    String descricao,
    int edicaoLimitada,
    boolean autografado
) {}
