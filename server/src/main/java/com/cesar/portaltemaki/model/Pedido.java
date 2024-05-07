package com.cesar.portaltemaki.model;

import java.time.LocalDateTime;

public class Pedido {
    private int nrPedido;
    private LocalDateTime dataHoraPedido;
    private LocalDateTime dataHoraPrevistaEntrega;
    private LocalDateTime dataHoraSaidaEntrega;
    private LocalDateTime dataHoraEntrega;
    private String statusPedido;
    private String canalSolicitacaoPedido = "site";
    private String canalEntrega = "domicilio";
    private String formaPagamento = "cartao credito";
    private double valorTotalPedido;

    public Pedido() {

    }
    public Pedido(int nrPedido, LocalDateTime dataHoraPedido, LocalDateTime dataHoraPrevistaEntrega,
                  LocalDateTime dataHoraSaidaEntrega, LocalDateTime dataHoraEntrega, String statusPedido,
                  String canalSolicitacaoPedido, String canalEntrega, String formaPagamento, double valorTotalPedido) {
        this.nrPedido = nrPedido;
        this.dataHoraPedido = dataHoraPedido;
        this.dataHoraPrevistaEntrega = dataHoraPrevistaEntrega;
        this.dataHoraSaidaEntrega = dataHoraSaidaEntrega;
        this.dataHoraEntrega = dataHoraEntrega;
        this.statusPedido = statusPedido;
        this.canalSolicitacaoPedido = canalSolicitacaoPedido;
        this.canalEntrega = canalEntrega;
        this.formaPagamento = formaPagamento;
        this.valorTotalPedido = valorTotalPedido;
    }

    public int getNrPedido() {
        return nrPedido;
    }

    public void setNrPedido(int nrPedido) {
        this.nrPedido = nrPedido;
    }

    public LocalDateTime getDataHoraPedido() {
        return dataHoraPedido;
    }

    public void setDataHoraPedido(LocalDateTime dataHoraPedido) {
        this.dataHoraPedido = dataHoraPedido;
    }

    public LocalDateTime getDataHoraPrevistaEntrega() {
        return dataHoraPrevistaEntrega;
    }

    public void setDataHoraPrevistaEntrega(LocalDateTime dataHoraPrevistaEntrega) {
        this.dataHoraPrevistaEntrega = dataHoraPrevistaEntrega;
    }

    public LocalDateTime getDataHoraSaidaEntrega() {
        return dataHoraSaidaEntrega;
    }

    public void setDataHoraSaidaEntrega(LocalDateTime dataHoraSaidaEntrega) {
        this.dataHoraSaidaEntrega = dataHoraSaidaEntrega;
    }

    public LocalDateTime getDataHoraEntrega() {
        return dataHoraEntrega;
    }

    public void setDataHoraEntrega(LocalDateTime dataHoraEntrega) {
        this.dataHoraEntrega = dataHoraEntrega;
    }

    public String getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(String statusPedido) {
        this.statusPedido = statusPedido;
    }

    public String getCanalSolicitacaoPedido() {
        return canalSolicitacaoPedido;
    }

    public void setCanalSolicitacaoPedido(String canalSolicitacaoPedido) {
        this.canalSolicitacaoPedido = canalSolicitacaoPedido;
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

    public double getValorTotalPedido() {
        return valorTotalPedido;
    }

    public void setValorTotalPedido(double valorTotalPedido) {
        this.valorTotalPedido = valorTotalPedido;
    }
}
