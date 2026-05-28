package igor.projeto.tp1.exception.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

import igor.projeto.tp1.exception.ProblemDetail;
import igor.projeto.tp1.exception.ValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;

class ExceptionMapperTest {

    @Test
    void testValidationExceptionMapper() {
        ValidationExceptionMapper mapper = new ValidationExceptionMapper();
        
        ValidationException exception = new ValidationException("Erro de validação", "campo");
        
        Response response = mapper.toResponse(exception);
        
        assertEquals(422, response.getStatus());
        assertNotNull(response.getEntity());
        
        ProblemDetail problemDetail = (ProblemDetail) response.getEntity();
        assertEquals("Erro de validação", problemDetail.getTitle());
        assertEquals("Erro de validação", problemDetail.getDetail());
        assertEquals(422, problemDetail.getStatus());
        assertEquals("http://localhost:8080/errors/validation-error", problemDetail.getType());
        assertNotNull(problemDetail.getErrors());
        assertEquals(1, problemDetail.getErrors().size());
        assertEquals("campo", problemDetail.getErrors().get(0).getField());
        assertEquals("Erro de validação", problemDetail.getErrors().get(0).getMessage());
    }

    @Test
    void testConstraintViolationExceptionMapper() {
        ConstraintViolationExceptionMapper mapper = new ConstraintViolationExceptionMapper();
        
        // Criar uma ConstraintViolation mock
        ConstraintViolation<?> violation = new ConstraintViolation<Object>() {
            @Override
            public String getMessage() {
                return "não deve estar em branco";
            }

            @Override
            public String getMessageTemplate() {
                return "{jakarta.validation.constraints.NotBlank.message}";
            }

            @Override
            public Object getRootBean() {
                return null;
            }

            @Override
            public Class<Object> getRootBeanClass() {
                return null;
            }

            @Override
            public Object getLeafBean() {
                return null;
            }

            @Override
            public Object getInvalidValue() {
                return "";
            }

            @Override
            public jakarta.validation.Path getPropertyPath() {
                return new jakarta.validation.Path() {
                    @Override
                    public java.util.Iterator<jakarta.validation.Path.Node> iterator() {
                        return null;
                    }

                    @Override
                    public String toString() {
                        return "createClienteRequestDTO.nome";
                    }
                };
            }

            @Override
            public Object[] getExecutableParameters() {
                return null;
            }

            @Override
            public Object getExecutableReturnValue() {
                return null;
            }

            @Override
            public jakarta.validation.metadata.ConstraintDescriptor<?> getConstraintDescriptor() {
                return null;
            }

            @Override
            public <U> U unwrap(Class<U> type) {
                return type.cast(this);
            }
        };
        
        Set<ConstraintViolation<?>> violations = Set.of(violation);
        ConstraintViolationException exception = new ConstraintViolationException(violations);
        
        Response response = mapper.toResponse(exception);
        
        assertEquals(422, response.getStatus());
        assertNotNull(response.getEntity());
        
        ProblemDetail problemDetail = (ProblemDetail) response.getEntity();
        assertEquals("Erro de validação", problemDetail.getTitle());
        assertEquals("Um ou mais campos não passaram na validação.", problemDetail.getDetail());
        assertEquals(422, problemDetail.getStatus());
        assertEquals("http://localhost:8080/errors/validation-error", problemDetail.getType());
        assertNotNull(problemDetail.getErrors());
        assertEquals(1, problemDetail.getErrors().size());
        assertEquals("nome", problemDetail.getErrors().get(0).getField());
        assertEquals("não deve estar em branco", problemDetail.getErrors().get(0).getMessage());
    }
}
