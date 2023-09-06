public class destino {
    private int id;
    private int idCliente;
    private String dataPartida;
    private String horaPartida;
    private String origem;
    private String companhiaAereaPartida;
    private String numVooPartida;
    private String dataRetorno;
    private String horaRetorno;
    private String destino;
    private String companhiaAereaRetorno;
    private String numVooRetorno;
    private int qtdPessoas;
    private int qtdQuartos;
    private String categoriaQuartos;

    // Construtor padrão
    public destino() {
    }

    // Construtor com parâmetros
    public destino(int idCliente, String dataPartida, String horaPartida, String origem, String companhiaAereaPartida, String numVooPartida, String dataRetorno, String horaRetorno, String destino, String companhiaAereaRetorno, String numVooRetorno, int qtdPessoas, int qtdQuartos, String categoriaQuartos) {
        this.idCliente = idCliente;
        this.dataPartida = dataPartida;
        this.horaPartida = horaPartida;
        this.origem = origem;
        this.companhiaAereaPartida = companhiaAereaPartida;
        this.numVooPartida = numVooPartida;
        this.dataRetorno = dataRetorno;
        this.horaRetorno = horaRetorno;
        this.destino = destino;
        this.companhiaAereaRetorno = companhiaAereaRetorno;
        this.numVooRetorno = numVooRetorno;
        this.qtdPessoas = qtdPessoas;
        this.qtdQuartos = qtdQuartos;
        this.categoriaQuartos = categoriaQuartos;
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

    public String getDataPartida() {
        return dataPartida;
    }

    public void setDataPartida(String dataPartida) {
        this.dataPartida = dataPartida;
    }

    public String getHoraPartida() {
        return horaPartida;
    }

    public void setHoraPartida(String horaPartida) {
        this.horaPartida = horaPartida;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getCompanhiaAereaPartida() {
        return companhiaAereaPartida;
    }

    public void setCompanhiaAereaPartida(String companhiaAereaPartida) {
        this.companhiaAereaPartida = companhiaAereaPartida;
    }

    public String getNumVooPartida() {
        return numVooPartida;
    }

    public void setNumVooPartida(String numVooPartida) {
        this.numVooPartida = numVooPartida;
    }

    public String getDataRetorno() {
        return dataRetorno;
    }

    public void setDataRetorno(String dataRetorno) {
        this.dataRetorno = dataRetorno;
    }

    public String getHoraRetorno() {
        return horaRetorno;
    }

    public void setHoraRetorno(String horaRetorno) {
        this.horaRetorno = horaRetorno;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getCompanhiaAereaRetorno() {
        return companhiaAereaRetorno;
    }

    public void setCompanhiaAereaRetorno(String companhiaAereaRetorno) {
        this.companhiaAereaRetorno = companhiaAereaRetorno;
    }

    public String getNumVooRetorno() {
        return numVooRetorno;
    }

    public void setNumVooRetorno(String numVooRetorno) {
        this.numVooRetorno = numVooRetorno;
    }

    public int getQtdPessoas() {
        return qtdPessoas;
    }

    public void setQtdPessoas(int qtdPessoas) {
        this.qtdPessoas = qtdPessoas;
    }

    public int getQtdQuartos() {
        return qtdQuartos;
    }

    public void setQtdQuartos(int qtdQuartos) {
        this.qtdQuartos = qtdQuartos;
    }

    public String getCategoriaQuartos() {
        return categoriaQuartos;
    }

    public void setCategoriaQuartos(String categoriaQuartos) {
        this.categoriaQuartos = categoriaQuartos;
    }
}
