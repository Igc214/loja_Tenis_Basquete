package igor.projeto.tp1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Fabricante extends DefaultEntity {
    private String marca;
    private String cnpj;

    @ManyToOne
    private Fornecedor fornecedor;

    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public Fornecedor getFornecedor() {
        return fornecedor;
    }
    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    
}
