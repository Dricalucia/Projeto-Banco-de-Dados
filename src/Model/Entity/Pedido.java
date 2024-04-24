package Model.Entity;

import java.sql.Timestamp;

public class Pedido {
    private String canalSolicitacaoPedido = "site", statusPedido, canalEntrega, formaPagamento;
    private Timestamp dataHoraPedido, dataHoraEntrega, dataHoraSaidaEntrega;
    private Integer idCliente, nmrPedido;
    private Double valorTotalPedido;

    public String getCanalSolicitacaoPedido() {
        return canalSolicitacaoPedido;
    }

    public void setCanalSolicitacaoPedido(String canalSolicitacaoPedido) {
        this.canalSolicitacaoPedido = canalSolicitacaoPedido;
    }

    public String getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(String statusPedido) {
        this.statusPedido = statusPedido;
    }

    public String getCanalEntrega() {
        return canalEntrega;
    }

    public void setCanalEntrega(String canalEntrega) {
        this.canalEntrega = canalEntrega;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Timestamp getDataHoraPedido() {
        return dataHoraPedido;
    }

    public void setDataHoraPedido(Timestamp dataHoraPedido) {
        this.dataHoraPedido = dataHoraPedido;
    }

    public Timestamp getDataHoraEntrega() {
        return dataHoraEntrega;
    }

    public void setDataHoraEntrega(Timestamp dataHoraEntrega) {
        this.dataHoraEntrega = dataHoraEntrega;
    }

    public Timestamp getDataHoraSaidaEntrega() {
        return dataHoraSaidaEntrega;
    }

    public void setDataHoraSaidaEntrega(Timestamp dataHoraSaidaEntrega) {
        this.dataHoraSaidaEntrega = dataHoraSaidaEntrega;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getNmrPedido() {
        return nmrPedido;
    }

    public void setNmrPedido(Integer nmrPedido) {
        this.nmrPedido = nmrPedido;
    }

    public Double getValorTotalPedido() {
        return valorTotalPedido;
    }

    public void setValorTotalPedido(Double valorTotalPedido) {
        this.valorTotalPedido = valorTotalPedido;
    }

}
