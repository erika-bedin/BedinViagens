package geral;
import java.sql.Date;

public class pesquisa {
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

    // Construtor padrão
    public pesquisa() {
    }

    // Construtor com parâmetros
    public pesquisa(int idCliente, int idDestino, Date dataPesquisa, Date dataSimulacao, int qtdPassagens, int qtdNoitesHotel, boolean simularPassagem, boolean simularHotel, boolean clienteExistente) {
        this.idCliente = idCliente;
        this.idDestino = idDestino;
        this.dataPesquisa = dataPesquisa;
        this.dataSimulacao = dataSimulacao;
        this.qtdPassagens = qtdPassagens;
        this.qtdNoitesHotel = qtdNoitesHotel;
        this.simularPassagem = simularPassagem;
        this.simularHotel = simularHotel;
        this.clienteExistente = clienteExistente;
    }

    // Getters e Setters para todos os atributos
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