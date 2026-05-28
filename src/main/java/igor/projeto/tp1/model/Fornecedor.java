package igor.projeto.tp1.model;

import jakarta.persistence.Entity;

@Entity
public class Fornecedor extends Pessoa {

    private String cnpj;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(final String cnpj) {
        this.cnpj = cnpj;
    }

  

}