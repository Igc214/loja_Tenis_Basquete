package igor.projeto.tp1.mapper;


import igor.projeto.tp1.dto.admin.TenisPerformanceRequestAdminDTO;
import igor.projeto.tp1.dto.admin.TenisPerformanceResponseAdminDTO;
import igor.projeto.tp1.model.Fabricante;
import igor.projeto.tp1.model.Posicao;
import igor.projeto.tp1.model.TenisPerformance;
import igor.projeto.tp1.model.TipoSolado;
import igor.projeto.tp1.repository.FornecedorRepository;
import jakarta.inject.Inject;
import igor.projeto.tp1.repository.FabricanteRepository;



public class TenisPerformanceMapper {

    @Inject
    FornecedorRepository fornecedorRepository;

    @Inject
    FabricanteRepository fabricanteRepository;  

    public static TenisPerformance toEntity(TenisPerformanceRequestAdminDTO dto) {
        if (dto == null) {
            return null;
        }

        TenisPerformance tenis = new TenisPerformance();
        tenis.setNome(dto.nome());
        tenis.setNumeroDoPe(dto.numeroDoPe());
        tenis.setCor(dto.cor());
        tenis.setDescricao(dto.descricao());
        tenis.setUrl(dto.url());
        tenis.setPreco(dto.preco());
        tenis.setEstoque(dto.estoque());
        tenis.setAtivo(dto.ativo() == null || dto.ativo());
        tenis.setEdicaoLimitada(dto.edicaoLimitada());
        tenis.setAutografado(dto.autografado());
        tenis.setPosicao(Posicao.valueOf(dto.idPosicao()));
        tenis.setTipoSolado(TipoSolado.valuesOf(dto.idSolado()));

        if (dto.idFabricante() != null) {
            Fabricante fabricante = new Fabricante();
            fabricante.setId(dto.idFabricante());
            tenis.setFabricante(fabricante);
        }

        return tenis;
    }

    public static TenisPerformanceResponseAdminDTO toResponseDTO(TenisPerformance tenisPerformance) {
        if (tenisPerformance == null) {
            return null;
        }

        

        return new TenisPerformanceResponseAdminDTO(
                tenisPerformance.getId(),
                tenisPerformance.getNome(),
                tenisPerformance.getNumeroDoPe(),
                tenisPerformance.getCor(),
                tenisPerformance.getPosicao(),
                tenisPerformance.getTipoSolado(),
                tenisPerformance.getDescricao(),
                tenisPerformance.getUrl(),
                tenisPerformance.getPreco(),
                tenisPerformance.getEstoque(),
                tenisPerformance.isAtivo(),
                tenisPerformance.getEdicaoLimitada(),
                tenisPerformance.isAutografado(),
                tenisPerformance.getFabricante()
        );
    }
}
