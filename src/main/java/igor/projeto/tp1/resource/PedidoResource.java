package igor.projeto.tp1.resource;

import java.util.List;
import java.util.Set;

import org.eclipse.microprofile.jwt.JsonWebToken;

import igor.projeto.tp1.dto.pedido.PedidoRequestDTO;
import igor.projeto.tp1.dto.pedido.PedidoResponseDTO;
import igor.projeto.tp1.mapper.PedidoMapper;
import igor.projeto.tp1.model.Pedido;
import igor.projeto.tp1.model.StatusPedido;
import igor.projeto.tp1.service.PedidoServiceInter;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"ADMIN", "FUNCIONARIO", "CLIENTE"})
public class PedidoResource {

    @Inject
    PedidoServiceInter service;

    @Inject
    PedidoMapper mapper;

    @Inject
    JsonWebToken jwt;

    @POST
    public Response criar(@Valid PedidoRequestDTO dto) {
        Pedido pedido = service.criar(loginAutenticado(), dto);
        return Response.status(Response.Status.CREATED)
                .entity(mapper.toResponseDTO(pedido))
                .build();
    }

    @GET
    public Response listar() {
        List<PedidoResponseDTO> pedidos = service.listar(loginAutenticado(), acessoAdministrativo())
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
        return Response.ok(pedidos).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        return Response.ok(mapper.toResponseDTO(service.buscarPorId(loginAutenticado(), id, acessoAdministrativo()))).build();
    }

    @PATCH
    @Path("/{id}/status/{status}")
    @RolesAllowed({"ADMIN", "FUNCIONARIO"})
    public Response atualizarStatus(@PathParam("id") Long id, @PathParam("status") StatusPedido status) {
        return Response.ok(mapper.toResponseDTO(service.atualizarStatus(id, status))).build();
    }

    @DELETE
    @Path("/{id}")
    public Response cancelar(@PathParam("id") Long id) {
        service.cancelar(loginAutenticado(), id, acessoAdministrativo());
        return Response.noContent().build();
    }

    private String loginAutenticado() {
        return jwt.getClaim("upn");
    }

    private boolean acessoAdministrativo() {
        Set<String> groups = jwt.getGroups();
        return groups != null && (groups.contains("ADMIN") || groups.contains("FUNCIONARIO"));
    }
}
