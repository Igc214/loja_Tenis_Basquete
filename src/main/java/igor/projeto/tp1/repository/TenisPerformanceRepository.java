package igor.projeto.tp1.repository;

import jakarta.enterprise.context.ApplicationScoped;
import igor.projeto.tp1.model.TenisPerformance;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class TenisPerformanceRepository implements PanacheRepository<TenisPerformance> {

     /* public PanacheQuery<TenisPerformance> findByNome(String nomeTenis) {
        return find("SELECT e FROM Tenis e WHERE UPPER (e.nomeTenis) LIKE UPPER (?1)","%" + nomeTenis + "%");
    } */
}