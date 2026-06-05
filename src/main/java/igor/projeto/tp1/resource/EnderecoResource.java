package igor.projeto.tp1.resource;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;

import igor.projeto.tp1.dto.endereco.EnderecoRequestDTO;
import igor.projeto.tp1.dto.endereco.EnderecoResponseDTO;
import igor.projeto.tp1.mapper.EnderecoMapper;
import igor.projeto.tp1.model.Endereco;
import igor.projeto.tp1.service.EnderecoServiceInter;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/enderecos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"ADMIN", "FUNCIONARIO", "CLIENTE"})
public class EnderecoResource {

    @Inject
    EnderecoServiceInter service;

    @Inject
    EnderecoMapper mapper;

    @Inject
    JsonWebToken jwt;

    @POST
    public Response cadastrar(@Valid EnderecoRequestDTO dto) {
        Endereco endereco = service.cadastrar(loginAutenticado(), dto);
        return Response.status(Response.Status.CREATED)
                .entity(mapper.toResponseDTO(endereco))
                .build();
    }

    @GET
    public Response listar() {
        List<EnderecoResponseDTO> enderecos = service.listar(loginAutenticado())
                .stream()
                .map(mapper::toResponseDTO)
                .toList();

        return Response.ok(enderecos).build();
    }

    @GET
    @Path("/usuarios/{usuarioId}")
    @RolesAllowed({"ADMIN", "FUNCIONARIO"})
    public Response listarPorUsuario(@PathParam("usuarioId") Long usuarioId) {
        List<EnderecoResponseDTO> enderecos = service.listarPorUsuarioId(usuarioId)
                .stream()
                .map(mapper::toResponseDTO)
                .toList();

        return Response.ok(enderecos).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        return Response.ok(mapper.toResponseDTO(service.buscarPorId(loginAutenticado(), id))).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, @Valid EnderecoRequestDTO dto) {
        Endereco endereco = service.atualizar(loginAutenticado(), id, dto);
        return Response.ok(mapper.toResponseDTO(endereco)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response remover(@PathParam("id") Long id) {
        service.remover(loginAutenticado(), id);
        return Response.noContent().build();
    }

    private String loginAutenticado() {
        return jwt.getClaim("upn");
    }
}
