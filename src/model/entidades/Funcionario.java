package model.entidades;

public class Funcionario {

    private Integer idFuncionario;
    private String nome;

    public Funcionario() {
    }

    public Funcionario(Integer idFuncionario, String nome) {
        this.idFuncionario = idFuncionario;
        this.nome = nome;
    }

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return idFuncionario + "- " + nome;
    }

}
