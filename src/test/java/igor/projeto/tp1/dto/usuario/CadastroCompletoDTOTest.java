package igor.projeto.tp1.dto.usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

class CadastroCompletoDTOTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldValidateEnderecoFields() {
        CadastroCompletoDTO dto = new CadastroCompletoDTO(
                "Igor",
                "Carvalho",
                "Stephen Curry",
                "Golden State Warriors",
                "Ab",
                "",
                "P",
                "T",
                "123"
        );

        Set<String> camposInvalidos = validator.validate(dto)
                .stream()
                .map(ConstraintViolation::getPropertyPath)
                .map(Object::toString)
                .collect(Collectors.toSet());

        assertEquals(5, camposInvalidos.size());
        assertTrue(camposInvalidos.contains("rua"));
        assertTrue(camposInvalidos.contains("numero"));
        assertTrue(camposInvalidos.contains("cidade"));
        assertTrue(camposInvalidos.contains("estado"));
        assertTrue(camposInvalidos.contains("cep"));
    }

    @Test
    void shouldAcceptValidCadastroCompleto() {
        CadastroCompletoDTO dto = new CadastroCompletoDTO(
                "Igor",
                "Carvalho",
                "Stephen Curry",
                "Golden State Warriors",
                "Rua das Flores",
                "123",
                "Palmas",
                "TO",
                "77000-000"
        );

        assertTrue(validator.validate(dto).isEmpty());
    }
}
