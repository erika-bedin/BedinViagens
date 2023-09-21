package model;

import java.util.Date;

public class Compra {
    private int id;
    private int idCliente;
    private int idDestino;
    private Date dataCompra;
    private TipoCompra tipoCompra;
    private int qtdPassagens;
    private int qtdNoitesHotel;
    private double precoTotal;

    // Construtor vazio
    public Compra() {
    }

    // Getters e Setters para todos os campos

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

    public double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(double precoTotal) {
        this.precoTotal = precoTotal;
    }
}
