package igor.projeto.tp1.resource;


import igor.projeto.tp1.model.Posicao;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/posicao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PosicaoResource {

    @GET
    public Posicao[] buscarTodos() {
        return Posicao.values();
    }

    @GET
    @Path("/{id}")
    public Posicao buscarPorId(@PathParam("id") Long id) {
        return Posicao.valueOf(id);
    }
    
}
