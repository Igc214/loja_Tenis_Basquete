package igor.projeto.tp1.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;

import igor.projeto.tp1.dto.auth.AuthRequestDTO;
import igor.projeto.tp1.dto.auth.AuthResponseDTO;
import igor.projeto.tp1.dto.auth.ForgotPasswordDTO;
import igor.projeto.tp1.service.auth.AuthServiceInter;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    AuthServiceInter authService;

    @Inject
    JsonWebToken jwt;

    @POST
    @Path("/login")
    @PermitAll
    public Response login(@Valid AuthRequestDTO dto) {
        AuthResponseDTO response = authService.login(dto);
        return Response.ok(response).build();
    }

    @GET
    @Path("/info")
    @Authenticated
    public Response info() {
        return Response.ok(authService.info(jwt)).build();
    }

    @POST
    @Path("/alterar-senha")
    @Authenticated
    public Response alterarSenha(@Valid ForgotPasswordDTO dto) {
        return Response.ok(authService.alterarSenha(dto)).build();
    }
}
