package geral;
import java.math.BigDecimal;

public class hotel {
    private int id;
    private String nomeHotel;
    private int idDestino;
    private String tipoQuarto;
    private String qtdeQuarto;
    private BigDecimal precoPorNoite;

    // Construtor padrão
    public hotel() {
    }

    // Construtor com parâmetros
    public hotel(String nomeHotel, int idDestino, String tipoQuarto, String qtdeQuarto, BigDecimal precoPorNoite) {
        this.nomeHotel = nomeHotel;
        this.idDestino = idDestino;
        this.tipoQuarto = tipoQuarto;
        this.qtdeQuarto = qtdeQuarto;
        this.precoPorNoite = precoPorNoite;
    }

    // Getters e Setters para todos os atributos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeHotel() {
        return nomeHotel;
    }

    public void setNomeHotel(String nomeHotel) {
        this.nomeHotel = nomeHotel;
    }

    public int getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(int idDestino) {
        this.idDestino = idDestino;
    }

    public String getTipoQuarto() {
        return tipoQuarto;
    }

    public void setTipoQuarto(String tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }

    public String getQtdeQuarto() {
        return qtdeQuarto;
    }

    public void setQtdeQuarto(String qtdeQuarto) {
        this.qtdeQuarto = qtdeQuarto;
    }

    public BigDecimal getPrecoPorNoite() {
        return precoPorNoite;
    }

    public void setPrecoPorNoite(BigDecimal precoPorNoite) {
        this.precoPorNoite = precoPorNoite;
    }
}