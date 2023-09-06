public class cliente {
    private int id;
    private String nome;
    private String rg;
    private String cpf;
    private String dataNascimento;
    private String endereco;
    private int numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String celular;
    private boolean whatsapp;

    // Construtor padrão
    public cliente() {
    }

    // Construtor com parâmetros
    public cliente(String nome, String rg, String cpf, String dataNascimento, String endereco, int numero, String bairro, String cidade, String estado, String celular, boolean whatsapp) {
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.celular = celular;
        this.whatsapp = whatsapp;
    }

    // Getters e Setters para todos os atributos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public boolean isWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(boolean whatsapp) {
        this.whatsapp = whatsapp;
    }
}
