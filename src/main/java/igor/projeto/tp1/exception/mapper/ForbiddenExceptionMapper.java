package igor.projeto.tp1.exception.mapper;

import igor.projeto.tp1.exception.ProblemDetail;
import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 * Mapeador para ForbiddenException do Quarkus Security seguindo o padrão RFC 7807
 * Trata erros de autorização que ocorrem quando a anotação @RolesAllowed nega acesso
 */
@Provider
public class ForbiddenExceptionMapper implements ExceptionMapper<ForbiddenException> {

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(ForbiddenException exception) {
        ProblemDetail problemDetail = new ProblemDetail(
            403,
            "Acesso negado",
            "Você não possui permissão para acessar este recurso"
        );
        
        problemDetail.setType("http://localhost:8080/errors/forbidden");
        
        if (uriInfo != null) {
            problemDetail.setInstance(uriInfo.getPath());
        }

        return Response
            .status(403)
            .entity(problemDetail)
            .build();
    }
}
