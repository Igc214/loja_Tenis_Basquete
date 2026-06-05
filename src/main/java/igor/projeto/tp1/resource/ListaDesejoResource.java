package igor.projeto.tp1.resource;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;

import igor.projeto.tp1.dto.listadesejo.ListaDesejoRequestDTO;
import igor.projeto.tp1.dto.listadesejo.ListaDesejoResponseDTO;
import igor.projeto.tp1.mapper.ListaDesejoMapper;
import igor.projeto.tp1.model.ListaDesejo;
import igor.projeto.tp1.service.ListaDesejoServiceInter;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/lista-desejos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"ADMIN", "FUNCIONARIO", "CLIENTE"})
public class ListaDesejoResource {

    @Inject
    ListaDesejoServiceInter service;

    @Inject
    ListaDesejoMapper mapper;

    @Inject
    JsonWebToken jwt;

    @POST
    public Response adicionar(@Valid ListaDesejoRequestDTO dto) {
        ListaDesejo item = service.adicionar(loginAutenticado(), dto);
        return Response.status(Response.Status.CREATED)
                .entity(mapper.toResponseDTO(item))
                .build();
    }

    @GET
    public Response listar() {
        List<ListaDesejoResponseDTO> itens = service.listar(loginAutenticado())
                .stream()
                .map(mapper::toResponseDTO)
                .toList();

        return Response.ok(itens).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        return Response.ok(mapper.toResponseDTO(service.buscarPorId(loginAutenticado(), id))).build();
    }

    @DELETE
    @Path("/{id}")
    public Response remover(@PathParam("id") Long id) {
        service.remover(loginAutenticado(), id);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/produtos/{produtoId}")
    public Response removerPorProduto(@PathParam("produtoId") Long produtoId) {
        service.removerPorProduto(loginAutenticado(), produtoId);
        return Response.noContent().build();
    }

    private String loginAutenticado() {
        return jwt.getClaim("upn");
    }
}
