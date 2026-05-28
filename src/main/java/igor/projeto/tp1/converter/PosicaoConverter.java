package igor.projeto.tp1.converter;

import igor.projeto.tp1.model.Posicao;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PosicaoConverter implements AttributeConverter<Posicao, Long> {

    @Override
    public Long convertToDatabaseColumn(Posicao posicao) {
        return posicao == null ? null : posicao.getId();
    }

    @Override
    public Posicao convertToEntityAttribute(Long id) {
        return Posicao.valueOf(id);
    }
    
}
