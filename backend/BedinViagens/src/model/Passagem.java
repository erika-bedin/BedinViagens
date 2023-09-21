package model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

public class Passagem {
    private int id;
    private int idDestino;
    private String ciaAereaDestino;
    private String vooDestino;
    private Date dataDestino;
    private Time horaDestino;
    private String origem;
    private String ciaAereaOrigem;
    private String vooOrigem;
    private Date dataOrigem;
    private Time horaOrigem;
    private BigDecimal precoPassagem;

    // Construtor vazio
    public Passagem() {
    }

    // Getters e Setters para todos os campos

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(int idDestino) {
        this.idDestino = idDestino;
    }

    public String getCiaAereaDestino() {
        return ciaAereaDestino;
    }

    public void setCiaAereaDestino(String ciaAereaDestino) {
        this.ciaAereaDestino = ciaAereaDestino;
    }

    public String getVooDestino() {
        return vooDestino;
    }

    public void setVooDestino(String vooDestino) {
        this.vooDestino = vooDestino;
    }

    public Date getDataDestino() {
        return dataDestino;
    }

    public void setDataDestino(Date dataDestino) {
        this.dataDestino = dataDestino;
    }

    public Time getHoraDestino() {
        return horaDestino;
    }

    public void setHoraDestino(Time horaDestino) {
        this.horaDestino = horaDestino;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getCiaAereaOrigem() {
        return ciaAereaOrigem;
    }

    public void setCiaAereaOrigem(String ciaAereaOrigem) {
        this.ciaAereaOrigem = ciaAereaOrigem;
    }

    public String getVooOrigem() {
        return vooOrigem;
    }

    public void setVooOrigem(String vooOrigem) {
        this.vooOrigem = vooOrigem;
    }

    public Date getDataOrigem() {
        return dataOrigem;
    }

    public void setDataOrigem(Date dataOrigem) {
        this.dataOrigem = dataOrigem;
    }

    public Time getHoraOrigem() {
        return horaOrigem;
    }

    public void setHoraOrigem(Time horaOrigem) {
        this.horaOrigem = horaOrigem;
    }

    public BigDecimal getPrecoPassagem() {
        return precoPassagem;
    }

    public void setPrecoPassagem(BigDecimal precoPassagem) {
        this.precoPassagem = precoPassagem;
    }
}
