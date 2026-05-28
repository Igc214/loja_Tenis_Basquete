package igor.projeto.tp1.resource;

import java.util.List;

import igor.projeto.tp1.dto.FornecedorRequestDTO;
import igor.projeto.tp1.dto.FornecedorResponseDTO;
import igor.projeto.tp1.mapper.FornecedorMapper;
import igor.projeto.tp1.model.Fornecedor;
import igor.projeto.tp1.service.FornecedorService;
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

@Path("/fornecedor")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FornecedorResource {
    
    @Inject
    FornecedorService service;

    @GET
    public List<FornecedorResponseDTO> buscarTodos() {
        return service.findAll()
                .stream()
                .map(e -> FornecedorMapper.toResponseDTO(e))
                .toList();
    }

    @GET
    @Path("/{id}")
    public FornecedorResponseDTO buscarPeloId(@PathParam("id") Long id) {
        return FornecedorMapper.toResponseDTO(service.findById(id));
    }

    @DELETE
    @Path("/{id}")
    public void deletar(@PathParam("id") Long id) {
        service.delete(id);
    }

    @POST
    public FornecedorResponseDTO incluir(FornecedorRequestDTO dto) {
        Fornecedor fornecedor = service.create(FornecedorMapper.toEntity(dto));
        return FornecedorMapper.toResponseDTO(fornecedor);
    }

    @PUT
    @Path("/{id}")
    public void alterar(@PathParam("id") Long id, FornecedorRequestDTO dto) {
        service.update(id, dto);
    }
}