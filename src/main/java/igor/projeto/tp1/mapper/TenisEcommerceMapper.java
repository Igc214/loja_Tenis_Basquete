package igor.projeto.tp1.mapper;

import igor.projeto.tp1.model.TenisPerformance;
import igor.projeto.tp1.dto.loja.TenisBuscaLojaDTO;
import igor.projeto.tp1.dto.loja.TenisDetalhePorNumeroPeDTO;
import igor.projeto.tp1.dto.loja.TenisDetalheTipoSoladoDTO;
import igor.projeto.tp1.dto.loja.TenisDetalhesLojaDTO;
import igor.projeto.tp1.dto.loja.TenisDetalhesPosicaoDTO;
import igor.projeto.tp1.dto.loja.TenisDetalhesLimitadosDTO;

public class TenisEcommerceMapper {

    public static TenisBuscaLojaDTO toBuscaDTO(TenisPerformance e) {
        if (e == null) {
            return null;
        }
        return new TenisBuscaLojaDTO(
            e.getId(),
            e.getNome(),
            e.getPreco(),
            e.getUrl()
            
        );
    }

    public static TenisDetalhesLojaDTO toDetalhesDTO(TenisPerformance e) {
        if (e == null) {
            return null;
        }
        return new TenisDetalhesLojaDTO(
            e.getId(),
            e.getNome(),
            e.getPreco(),
            e.getUrl(),
            e.getDescricao()
        );
    }

    public static TenisDetalhesLimitadosDTO toDetalhesLimitadosDTO(TenisPerformance e) {
        if (e == null) {
            return null;
        }
        return new TenisDetalhesLimitadosDTO(
            e.getId(),
            e.getNome(),
            e.getPreco(),
            e.getUrl(),
            e.getDescricao(),
            e.getEdicaoLimitada(),
            e.isAutografado()
        );
    }

    public static TenisDetalhesPosicaoDTO toDetalhesPosicaoDTO(TenisPerformance e) {
        if (e == null) {
            return null;
        }
        return new TenisDetalhesPosicaoDTO(
            e.getId(),
            e.getNome(),
            e.getPreco(),
            e.getUrl(),
            e.getDescricao(),
            e.getPosicao()
        );
    }

    public static TenisDetalheTipoSoladoDTO toDetalhesTipoSoladoDTO(TenisPerformance e) {
        if (e == null) {
            return null;
        }
        return new TenisDetalheTipoSoladoDTO(
            e.getId(),
            e.getNome(),
            e.getPreco(),
            e.getUrl(),
            e.getDescricao(),
            e.getTipoSolado()
        );
    }

    public static TenisDetalhePorNumeroPeDTO toDetalhePorNumeroPeDTO(TenisPerformance e) {
        if (e == null) {
            return null;
        }
        return new TenisDetalhePorNumeroPeDTO(
            e.getId(),
            e.getNome(),
            e.getPreco(),
            e.getUrl(),
            e.getDescricao(),
            e.getNumeroDoPe()
        );
    }
}

