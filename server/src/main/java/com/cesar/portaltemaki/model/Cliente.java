    package com.cesar.portaltemaki.model;

    import java.time.LocalDateTime;

    public class Cliente {
        private int idCliente;
        private String nome;
        private String sobrenome;
        private String rua;
        private int numero;
        private String complemento;
        private String bairro;
        private String cidade;
        private String uf;
        private String telefone;
        private String email;
        private String pontoReferencia;
        private String senha;
        private String observacao;
        private LocalDateTime dataCadastro;

        public Cliente() {
        }

        public Cliente(int idCliente, String nome, String sobrenome, String rua, int numero, String complemento,
                       String bairro, String cidade, String uf, String telefone, String email, String pontoReferencia,
                       String senha, String observacao, LocalDateTime dataCadastro) {
            this.idCliente = idCliente;
            this.nome = nome;
            this.sobrenome = sobrenome;
            this.rua = rua;
            this.numero = numero;
            this.complemento = complemento;
            this.bairro = bairro;
            this.cidade = cidade;
            this.uf = uf;
            this.telefone = telefone;
            this.email = email;
            this.pontoReferencia = pontoReferencia;
            this.senha = senha;
            this.observacao = observacao;
            this.dataCadastro = dataCadastro;
        }

        public int getIdCliente() {
            return idCliente;
        }

        public void setIdCliente(int idCliente) {
            this.idCliente = idCliente;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getSobrenome() {
            return sobrenome;
        }

        public void setSobrenome(String sobrenome) {
            this.sobrenome = sobrenome;
        }

        public String getRua() {
            return rua;
        }

        public void setRua(String rua) {
            this.rua = rua;
        }

        public int getNumero() {
            return numero;
        }

        public void setNumero(int numero) {
            this.numero = numero;
        }

        public String getComplemento() {
            return complemento;
        }

        public void setComplemento(String complemento) {
            this.complemento = complemento;
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

        public String getTelefone() {
            return telefone;
        }

        public void setTelefone(String telefone) {
            this.telefone = telefone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPontoReferencia() {
            return pontoReferencia;
        }

        public void setPontoReferencia(String pontoReferencia) {
            this.pontoReferencia = pontoReferencia;
        }

        public String getSenha() {
            return senha;
        }

        public void setSenha(String senha) {
            this.senha = senha;
        }

        public String getObservacao() {
            return observacao;
        }

        public void setObservacao(String observacao) {
            this.observacao = observacao;
        }

        public LocalDateTime getDataCadastro() {
            return dataCadastro;
        }

        public void setDataCadastro(LocalDateTime dataCadastro) {
            this.dataCadastro = dataCadastro;
        }
    }
