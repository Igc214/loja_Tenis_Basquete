package igor.projeto.tp1.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum Posicao {
    PG(1L,"Armador"),
    SG(2L,"Ala-Armador"),
    SF(3L,"Ala"),
    PF(4L,"Ala-Pivô"), 
    C(5L,"Pivô");

    private final Long ID;
    private final String NOME;

    private Posicao(Long id, String nome) {
        this.ID = id;
        this.NOME = nome;
    }

    public Long getId() {
        return ID;
    }

    public String getNome() {
        return NOME;
    }

    public static Posicao valueOf(Long id) {
       if (id == null)
            return null;

        for (Posicao posicao : Posicao.values()) {
            if (posicao.getId().equals(id))
                return posicao;
        }
        return null;
    }

    
    
}
