package Model.Entity;

public class Funcionario {
    private String cpf, nome, funcao, rua, bairro, cidade, uf, lojasCNPJ;
    private Integer matricula, numero;
    private Double salario;
    private char status;
    private java.sql.Date dataAdmissao;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
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

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getLojasCNPJ() {
        return lojasCNPJ;
    }

    public void setLojasCNPJ(String lojasCNPJ) {
        this.lojasCNPJ = lojasCNPJ;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public java.sql.Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(java.sql.Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

}
