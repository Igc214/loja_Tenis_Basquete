package igor.projeto.tp1.dto;

public record ClienteResponseDTO(
    Long id,
    String apelido,
    String telefone,
    String endereco,
    String cpf,
    String email,
    Integer numeroDeCompra) 
{
}