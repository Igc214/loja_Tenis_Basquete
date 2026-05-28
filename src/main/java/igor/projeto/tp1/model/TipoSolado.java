package igor.projeto.tp1.model;

public enum TipoSolado {
    INDOOR(1L,"Indoor"),
    OUTDOOR(2L,"Outdoor"),
    HIBRIDO(3L,"Híbrido");

    private final Long ID;
    private final String NOME;

    private TipoSolado(Long ID, String NOME) {
        this.ID = ID;
        this.NOME = NOME;
    }

    public Long getID() {
        return ID;
    }

    public String getNOME() {
        return NOME;
    }

    public static TipoSolado valuesOf(Long id) {
        if (id == null) {
            return null;
        }
        for (TipoSolado tipoSolado : TipoSolado.values()) {
            if(tipoSolado.getID().equals(id))
                return tipoSolado;
        }
        return null;
    }
}   
