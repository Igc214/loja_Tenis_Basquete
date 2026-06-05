package igor.projeto.tp1.exception;

/**
 * Exceção lançada quando um usuário tenta acessar um recurso sem permissão
 */
public class AuthorizationException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String resource;
    private Integer status;

    public AuthorizationException(String message) {
        super(message);
    }

    public AuthorizationException(String message, String resource) {
        super(message);
        this.resource = resource;
    }

    public AuthorizationException(String message, String resource, jakarta.ws.rs.core.Response.Status status) {
        super(message);
        this.resource = resource;
        this.status = status.getStatusCode();
    }

    public AuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthorizationException(String message, String resource, Throwable cause) {
        super(message, cause);
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public Integer getStatus() {
        return status;
    }
}
