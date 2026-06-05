package igor.projeto.tp1.dto.admin;

import java.math.BigDecimal;

import igor.projeto.tp1.model.Fabricante;
import igor.projeto.tp1.model.Posicao;
import igor.projeto.tp1.model.TipoSolado;

public record TenisPerformanceResponseAdminDTO(
        Long id,
        String nome,
        Integer numeroDoPe,
        String cor,
        Posicao posicao,
        TipoSolado tipoSolado,
        String descricao,
        String url,
        BigDecimal preco,
        Integer estoque,
        boolean ativo,
        int edicaoLimitada,
        boolean autografado,
        Fabricante fabricante) {
}                                                                                                                                                                                                                                                                               
