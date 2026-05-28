package igor.projeto.tp1.mapper;

import igor.projeto.tp1.dto.FuncionarioRequestDTO;
import igor.projeto.tp1.dto.FuncionarioResponseDTO;
import igor.projeto.tp1.model.Funcionario;

public class FuncionarioMapper {
    
    public static Funcionario toEntity(FuncionarioRequestDTO dto) {
        if(dto == null) {
            return null;
        }

        Funcionario funcionario = new Funcionario();
        funcionario.setApelido(dto.apelido());
        funcionario.setTelefone(dto.telefone());
        funcionario.setEndereco(dto.endereco());
        funcionario.setCpf(dto.cpf());
        funcionario.setCargo(dto.cargo());
        funcionario.setSalario(dto.salario());

        return funcionario;
    }  
    
    public static FuncionarioResponseDTO toResponseDTO(Funcionario funcionario) {
        if(funcionario == null) {
            return null;
        }

        return new FuncionarioResponseDTO(
            funcionario.getId(),
            funcionario.getApelido(),
            funcionario.getTelefone(),
            funcionario.getEndereco(),
            funcionario.getCpf(),
            funcionario.getCargo(),
            funcionario.getSalario()
        );
    }
}