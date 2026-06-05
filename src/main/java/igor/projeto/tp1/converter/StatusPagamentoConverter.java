package igor.projeto.tp1.converter;

import igor.projeto.tp1.model.StatusPagamento;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusPagamentoConverter implements AttributeConverter<StatusPagamento, Long> {

    @Override
    public Long convertToDatabaseColumn(StatusPagamento status) {
        return status == null ? null : status.getId();
    }

    @Override
    public StatusPagamento convertToEntityAttribute(Long id) {
        return StatusPagamento.valueOf(id);
    }
}
