package igor.projeto.tp1.converter;

import igor.projeto.tp1.model.StatusPedido;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusPedidoConverter implements AttributeConverter<StatusPedido, Long> {

    @Override
    public Long convertToDatabaseColumn(StatusPedido status) {
        return status == null ? null : status.getId();
    }

    @Override
    public StatusPedido convertToEntityAttribute(Long id) {
        return StatusPedido.valueOf(id);
    }
}
