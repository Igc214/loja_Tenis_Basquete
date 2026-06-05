package igor.projeto.tp1.mapper;

import igor.projeto.tp1.dto.listadesejo.ListaDesejoResponseDTO;
import igor.projeto.tp1.model.ListaDesejo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ListaDesejoMapper {

    @Inject
    UsuarioMapper usuarioMapper;

    public ListaDesejoResponseDTO toResponseDTO(ListaDesejo listaDesejo) {
        if (listaDesejo == null) {
            return null;
        }

        return new ListaDesejoResponseDTO(
                listaDesejo.getId(),
                usuarioMapper.toResponseDTO(listaDesejo.getUsuario()),
                listaDesejo.getUsuario() != null ? listaDesejo.getUsuario().getEndereco() : null,
                listaDesejo.getUsuario() != null ? listaDesejo.getUsuario().getJogadorFavorito() : null,
                listaDesejo.getUsuario() != null ? listaDesejo.getUsuario().getTimeNba() : null,
                TenisEcommerceMapper.toBuscaDTO(listaDesejo.getTenisPerformance()),
                listaDesejo.getAdicionadoEm()
        );
    }
}
