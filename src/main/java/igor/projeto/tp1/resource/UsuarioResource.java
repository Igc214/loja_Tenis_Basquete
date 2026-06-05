package igor.projeto.tp1.resource;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.jwt.Claims;

import igor.projeto.tp1.dto.usuario.CadastroCompletoDTO;
import igor.projeto.tp1.dto.usuario.CadastroSimplesDTO;
import igor.projeto.tp1.dto.usuario.EditarDadosDTO;
import igor.projeto.tp1.dto.usuario.UsuarioRequestDTO;
import igor.projeto.tp1.dto.usuario.UsuarioResponseDTO;
import igor.projeto.tp1.mapper.UsuarioMapper;
import igor.projeto.tp1.model.Usuario;
import igor.projeto.tp1.service.UsuarioServiceI;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioServiceI service;

    @Inject
    UsuarioMapper mapper;

    @Inject
    JsonWebToken jwt;

    @POST
    @PermitAll
    public Response criar(@Valid CadastroSimplesDTO dto) {
        Usuario usuario = service.create(dto);
        return Response.status(Status.CREATED)
                .entity(mapper.toResponseDTO(usuario))
                .build();
    }

    @POST
    @Path("/cadastro/simples")
    @RolesAllowed({"ADMIN", "FUNCIONARIO"})
    public Response cadastroSimples(@Valid CadastroSimplesDTO dto) {
        Usuario usuario = service.create(dto);
        return Response.status(Status.CREATED)
                .entity(mapper.toResponseDTO(usuario))
                .build();
    }

    @POST
    @Path("/cadastro/completo")
    @RolesAllowed({"ADMIN", "FUNCIONARIO"})
    public Response cadastroCompleto(@Valid CadastroCompletoDTO dto) {
        Usuario usuario = service.create(dto);
        return Response.status(Status.CREATED)
                .entity(mapper.toResponseDTO(usuario))
                .build();
    }

    @GET
    @RolesAllowed({"ADMIN", "FUNCIONARIO"})
    public Response listarTodos() {
        List<UsuarioResponseDTO> usuarios = service.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
        return Response.ok(usuarios).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "FUNCIONARIO"})
    public Response buscarPorId(@PathParam("id") Long id) {
        Usuario usuario = service.findById(id);
        if (usuario == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok(mapper.toResponseDTO(usuario)).build();
    }

    @PATCH
    @Path("/editar/senha/{token}")
    @RolesAllowed({"ADMIN", "FUNCIONARIO", "CLIENTE"})
    public Response atualizarSenha(@PathParam("token") String token, String novaSenha) {
        String login = jwt.getClaim("upn").toString();
        service.setPassword(login, token, novaSenha);
        return Response.noContent().build();
    }

    @PUT
    @Path("/editar/dados")
    @RolesAllowed({"ADMIN", "FUNCIONARIO", "CLIENTE"})
    public Response editarDados(@Valid EditarDadosDTO dto) {
        String login = jwt.getClaim("upn").toString();
        service.update(login, dto);
        Usuario usuario = service.findByLogin(login);
        return Response.ok(mapper.toResponseDTO(usuario)).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    public Response deletar(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    public Response atualizar(@PathParam("id") Long id, @Valid UsuarioRequestDTO dto) {
        service.update(id, dto);
        Usuario usuario = service.findById(id);
        return Response.ok(mapper.toResponseDTO(usuario)).build();
    }
}
