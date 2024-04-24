package Model.Entity;

import java.util.List;

public class EstoqueDisponivel {
    private Integer estoqueIdEstoque;
    private List<ItemEstoque> itensDisponiveis;
    private java.sql.Date dataValidade;

    public Integer getEstoqueIdEstoque() {
        return estoqueIdEstoque;
    }

    public void setEstoqueIdEstoque(Integer estoqueIdEstoque) {
        this.estoqueIdEstoque = estoqueIdEstoque;
    }

    public List<ItemEstoque> getItensDisponiveis() {
        return itensDisponiveis;
    }

    public void setItensDisponiveis(List<ItemEstoque> itensDisponiveis) {
        this.itensDisponiveis = itensDisponiveis;
    }

    public java.sql.Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(java.sql.Date dataValidade) {
        this.dataValidade = dataValidade;
    }

}
