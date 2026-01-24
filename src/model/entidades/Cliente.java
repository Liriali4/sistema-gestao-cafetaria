package model.entidades;

public class Cliente {
    private Integer idCliente;
    private String nome;

    public Cliente() {
    }
    
    public Cliente(Integer idCliente, String nome) {
        this.idCliente = idCliente;
        this.nome = nome;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
