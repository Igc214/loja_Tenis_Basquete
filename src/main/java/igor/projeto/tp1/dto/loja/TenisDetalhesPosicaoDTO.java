package igor.projeto.tp1.dto.loja;

import java.math.BigDecimal;

import igor.projeto.tp1.model.Posicao;

public record TenisDetalhesPosicaoDTO(
    Long id,
    String nome,
    BigDecimal preco,
    String url,
    String descricao,
    Posicao posicao
) {
    
}
