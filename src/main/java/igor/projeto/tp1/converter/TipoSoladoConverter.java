package igor.projeto.tp1.converter;

import igor.projeto.tp1.model.TipoSolado;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoSoladoConverter implements AttributeConverter<TipoSolado, Long> {

    @Override
    public Long convertToDatabaseColumn(TipoSolado tipoSolado) {
        return tipoSolado == null ? null : tipoSolado.getID();
    }

    @Override
    public TipoSolado convertToEntityAttribute(Long id) {
        return TipoSolado.valuesOf(id);
    }
    
}