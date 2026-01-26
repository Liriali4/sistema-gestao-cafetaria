package model.entidades;

public class Produto {

    private Integer idProduto;
    private String nome;
    private Float preco;

    public Produto() {
    }

    public Produto(Integer idProduto, String nome, Float preco) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.preco = preco;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
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

    @Override
    public String toString() {
        return idProduto + " - " + nome + " | Preço: " + preco;
    }

}
