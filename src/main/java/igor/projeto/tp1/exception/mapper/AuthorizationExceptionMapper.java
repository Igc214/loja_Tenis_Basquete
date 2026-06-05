package igor.projeto.tp1.exception.mapper;

import igor.projeto.tp1.exception.ProblemDetail;
import igor.projeto.tp1.exception.AuthorizationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 * Mapeador para AuthorizationException (erros de autorização) seguindo o padrão RFC 7807
 * Trata casos onde o usuário não possui permissão para acessar um recurso
 */
@Provider
public class AuthorizationExceptionMapper implements ExceptionMapper<AuthorizationException> {

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(AuthorizationException exception) {
        int status = exception.getStatus() != null ? exception.getStatus() : 403;
        String title = status == 401 ? "Nao autenticado" : "Acesso negado";

        ProblemDetail problemDetail = new ProblemDetail(
            status,
            title,
            exception.getMessage()
        );
        
        problemDetail.setType(status == 401
            ? "http://localhost:8080/errors/authentication-error"
            : "http://localhost:8080/errors/authorization-error");
        
        if (uriInfo != null) {
            problemDetail.setInstance(uriInfo.getPath());
        }

        return Response
            .status(status)
            .entity(problemDetail)
            .build();
    }
}
