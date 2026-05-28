package igor.projeto.tp1.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;

@Entity
@Inheritance(strategy = jakarta.persistence.InheritanceType.JOINED)
public abstract class Pessoa extends DefaultEntity {

    public String apelido;
    public String telefone;
    public String endereco;

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getApelido() {
        return apelido;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEndereco() {
        return endereco;
    }

}
