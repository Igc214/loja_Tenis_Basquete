package igor.projeto.tp1.exception.mapper;

import igor.projeto.tp1.exception.ProblemDetail;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(NotFoundException exception) {
        ProblemDetail problemDetail = new ProblemDetail(
                404,
                "Recurso nao encontrado",
                exception.getMessage()
        );

        problemDetail.setType("http://localhost:8080/errors/not-found");

        if (uriInfo != null) {
            problemDetail.setInstance(uriInfo.getPath());
        }

        return Response.status(Response.Status.NOT_FOUND)
                .entity(problemDetail)
                .build();
    }
}
