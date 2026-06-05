package igor.projeto.tp1.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
        name = "lista_desejo",
        uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "tenis_performance_id"})
)
public class ListaDesejo extends DefaultEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tenis_performance_id", nullable = false)
    private TenisPerformance tenisPerformance;

    @Column(nullable = false)
    private LocalDateTime adicionadoEm;

    @PrePersist
    protected void onCreate() {
        if (adicionadoEm == null) {
            adicionadoEm = LocalDateTime.now();
        }
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
