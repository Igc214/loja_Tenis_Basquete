package igor.projeto.tp1.dto;

public record FornecedorResponseDTO(
    Long id,
    String apelido,
    String telefone,
    String endereco,
    String cnpj) 
{
}