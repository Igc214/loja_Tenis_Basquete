package igor.projeto.tp1.service;

import java.util.List;

import igor.projeto.tp1.exception.ValidationException;
import igor.projeto.tp1.model.TenisPerformance;
import igor.projeto.tp1.model.Review;
import igor.projeto.tp1.repository.TenisPerformanceRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TenisPerformanceAdminServiceImpl implements TenisPerformanceAdminService {

    @Inject
    TenisPerformanceRepository repository;

    @Override
    public List<TenisPerformance> findAll() {
        return repository.findAll().list();
    }

    @Override
    public TenisPerformance findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public TenisPerformance create(TenisPerformance tenisPerformance, String url) {
        if (tenisPerformance.getNome() == null || tenisPerformance.getNome().isEmpty() ||
            tenisPerformance.getNumeroDoPe() == 0 ||
            tenisPerformance.getCor() == null || tenisPerformance.getCor().isEmpty() ||
            tenisPerformance.getDescricao() == null || tenisPerformance.getDescricao().isEmpty() ||
            tenisPerformance.getPosicao() == null ||
            tenisPerformance.getTipoSolado() == null ||
            tenisPerformance.getFabricante() == null) {
            throw new ValidationException("Precisa preencher todos os campos obrigatórios");
        }

        if (url != null && !url.isBlank()) {
            Review review = new Review();
            review.setUrl(url);
            review.setTenisPerformance(tenisPerformance);
            tenisPerformance.setReview(review);
        }

        repository.persist(tenisPerformance);
        return tenisPerformance;
    }

    @Override
    @Transactional
    public void update(Long id, TenisPerformance tenisPerformance, String url) {
        TenisPerformance e = findById(id);

        if (e == null) {
            throw new ValidationException("Tênis de performance não encontrado para o ID: " + id);
        }

        if (tenisPerformance.getNome() == null || tenisPerformance.getNome().isEmpty() ||
            tenisPerformance.getNumeroDoPe() == 0 ||
            tenisPerformance.getCor() == null || tenisPerformance.getCor().isEmpty() ||
            tenisPerformance.getPosicao() == null ||
            tenisPerformance.getTipoSolado() == null ||
            tenisPerformance.getFabricante() == null ||
            tenisPerformance.getDescricao() == null || tenisPerformance.getDescricao().isEmpty()) {
            throw new ValidationException("Precisa preencher todos os campos obrigatórios");
        }

        e.setNome(tenisPerformance.getNome());
        e.setNumeroDoPe(tenisPerformance.getNumeroDoPe());
        e.setCor(tenisPerformance.getCor());
        e.setPosicao(tenisPerformance.getPosicao());
        e.setTipoSolado(tenisPerformance.getTipoSolado());
        e.setFabricante(tenisPerformance.getFabricante());
        e.setAutografado(tenisPerformance.isAutografado());
        e.setDescricao(tenisPerformance.getDescricao());
        if (url != null && !url.isBlank()) {
            Review review = new Review();
            review.setUrl(url);
            review.setTenisPerformance(e);
            e.setReview(review);
        }
        

    }   

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}