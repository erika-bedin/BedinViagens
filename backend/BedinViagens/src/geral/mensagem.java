package geral;
public class mensagem {
    private int id;
    private int idCliente;
    private String nomeRemetente;
    private String telefoneRemetente;
    private String emailRemetente;
    private String mensagem;
    private PreferenciaContato preferenciaContato;
    private MelhorPeriodoContato melhorPeriodoContato;
    private boolean receberDescontos;
    private boolean receberNovidades;
    

    public mensagem() {
    }

    public mensagem(int idCliente, String nomeRemetente, String telefoneRemetente, String emailRemetente, String mensagem, PreferenciaContato preferenciaContato, MelhorPeriodoContato melhorPeriodoContato, boolean receberDescontos, boolean receberNovidades) {
        this.idCliente = idCliente;
        this.nomeRemetente = nomeRemetente;
        this.telefoneRemetente = telefoneRemetente;
        this.emailRemetente = emailRemetente;
        this.mensagem = mensagem;
        this.preferenciaContato = preferenciaContato;
        this.melhorPeriodoContato = melhorPeriodoContato;
        this.receberDescontos = receberDescontos;
        this.receberNovidades = receberNovidades;
    }

    public enum PreferenciaContato {
        TELEFONE, 
        EMAIL, 
        WHATSAPP
    }

    public enum MelhorPeriodoContato {
        MANHA, 
        TARDE, 
        NOITE
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

    public String getNomeRemetente() {
        return nomeRemetente;
    }

    public void setNomeRemetente(String nomeRemetente) {
        this.nomeRemetente = nomeRemetente;
    }

    public String getTelefoneRemetente() {
        return telefoneRemetente;
    }

    public void setTelefoneRemetente(String telefoneRemetente) {
        this.telefoneRemetente = telefoneRemetente;
    }

    public String getEmailRemetente() {
        return emailRemetente;
    }

    public void setEmailRemetente(String emailRemetente) {
        this.emailRemetente = emailRemetente;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public PreferenciaContato getPreferenciaContato() {
        return preferenciaContato;
    }

    public void setPreferenciaContato(PreferenciaContato preferenciaContato) {
        this.preferenciaContato = preferenciaContato;
    }

    public MelhorPeriodoContato getMelhorPeriodoContato() {
        return melhorPeriodoContato;
    }

    public void setMelhorPeriodoContato(MelhorPeriodoContato melhorPeriodoContato) {
        this.melhorPeriodoContato = melhorPeriodoContato;
    }

    public boolean isReceberDescontos() {
        return receberDescontos;
    }

    public void setReceberDescontos(boolean receberDescontos) {
        this.receberDescontos = receberDescontos;
    }

    public boolean isReceberNovidades() {
        return receberNovidades;
    }

    public void setReceberNovidades(boolean receberNovidades) {
        this.receberNovidades = receberNovidades;
    }
}