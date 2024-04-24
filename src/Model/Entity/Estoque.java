package Model.Entity;

import java.util.List;

public class Estoque {
    private Integer estoqueIdEstoque;
    private List<ItemEstoque> itensEstoque;

    public Integer getEstoqueIdEstoque() {
        return estoqueIdEstoque;
    }

    public void setEstoqueIdEstoque(Integer estoqueIdEstoque) {
        this.estoqueIdEstoque = estoqueIdEstoque;
    }

    public List<ItemEstoque> getItensEstoque() {
        return itensEstoque;
    }

    public void setItensEstoque(List<ItemEstoque> itensEstoque) {
        this.itensEstoque = itensEstoque;
    }

}
