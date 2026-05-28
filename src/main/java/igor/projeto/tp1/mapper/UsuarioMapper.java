package igor.projeto.tp1.mapper;

import igor.projeto.tp1.dto.UsuarioRequestDTO;
import igor.projeto.tp1.dto.UsuarioResponseDTO;
import igor.projeto.tp1.model.Usuario;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setLogin(dto.login());
        usuario.setSenhaHash(dto.senha());
        usuario.setPerfil(dto.perfil());
        return usuario;
    }

    public static UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getPerfil()
        );
    }
}
