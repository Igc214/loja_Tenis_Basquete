package igor.projeto.tp1.mapper;

import igor.projeto.tp1.dto.usuario.CadastroCompletoDTO;
import igor.projeto.tp1.dto.usuario.CadastroSimplesDTO;
import igor.projeto.tp1.dto.usuario.UsuarioRequestDTO;
import igor.projeto.tp1.dto.usuario.UsuarioResponseDTO;
import igor.projeto.tp1.model.Perfil;
import igor.projeto.tp1.model.Usuario;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioMapper {

    public Usuario toEntity(UsuarioRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Usuario usuario = new Usuario();
        usuario.setLogin(dto.login());
        usuario.setPerfil(dto.perfil() != null ? dto.perfil() : Perfil.CLIENTE);
        usuario.setEndereco(dto.endereco());
        usuario.setVersion(dto.version());

        return usuario;
    }

    public Usuario toEntity(CadastroSimplesDTO dto) {
        if (dto == null) {
            return null;
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setLogin(dto.login());
        usuario.setSenhaHash(dto.senha());
        usuario.setPerfil(Perfil.CLIENTE);

        return usuario;
    }

    public Usuario toEntity(CadastroCompletoDTO dto) {
        if (dto == null) {
            return null;
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setSobrenome(dto.sobrenome());
        usuario.setLogin(dto.login());
        usuario.setSenhaHash(dto.senha());
        usuario.setEndereco(dto.endereco());
        usuario.setPerfil(Perfil.CLIENTE);

        return usuario;
    }

    public UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getNome(),
                usuario.getPerfil(),
                usuario.getEndereco()
        );
    }
}
