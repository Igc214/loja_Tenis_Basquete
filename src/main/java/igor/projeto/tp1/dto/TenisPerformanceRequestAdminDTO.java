package igor.projeto.tp1.dto;

public record TenisPerformanceRequestAdminDTO(
                String nome,
                Integer numeroDoPe,
                String cor,
                String descricao,
                String url,
                Double preco,
                Integer estoque,
                int edicaoLimitada,
                boolean autografado,
                Long idPosicao,
                Long idSolado,
                Long idFabricante) {
}