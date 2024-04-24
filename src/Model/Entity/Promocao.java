package Model.Entity;

public class Promocao {
    private Integer idPromocao, itemIdItem;
    private java.sql.Date dataPromocao, dataValidade;
    private String observacao;
    private Double preco;

    public Integer getIdPromocao() {
        return idPromocao;
    }

    public void setIdPromocao(Integer idPromocao) {
        this.idPromocao = idPromocao;
    }

    public Integer getItemIdItem() {
        return itemIdItem;
    }

    public void setItemIdItem(Integer itemIdItem) {
        this.itemIdItem = itemIdItem;
    }

    public java.sql.Date getDataPromocao() {
        return dataPromocao;
    }

    public void setDataPromocao(java.sql.Date dataPromocao) {
        this.dataPromocao = dataPromocao;
    }

    public java.sql.Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(java.sql.Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

}
