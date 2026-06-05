package igor.projeto.tp1.mapper;

import igor.projeto.tp1.dto.endereco.EnderecoRequestDTO;
import igor.projeto.tp1.dto.endereco.EnderecoResponseDTO;
import igor.projeto.tp1.model.Endereco;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnderecoMapper {

    public Endereco toEntity(EnderecoRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Endereco endereco = new Endereco();
        endereco.setRua(dto.rua());
        endereco.setNumero(dto.numero());
        endereco.setCidade(dto.cidade());
        endereco.setEstado(dto.estado());
        endereco.setCep(dto.cep());
        endereco.setVersion(dto.version());
        return endereco;
    }

    public EnderecoResponseDTO toResponseDTO(Endereco endereco) {
        if (endereco == null) {
            return null;
        }

        return new EnderecoResponseDTO(
                endereco.getId(),
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCep(),
                endereco.getDataCadastro(),
                endereco.getVersion()
        );
    }

    public void copyToEntity(EnderecoRequestDTO dto, Endereco endereco) {
        endereco.setRua(dto.rua());
        endereco.setNumero(dto.numero());
        endereco.setCidade(dto.cidade());
        endereco.setEstado(dto.estado());
        endereco.setCep(dto.cep());
    }
}
