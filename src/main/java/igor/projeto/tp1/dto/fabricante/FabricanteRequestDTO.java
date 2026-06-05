package igor.projeto.tp1.dto.fabricante;

public record FabricanteRequestDTO(
    String marca,
    String cnpj,
    Long idFornecedor) 
{
}