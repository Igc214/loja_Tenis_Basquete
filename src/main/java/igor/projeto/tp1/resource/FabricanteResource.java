package igor.projeto.tp1.resource;

import java.util.List;

import igor.projeto.tp1.dto.FabricanteRequestDTO;
import igor.projeto.tp1.dto.FabricanteResponseDTO;
import igor.projeto.tp1.mapper.FabricanteMapper;
import igor.projeto.tp1.model.Fabricante;
import igor.projeto.tp1.service.FabricanteService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/fabricante")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FabricanteResource {
    
    @Inject
    FabricanteService service;

    @GET
    public List<FabricanteResponseDTO> buscarTodos() {
        return service.findAll()
                .stream()
                .map(e -> FabricanteMapper.toResponseDTO(e))
                .toList();
    }

    @GET
    @Path("/{id}")
    public FabricanteResponseDTO buscarPeloId(@PathParam("id") Long id) {
        return FabricanteMapper.toResponseDTO(service.findById(id));
    }

    @DELETE
    @Path("/{id}")
    public void deletar(@PathParam("id") Long id) {
        service.delete(id);
    }

    @POST
    public FabricanteResponseDTO incluir(FabricanteRequestDTO dto) {
        Fabricante fabricante = service.create(FabricanteMapper.toEntity(dto));
        return FabricanteMapper.toResponseDTO(fabricante);
    }

    @PUT
    @Path("/{id}")
    public void alterar(@PathParam("id") Long id, FabricanteRequestDTO dto) {
        service.update(id, dto);
    }
}