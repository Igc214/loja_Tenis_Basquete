package igor.projeto.tp1.dto.loja;

import java.math.BigDecimal;

import igor.projeto.tp1.model.TipoSolado;

public record TenisDetalheTipoSoladoDTO(
    Long id,
    String nome,
    BigDecimal preco,
    String url,
    String descricao,
    TipoSolado tipoSolado
) {
    
}
