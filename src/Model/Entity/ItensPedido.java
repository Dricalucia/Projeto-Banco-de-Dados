package Model.Entity;

import java.util.List;

public class ItensPedido {
    private Integer pedidoNumeroPedido;
    private List<Item> itens;

    public Integer getPedidoNumeroPedido() {
        return pedidoNumeroPedido;
    }

    public void setPedidoNumeroPedido(Integer pedidoNumeroPedido) {
        this.pedidoNumeroPedido = pedidoNumeroPedido;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

}
