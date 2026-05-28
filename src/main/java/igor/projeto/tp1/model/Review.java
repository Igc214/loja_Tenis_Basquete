package igor.projeto.tp1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Review extends DefaultEntity {

    private String url;

    @ManyToOne
    private TenisPerformance tenisPerformance;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public TenisPerformance getTenisPerformance() {
        return tenisPerformance;
    }

    public void setTenisPerformance(TenisPerformance tenisPerformance) {
        this.tenisPerformance = tenisPerformance;
    }

}