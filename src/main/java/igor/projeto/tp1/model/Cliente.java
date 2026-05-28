package igor.projeto.tp1.model;

import jakarta.persistence.Entity;

@Entity
public class Cliente extends Pessoa {

    private String cpf;
    private String email;
    private int numeroDeCompra;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumeroDeCompra() {
        return numeroDeCompra;
    }

    public void setNumeroDeCompra(int numeroDeCompra) {
        this.numeroDeCompra = numeroDeCompra;
    }
}
