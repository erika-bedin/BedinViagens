package geral;
public class destino {
    private int id;
    private String nomeDestino;
    private String descricaoDestino;

    // Construtor padrão
    public destino() {
    }

    // Construtor com parâmetros
    public destino(String nomeDestino, String descricaoDestino) {
        this.nomeDestino = nomeDestino;
        this.descricaoDestino = descricaoDestino;
    }

    // Getters e Setters para todos os atributos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeDestino() {
        return nomeDestino;
    }

    public void setNomeDestino(String nomeDestino) {
        this.nomeDestino = nomeDestino;
    }

    public String getDescricaoDestino() {
        return descricaoDestino;
    }

    public void setDescricaoDestino(String descricaoDestino) {
        this.descricaoDestino = descricaoDestino;
    }
}