package model;

public enum TipoCompra {
    PASSAGEM("Passagem"),
    HOTEL("Hotel"),
    AMBOS("Ambos");

    private final String descricao;

    TipoCompra(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
