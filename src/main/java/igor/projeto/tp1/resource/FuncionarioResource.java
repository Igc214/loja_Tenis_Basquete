package igor.projeto.tp1.resource;

import java.util.List;

import igor.projeto.tp1.dto.funcionario.FuncionarioRequestDTO;
import igor.projeto.tp1.dto.funcionario.FuncionarioResponseDTO;
import igor.projeto.tp1.mapper.FuncionarioMapper;
import igor.projeto.tp1.model.Funcionario;
import igor.projeto.tp1.service.FuncionarioService;
import jakarta.annotation.security.RolesAllowed;
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

@Path("/funcionario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"ADMIN", "FUNCIONARIO"})
public class FuncionarioResource {
    
    @Inject
    FuncionarioService service;

    @GET
    public List<FuncionarioResponseDTO> buscarTodos() {
        return service.findAll()
                .stream()
                .map(e -> FuncionarioMapper.toResponseDTO(e))
                .toList();
    }

    @GET
    @Path("/{id}")
    public FuncionarioResponseDTO buscarPeloId(@PathParam("id") Long id) {
        return FuncionarioMapper.toResponseDTO(service.findById(id));
    }

    @DELETE
    @Path("/{id}")
    public void deletar(@PathParam("id") Long id) {
        service.delete(id);
    }

    @POST
    public FuncionarioResponseDTO incluir(FuncionarioRequestDTO dto) {
        Funcionario funcionario = service.create(FuncionarioMapper.toEntity(dto));
        return FuncionarioMapper.toResponseDTO(funcionario);
    }

    @PUT
    @Path("/{id}")
    public void alterar(@PathParam("id") Long id, FuncionarioRequestDTO dto) {
        service.update(id, dto);
    }
}
