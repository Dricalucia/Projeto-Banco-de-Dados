package Model.Entity;

public class ItemEstoque {
    private Integer itemIdItem;
    private Integer qtdEstoque;
    private java.sql.Date dtValidade;

    public Integer getItemIdItem() {
        return itemIdItem;
    }

    public void setItemIdItem(Integer itemIdItem) {
        this.itemIdItem = itemIdItem;
    }

    public Integer getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(Integer qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public java.sql.Date getDtValidade() {
        return dtValidade;
    }

    public void setDtValidade(java.sql.Date dtValidade) {
        this.dtValidade = dtValidade;
    }
}