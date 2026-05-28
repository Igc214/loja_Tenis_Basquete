package igor.projeto.tp1.resource;

import java.util.List;

import igor.projeto.tp1.dto.ClienteRequestDTO;
import igor.projeto.tp1.dto.ClienteResponseDTO;
import igor.projeto.tp1.mapper.ClienteMapper;
import igor.projeto.tp1.model.Cliente;
import igor.projeto.tp1.service.ClienteService;
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

@Path("/cliente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {

    @Inject
    ClienteService service;

    @GET
    public List<ClienteResponseDTO> buscarTodos() {
        return service.findAll()
                .stream()
                .map(e -> ClienteMapper.toResponseDTO(e))
                .toList();
    }

    @GET
    @Path("/{id}")
    public ClienteResponseDTO buscarPeloId(@PathParam("id") Long id) {
        return ClienteMapper.toResponseDTO(service.findById(id));
    }

    @DELETE
    @Path("/{id}")
    public void deletar(@PathParam("id") Long id) {
        service.delete(id);
    }

    @POST
    public ClienteResponseDTO incluir(ClienteRequestDTO dto) {
        Cliente cliente = service.create(ClienteMapper.toEntity(dto));
        return ClienteMapper.toResponseDTO(cliente);
    }

    @PUT
    @Path("/{id}")
    public void alterar(@PathParam("id") Long id, ClienteRequestDTO dto) {
        service.update(id, dto);
    }
}