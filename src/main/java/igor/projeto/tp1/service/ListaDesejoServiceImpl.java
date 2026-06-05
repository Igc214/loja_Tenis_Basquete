package igor.projeto.tp1.service;

import java.util.List;

import igor.projeto.tp1.dto.listadesejo.ListaDesejoRequestDTO;
import igor.projeto.tp1.exception.ValidationException;
import igor.projeto.tp1.model.ListaDesejo;
import igor.projeto.tp1.model.TenisPerformance;
import igor.projeto.tp1.model.Usuario;
import igor.projeto.tp1.repository.ListaDesejoRepository;
import igor.projeto.tp1.repository.TenisPerformanceRepository;
import igor.projeto.tp1.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ListaDesejoServiceImpl implements ListaDesejoServiceInter {

    @Inject
    ListaDesejoRepository repository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    TenisPerformanceRepository tenisPerformanceRepository;

    @Override
    @Transactional
    public ListaDesejo adicionar(String login, ListaDesejoRequestDTO dto) {
        Usuario usuario = buscarUsuario(login);
        TenisPerformance produto = buscarProduto(dto.idProduto());

        if (repository.existsByUsuarioLoginAndProdutoId(login, produto.getId())) {
            throw new ValidationException("Produto ja esta na lista de desejos", "idProduto");
        }

        ListaDesejo item = new ListaDesejo();
        item.setUsuario(usuario);
        item.setTenisPerformance(produto);

        repository.persist(item);
        return item;
    }

    @Override
    public List<ListaDesejo> listar(String login) {
        buscarUsuario(login);
        return repository.findByUsuarioLogin(login);
    }

    @Override
    public ListaDesejo buscarPorId(String login, Long id) {
        return repository.findByIdAndUsuarioLogin(id, login)
                .orElseThrow(() -> new NotFoundException("Item da lista de desejos nao encontrado"));
    }

    @Override
    @Transactional
    public void remover(String login, Long id) {
        ListaDesejo item = buscarPorId(login, id);
        repository.delete(item);
    }

    @Override
    @Transactional
    public void removerPorProduto(String login, Long produtoId) {
        buscarProduto(produtoId);
        ListaDesejo item = repository.findByUsuarioLoginAndProdutoId(login, produtoId)
                .orElseThrow(() -> new NotFoundException("Produto nao encontrado na lista de desejos"));

        repository.delete(item);
    }

    private Usuario buscarUsuario(String login) {
        return usuarioRepository.findByLogin(login)
                .orElseThrow(() -> new NotFoundException("Usuario nao encontrado"));
    }

    private TenisPerformance buscarProduto(Long produtoId) {
        TenisPerformance produto = tenisPerformanceRepository.findById(produtoId);
        if (produto == null) {
            throw new NotFoundException("Produto nao encontrado");
        }
        if (!produto.isAtivo()) {
            throw new NotFoundException("Produto nao encontrado");
        }
        return produto;
    }
}
