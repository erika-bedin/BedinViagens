package model;

import java.util.Date;

public class Pesquisa {
    private int id;
    private int idCliente;
    private int idDestino;
    private Date dataPesquisa;
    private Date dataSimulacao;
    private int qtdPassagens;
    private int qtdNoitesHotel;
    private boolean simularPassagem;
    private boolean simularHotel;
    private boolean clienteExistente;

    // Construtor vazio
    public Pesquisa() {
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

    public Date getDataPesquisa() {
        return dataPesquisa;
    }

    public void setDataPesquisa(Date dataPesquisa) {
        this.dataPesquisa = dataPesquisa;
    }

    public Date getDataSimulacao() {
        return dataSimulacao;
    }

    public void setDataSimulacao(Date dataSimulacao) {
        this.dataSimulacao = dataSimulacao;
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

    public boolean isSimularPassagem() {
        return simularPassagem;
    }

    public void setSimularPassagem(boolean simularPassagem) {
        this.simularPassagem = simularPassagem;
    }

    public boolean isSimularHotel() {
        return simularHotel;
    }

    public void setSimularHotel(boolean simularHotel) {
        this.simularHotel = simularHotel;
    }

    public boolean isClienteExistente() {
        return clienteExistente;
    }

    public void setClienteExistente(boolean clienteExistente) {
        this.clienteExistente = clienteExistente;
    }
}
