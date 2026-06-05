package igor.projeto.tp1.model;

public enum TipoPagamento {
    CARTAO_CREDITO(1L, "Cartao de Credito"),
    CARTAO_DEBITO(2L, "Cartao de Debito"),
    BOLETO(3L, "Boleto"),
    PIX(4L, "Pix");

    private final Long id;
    private final String nome;

    TipoPagamento(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public static TipoPagamento valueOf(Long id) {
        for (TipoPagamento tipo : values()) {
            if (tipo.getId().equals(id)) {
                return tipo;
            }
        }
        return null;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
