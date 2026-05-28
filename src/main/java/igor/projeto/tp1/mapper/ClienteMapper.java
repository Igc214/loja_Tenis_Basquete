package igor.projeto.tp1.mapper;

import igor.projeto.tp1.dto.ClienteRequestDTO;
import igor.projeto.tp1.dto.ClienteResponseDTO;
import igor.projeto.tp1.model.Cliente;
import igor.projeto.tp1.model.Pessoa;

public class ClienteMapper {
    
    public static Cliente toEntity(ClienteRequestDTO dto) {
        if(dto == null) {
            return null;
        }

        Cliente cliente = new Cliente();
        cliente.setApelido(dto.apelido());
        cliente.setTelefone(dto.telefone());
        cliente.setEndereco(dto.endereco());
        cliente.setCpf(dto.cpf());
        cliente.setEmail(dto.email());
        cliente.setNumeroDeCompra(dto.numeroDeCompra());

        return cliente;
    }  
    
    public static ClienteResponseDTO toResponseDTO(Cliente cliente) {
        if(cliente == null) {
            return null;
        }

        
        return new ClienteResponseDTO(
            cliente.getId(),
            cliente.getApelido(),
            cliente.getTelefone(),
            cliente.getEndereco(),
            cliente.getCpf(),
            cliente.getEmail(),
            cliente.getNumeroDeCompra()
        );
    }
}