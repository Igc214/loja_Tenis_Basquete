package igor.projeto.tp1.service;

import java.util.List;

import igor.projeto.tp1.model.TenisPerformance;

public interface TenisPerformanceAdminService {
    List<TenisPerformance> findAll();
    TenisPerformance findById(Long id);
    TenisPerformance create(TenisPerformance tenisPerformance, String url);
    void update(Long id, TenisPerformance tenisPerformance, String url);
    void delete(Long id);
}