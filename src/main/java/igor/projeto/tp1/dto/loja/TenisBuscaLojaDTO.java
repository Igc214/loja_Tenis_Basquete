package igor.projeto.tp1.dto.loja;

import java.math.BigDecimal;

public record TenisBuscaLojaDTO(
    Long id,
    String nome,
    BigDecimal preco,
    String url
) {}
