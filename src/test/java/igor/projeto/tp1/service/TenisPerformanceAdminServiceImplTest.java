package igor.projeto.tp1.service;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import igor.projeto.tp1.model.Fabricante;
import igor.projeto.tp1.model.Posicao;
import igor.projeto.tp1.model.TenisPerformance;
import igor.projeto.tp1.model.TipoSolado;
import igor.projeto.tp1.repository.TenisPerformanceRepository;

class TenisPerformanceAdminServiceImplTest {

    private static class StubTenisPerformanceRepository extends TenisPerformanceRepository {
        private TenisPerformance stored;
        private TenisPerformance persisted;

        @Override
        public TenisPerformance findById(Long id) {
            if (stored == null || stored.getId() == null) {
                return null;
            }
            return stored.getId().equals(id) ? stored : null;
        }

        @Override
        public void persist(TenisPerformance entity) {
            this.persisted = entity;
            this.stored = entity;
        }
    }

    private TenisPerformance createValidTenis() {
        TenisPerformance tenis = new TenisPerformance();
        tenis.setNome("Nike Air Jordan");
        tenis.setNumeroDoPe(42);
        tenis.setCor("Preto");
        tenis.setDescricao("Tênis premium");
        tenis.setPreco(BigDecimal.valueOf(120.0));
        tenis.setEstoque(10);
        tenis.setEdicaoLimitada(0);
        tenis.setAutografado(true);
        tenis.setPosicao(Posicao.SG);
        tenis.setTipoSolado(TipoSolado.OUTDOOR);

        Fabricante fabricante = new Fabricante();
        fabricante.setId(1L);
        tenis.setFabricante(fabricante);

        return tenis;
    }

    @Test
    void createShouldPersistPrecoAndEdicaoLimitada() {
        TenisPerformanceAdminServiceImpl service = new TenisPerformanceAdminServiceImpl();
        StubTenisPerformanceRepository repository = new StubTenisPerformanceRepository();
        service.repository = repository;

        TenisPerformance tenis = createValidTenis();
        tenis.setPreco(BigDecimal.valueOf(250.0));
        tenis.setEdicaoLimitada(1);

        service.create(tenis, null);

        assertEquals(BigDecimal.valueOf(250.0), repository.persisted.getPreco());
        assertEquals(1, repository.persisted.getEdicaoLimitada());
    }

    @Test
    void updateShouldPersistPrecoAndEdicaoLimitada() {
        TenisPerformanceAdminServiceImpl service = new TenisPerformanceAdminServiceImpl();
        StubTenisPerformanceRepository repository = new StubTenisPerformanceRepository();
        service.repository = repository;

        TenisPerformance existing = createValidTenis();
        existing.setId(1L);
        repository.persist(existing);

        TenisPerformance updated = createValidTenis();
        updated.setPreco(BigDecimal.valueOf(250.0));
        updated.setEdicaoLimitada(1);
        updated.setEstoque(8);

        service.update(1L, updated, null);

        assertEquals(BigDecimal.valueOf(250.0), repository.stored.getPreco());
        assertEquals(1, repository.stored.getEdicaoLimitada());
    }
}
