package igor.projeto.tp1.mapper;

import igor.projeto.tp1.dto.FornecedorRequestDTO;
import igor.projeto.tp1.dto.FornecedorResponseDTO;
import igor.projeto.tp1.model.Fornecedor;

public class FornecedorMapper {
    
    public static Fornecedor toEntity(FornecedorRequestDTO dto) {
        if(dto == null) {
            return null;
        }

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setApelido(dto.apelido());
        fornecedor.setTelefone(dto.telefone());
        fornecedor.setEndereco(dto.endereco());
        fornecedor.setCnpj(dto.cnpj());

        return fornecedor;
    }  
    
    public static FornecedorResponseDTO toResponseDTO(Fornecedor fornecedor) {
        if(fornecedor == null) {
            return null;
        }

        return new FornecedorResponseDTO(
            fornecedor.getId(),
            fornecedor.getApelido(),
            fornecedor.getTelefone(),
            fornecedor.getEndereco(),
            fornecedor.getCnpj()
        );
    }
}