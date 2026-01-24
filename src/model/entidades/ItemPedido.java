package model.entidades;

public class ItemPedido {
    private Integer produto;
    private Integer pedido;
    private Integer quantidade;
    private Float valor;

    public ItemPedido() {
    }

    public ItemPedido(Integer produto, Integer pedido, Integer quantidade, Float valor) {
        this.produto = produto;
        this.pedido = pedido;
        this.quantidade = quantidade;
        this.valor = valor;
    }
    
    public Integer getProduto() {
        return produto;
    }

    public void setProduto(Integer produto) {
        this.produto = produto;
    }

    public Integer getPedido() {
        return pedido;
    }

    public void setPedido(Integer pedido) {
        this.pedido = pedido;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }
    
}
