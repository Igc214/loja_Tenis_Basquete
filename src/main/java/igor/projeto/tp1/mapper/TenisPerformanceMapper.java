package igor.projeto.tp1.mapper;


import igor.projeto.tp1.dto.TenisPerformanceRequestAdminDTO;
import igor.projeto.tp1.dto.TenisPerformanceResponseAdminDTO;
import igor.projeto.tp1.model.Fabricante;
import igor.projeto.tp1.model.Posicao;
import igor.projeto.tp1.model.TenisPerformance;
import igor.projeto.tp1.model.TipoSolado;
import java.util.List;
import java.util.stream.Collectors;

public class TenisPerformanceMapper {

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
                tenisPerformance.getEdicaoLimitada(),
                tenisPerformance.isAutografado(),
                tenisPerformance.getFabricante()
        );
    }
}