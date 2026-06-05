package igor.projeto.tp1.resource;

import java.net.URI;

import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/")
public class RootResource {

    @GET
    @PermitAll
    public Response redirectToSwagger() {
        return Response.seeOther(URI.create("/q/swagger-ui/")).build();
    }
}
