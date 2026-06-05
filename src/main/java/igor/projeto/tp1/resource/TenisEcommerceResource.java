package igor.projeto.tp1.resource;

import java.util.List;

import igor.projeto.tp1.dto.loja.TenisBuscaLojaDTO;
import igor.projeto.tp1.dto.loja.TenisDetalhePorNumeroPeDTO;
import igor.projeto.tp1.dto.loja.TenisDetalheTipoSoladoDTO;
import igor.projeto.tp1.dto.loja.TenisDetalhesLojaDTO;
import igor.projeto.tp1.dto.loja.TenisDetalhesPosicaoDTO;
import igor.projeto.tp1.dto.loja.TenisDetalhesLimitadosDTO;
import igor.projeto.tp1.service.TenisEcommerceService;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@PermitAll
public class TenisEcommerceResource {
    
    @Inject
    TenisEcommerceService service;

    @GET
    public List<TenisBuscaLojaDTO> listar() {
        return service.listarTenis();
    }

    @GET
    @Path("/autografados")
    public List<TenisDetalhesLimitadosDTO> listarAutografados() {
        return service.listarAutografados();
    }

    @GET
    @Path("/posicoes/{idPosicao}")
    public List<TenisDetalhesPosicaoDTO> listarPorPosicao(@PathParam("idPosicao") Long idPosicao) {
        return service.listarPorPosicao(idPosicao);
    }

    @GET
    @Path("/solados/{idSolado}")
    public List<TenisDetalheTipoSoladoDTO> listarPorTipoSolado(@PathParam("idSolado") Long idSolado) {
        return service.listarPorTipoSolado(idSolado);
    }

    @GET
    @Path("/numeros-pe/{numeroPe}")
    public TenisDetalhePorNumeroPeDTO buscarDetalhePorNumeroPe(@PathParam("numeroPe") Integer numeroPe) {
        return service.buscarDetalhePorNumeroPe(numeroPe);
    }

    @GET
    @Path("/{id}")
    public TenisDetalhesLojaDTO buscarPorId(@PathParam("id") Long id) {
        return service.buscarPorId(id);
    }
}
