package igor.projeto.tp1.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Version;

@Entity
public class Pagamento extends DefaultEntity {

    @OneToOne(optional = false)
    @JoinColumn(name = "pedido_id", nullable = false, unique = true)
    private Pedido pedido;

    @Column(nullable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "data_processado")
    private LocalDateTime dataProcessado;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(name = "codigo_tipo_pagamento", nullable = false)
    private TipoPagamento tipoPagamento;

    @Column(name = "codigo_status_pagamento", nullable = false)
    private StatusPagamento statusPagamento;

    @Version
    @Column(nullable = false, columnDefinition = "integer default 0")
    private Integer version = 0;

    @PrePersist
    protected void onCreate() {
        if (dataCadastro == null) {
            dataCadastro = LocalDateTime.now();
        }
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDateTime getDataProcessado() {
        return dataProcessado;
    }

    public void setDataProcessado(LocalDateTime dataProcessado) {
        this.dataProcessado = dataProcessado;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
