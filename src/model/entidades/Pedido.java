package model.entidades;

import java.util.Date;

public class Pedido {

    private String idPedido;
    private String cliente;
    private String funcionario;
    private Date data;
    private Float valorTotal;


    public Pedido(String idPedido, String cliente, String funcionario, Date data, Float valorTotal) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.data = data;
        this.valorTotal = valorTotal;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
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
    
}
