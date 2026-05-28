package igor.projeto.tp1.mapper;

import igor.projeto.tp1.dto.FabricanteRequestDTO;
import igor.projeto.tp1.dto.FabricanteResponseDTO;
import igor.projeto.tp1.model.Fabricante;
import igor.projeto.tp1.model.Fornecedor;

public class FabricanteMapper {
    
    public static Fabricante toEntity(FabricanteRequestDTO dto) {
        if(dto == null) {
            return null;
        }

        Fabricante fabricante = new Fabricante();
        fabricante.setMarca(dto.marca());
        fabricante.setCnpj(dto.cnpj());

        if (dto.idFornecedor() != null) {
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setId(dto.idFornecedor());
            fabricante.setFornecedor(fornecedor);
        }

        return fabricante;
    }  
    
    public static FabricanteResponseDTO toResponseDTO(Fabricante fabricante) {
        if(fabricante == null) {
            return null;
        }

        return new FabricanteResponseDTO(
            fabricante.getId(),
            fabricante.getMarca(),
            fabricante.getCnpj(),
            fabricante.getFornecedor()
        );
    }
}