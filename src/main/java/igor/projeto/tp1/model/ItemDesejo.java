package igor.projeto.tp1.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

@Entity
public class ItemDesejo extends DefaultEntity {

    @ManyToOne(optional = false)
    private Usuario usuario;

    @ManyToOne(optional = false)
    private TenisPerformance tenisPerformance;

    @Column(nullable = false)
    private LocalDateTime adicionadoEm;

    @PrePersist
    protected void onCreate() {
        adicionadoEm = LocalDateTime.now();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TenisPerformance getTenisPerformance() {
        return tenisPerformance;
    }

    public void setTenisPerformance(TenisPerformance tenisPerformance) {
        this.tenisPerformance = tenisPerformance;
    }

    public LocalDateTime getAdicionadoEm() {
        return adicionadoEm;
    }

    public void setAdicionadoEm(LocalDateTime adicionadoEm) {
        this.adicionadoEm = adicionadoEm;
    }
}
