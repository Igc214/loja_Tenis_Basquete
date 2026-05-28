package igor.projeto.tp1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.CascadeType;
;
@Entity
@Table(name = "tenis_performance") // Especifica o nome da tabela no banco de dados
public class TenisPerformance extends DefaultEntity {

    private String nome;
    private int numeroDoPe;
    private String cor;
    private String descricao;
    private Double preco;
    private Integer estoque;
    private int edicaoLimitada;
    private boolean autografado;

    @Column(name = "codigo_posicao")
    private Posicao posicao;

    @Column(name = "codigo_TipoSolado")
    private TipoSolado tipoSolado;

    @ManyToOne
    private Fabricante fabricante;

    private String url;

    @OneToOne(mappedBy = "tenisPerformance", cascade = CascadeType.ALL, orphanRemoval = true)
    private Review review;

    public int getNumeroDoPe() {
        return numeroDoPe;
    }

    public void setNumeroDoPe(int numeroDoPe) {
        this.numeroDoPe = numeroDoPe;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }

    public TipoSolado getTipoSolado() {
        return tipoSolado;
    }

    public void setTipoSolado(TipoSolado tipoSolado) {
        this.tipoSolado = tipoSolado;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getEdicaoLimitada() {
        return edicaoLimitada;
    }

    public void setEdicaoLimitada(int edicaoLimitada) {
        this.edicaoLimitada = edicaoLimitada;
    }

    public boolean isAutografado() {
        return autografado;
    }

    public void setAutografado(boolean autografado) {
        this.autografado = autografado;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

}