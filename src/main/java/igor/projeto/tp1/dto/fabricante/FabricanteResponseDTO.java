package igor.projeto.tp1.dto.fabricante;

import igor.projeto.tp1.model.Fornecedor;

public record FabricanteResponseDTO(
    Long id,
    String marca,
    String cnpj,
    Fornecedor fornecedor) 
{
}