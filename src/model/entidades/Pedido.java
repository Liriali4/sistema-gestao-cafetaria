package model.entidades;

import java.util.Date;

public class Pedido {

    private Integer idPedido;
    private Integer cliente;
    private Integer funcionario;
    private Date data;
    private Float valorTotal;

    public Pedido() {
    }

    public Pedido(Integer idPedido, Integer cliente, Integer funcionario, Date data, Float valorTotal) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.data = data;
        this.valorTotal = valorTotal;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public Integer getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Integer funcionario) {
        this.funcionario = funcionario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Float valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "Pedido{" + "idPedido=" + idPedido + ", cliente=" + cliente + ", funcionario=" + funcionario + ", data=" + data + ", valorTotal=" + valorTotal + '}';
    }
}
