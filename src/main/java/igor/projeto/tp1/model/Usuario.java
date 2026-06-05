package igor.projeto.tp1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;

@Entity
public class Usuario extends DefaultEntity {

    @Column(unique = true, nullable = false)
    private String login;

    @Column(nullable = false)
    private String nome;

    private String sobrenome;

    @Column(name = "jogador_favorito")
    private String jogadorFavorito;

    @Column(name = "time_nba")
    private String timeNba;

    @Column(name = "senha_hash", nullable = false)
    private String senhaHash;

    private String endereco;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Perfil perfil;

    @Version
    @Column(nullable = false, columnDefinition = "integer default 0")
    private Integer version = 0;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<ListaDesejo> listaDeDesejos;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getJogadorFavorito() {
        return jogadorFavorito;
    }

    public void setJogadorFavorito(String jogadorFavorito) {
        this.jogadorFavorito = jogadorFavorito;
    }

    public String getTimeNba() {
        return timeNba;
    }

    public void setTimeNba(String timeNba) {
        this.timeNba = timeNba;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public List<ListaDesejo> getListaDeDesejos() {
        return listaDeDesejos;
    }

    public void setListaDeDesejos(List<ListaDesejo> listaDeDesejos) {
        this.listaDeDesejos = listaDeDesejos;
    }
}
