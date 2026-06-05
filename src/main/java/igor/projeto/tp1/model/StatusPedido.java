package igor.projeto.tp1.model;

public enum StatusPedido {
    PENDENTE(1L, "Pendente"),
    PAGO(2L, "Pago"),
    CANCELADO(3L, "Cancelado");

    private final Long id;
    private final String nome;

    StatusPedido(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public static StatusPedido valueOf(Long id) {
        for (StatusPedido status : values()) {
            if (status.getId().equals(id)) {
                return status;
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
