package igor.projeto.tp1.service;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

import igor.projeto.tp1.model.Posicao;
import igor.projeto.tp1.model.TenisPerformance;
import igor.projeto.tp1.model.TipoSolado;
import igor.projeto.tp1.repository.TenisPerformanceRepository;
import igor.projeto.tp1.mapper.TenisEcommerceMapper;
import igor.projeto.tp1.dto.loja.TenisBuscaLojaDTO;
import igor.projeto.tp1.dto.loja.TenisDetalhePorNumeroPeDTO;
import igor.projeto.tp1.dto.loja.TenisDetalheTipoSoladoDTO;
import igor.projeto.tp1.dto.loja.TenisDetalhesLojaDTO;
import igor.projeto.tp1.dto.loja.TenisDetalhesPosicaoDTO;
import igor.projeto.tp1.dto.loja.TenisDetalhesLimitadosDTO;

@ApplicationScoped
public class TenisEcommerceService {

    @Inject
    TenisPerformanceRepository repository;

    public List<TenisBuscaLojaDTO> listarTenis() {
        return repository.listAll()
            .stream()
            .filter(this::isDisponivelNaLoja)
            .map(TenisEcommerceMapper::toBuscaDTO)
            .collect(Collectors.toList());
    }

    public List<TenisDetalhesLimitadosDTO> listarAutografados() {
        return repository.listAll()
            .stream()
            .filter(this::isDisponivelNaLoja)
            .filter(TenisPerformance::isAutografado)
            .map(TenisEcommerceMapper::toDetalhesLimitadosDTO)
            .collect(Collectors.toList());
    }

    public List<TenisDetalhesPosicaoDTO> listarPorPosicao(Long idPosicao) {
        Posicao posicao = Posicao.valueOf(idPosicao);
        if (posicao == null) {
            throw new NotFoundException("Posicao nao encontrada");
        }

        return repository.listAll()
            .stream()
            .filter(this::isDisponivelNaLoja)
            .filter(tenis -> posicao.equals(tenis.getPosicao()))
            .map(TenisEcommerceMapper::toDetalhesPosicaoDTO)
            .collect(Collectors.toList());
    }

    public List<TenisDetalheTipoSoladoDTO> listarPorTipoSolado(Long idSolado) {
        TipoSolado tipoSolado = TipoSolado.valuesOf(idSolado);
        if (tipoSolado == null) {
            throw new NotFoundException("Tipo de solado nao encontrado");
        }

        return repository.listAll()
            .stream()
            .filter(this::isDisponivelNaLoja)
            .filter(tenis -> tipoSolado.equals(tenis.getTipoSolado()))
            .map(TenisEcommerceMapper::toDetalhesTipoSoladoDTO)
            .collect(Collectors.toList());
    }

    public TenisDetalhePorNumeroPeDTO buscarDetalhePorNumeroPe(Integer numeroPe) {
        return repository.listAll()
            .stream()
            .filter(this::isDisponivelNaLoja)
            .filter(tenis -> tenis.getNumeroDoPe() == numeroPe)
            .findFirst()
            .map(TenisEcommerceMapper::toDetalhePorNumeroPeDTO)
            .orElseThrow(() -> new NotFoundException("Produto com numero de pe " + numeroPe + " nao encontrado"));
    }

    public TenisDetalhesLojaDTO buscarPorId(Long id) {
        TenisPerformance tenis = repository.findById(id);
        
        if (tenis == null || !tenis.isAtivo() || tenis.getEstoque() == null || tenis.getEstoque() <= 0) {
            throw new NotFoundException("Produto não encontrado");
        }
        
        return TenisEcommerceMapper.toDetalhesDTO(tenis);
    }

    private boolean isDisponivelNaLoja(TenisPerformance tenis) {
        return tenis.isAtivo() && tenis.getEstoque() != null && tenis.getEstoque() > 0;
    }
}
