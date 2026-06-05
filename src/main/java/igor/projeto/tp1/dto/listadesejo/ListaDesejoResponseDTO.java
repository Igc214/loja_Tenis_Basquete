package igor.projeto.tp1.dto.listadesejo;

import java.time.LocalDateTime;

import igor.projeto.tp1.dto.loja.TenisBuscaLojaDTO;
import igor.projeto.tp1.dto.usuario.UsuarioResponseDTO;

public record ListaDesejoResponseDTO(
        Long id,
        UsuarioResponseDTO usuario,
        String endereco,
        String jogadorFavorito,
        String timeNba,
        TenisBuscaLojaDTO produto,
        LocalDateTime adicionadoEm
) {
}
