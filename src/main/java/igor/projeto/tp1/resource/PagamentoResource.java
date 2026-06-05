package igor.projeto.tp1.resource;

import java.util.List;
import java.util.Set;

import org.eclipse.microprofile.jwt.JsonWebToken;

import igor.projeto.tp1.dto.pagamento.PagamentoPatchDTO;
import igor.projeto.tp1.dto.pagamento.PagamentoRequestDTO;
import igor.projeto.tp1.dto.pagamento.PagamentoResponseDTO;
import igor.projeto.tp1.mapper.PagamentoMapper;
import igor.projeto.tp1.model.Pagamento;
import igor.projeto.tp1.service.PagamentoServiceInter;
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

@Path("/pagamentos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"ADMIN", "FUNCIONARIO", "CLIENTE"})
public class PagamentoResource {

    @Inject
    PagamentoServiceInter service;

    @Inject
    PagamentoMapper mapper;

    @Inject
    JsonWebToken jwt;

    @POST
    public Response criar(@Valid PagamentoRequestDTO dto) {
        Pagamento pagamento = service.criar(loginAutenticado(), dto);
        return Response.status(Response.Status.CREATED)
                .entity(mapper.toResponseDTO(pagamento))
                .build();
    }

    @GET
    public Response listar() {
        List<PagamentoResponseDTO> pagamentos = service.listar(loginAutenticado(), acessoAdministrativo())
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
        return Response.ok(pagamentos).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        return Response.ok(mapper.toResponseDTO(service.buscarPorId(loginAutenticado(), id, acessoAdministrativo()))).build();
    }

    @PATCH
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "FUNCIONARIO"})
    public Response atualizarStatus(@PathParam("id") Long id, @Valid PagamentoPatchDTO dto) {
        return Response.ok(mapper.toResponseDTO(service.atualizarStatus(id, dto))).build();
    }

    @PATCH
    @Path("/{id}/cancelar")
    public Response cancelarPorPatch(@PathParam("id") Long id) {
        service.cancelar(loginAutenticado(), id, acessoAdministrativo());
        return Response.noContent().build();
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
