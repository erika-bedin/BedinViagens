package geral;
import java.math.BigDecimal;
import java.sql.Date;

public class compra {
    private int id;
    private int idCliente;
    private int idDestino;
    private Date dataCompra;
    private TipoCompra tipoCompra;
    private int qtdPassagens;
    private int qtdNoitesHotel;
    private BigDecimal precoTotal;

    // Construtor padrão
    public compra() {
    }

    // Construtor com parâmetros
    public compra(int idCliente, int idDestino, Date dataCompra, TipoCompra tipoCompra, int qtdPassagens, int qtdNoitesHotel, BigDecimal precoTotal) {
        this.idCliente = idCliente;
        this.idDestino = idDestino;
        this.dataCompra = dataCompra;
        this.tipoCompra = tipoCompra;
        this.qtdPassagens = qtdPassagens;
        this.qtdNoitesHotel = qtdNoitesHotel;
        this.precoTotal = precoTotal;
    }

    // Getters e Setters para todos os atributos
    public enum TipoCompra {
        PASSAGEM,
        HOTEL,
        AMBOS
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(int idDestino) {
        this.idDestino = idDestino;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public TipoCompra getTipoCompra() {
        return tipoCompra;
    }

    public void setTipoCompra(TipoCompra tipoCompra) {
        this.tipoCompra = tipoCompra;
    }

    public int getQtdPassagens() {
        return qtdPassagens;
    }

    public void setQtdPassagens(int qtdPassagens) {
        this.qtdPassagens = qtdPassagens;
    }

    public int getQtdNoitesHotel() {
        return qtdNoitesHotel;
    }

    public void setQtdNoitesHotel(int qtdNoitesHotel) {
        this.qtdNoitesHotel = qtdNoitesHotel;
    }

    public BigDecimal getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(BigDecimal precoTotal) {
        this.precoTotal = precoTotal;
    }    
    
}