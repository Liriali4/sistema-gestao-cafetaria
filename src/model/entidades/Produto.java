package model.entidades;

public class Produto {
    private String idProduto;
    private String nome;
    private Float preco;

    public Produto(String idProduto, String nome, Float preco) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.preco = preco;
    }

    public String getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(String idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }
    
}
