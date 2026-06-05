package igor.projeto.tp1.dto.funcionario;

public record FuncionarioResponseDTO(
    Long id,
    String apelido,
    String telefone,
    String endereco,
    String cpf,
    String cargo,
    Double salario) 
{
}