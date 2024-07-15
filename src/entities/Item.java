package entities;

import java.time.LocalDate;

public class Item {
    private String descricao;
    private int quantidade;
    private LocalDate dataCadastro;

    public Item(String descricao, int quantidade) {
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.dataCadastro = LocalDate.now();
    }

    // Getters and Setters
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    @Override
    public String toString() {
        return "Item{" +
                "descricao='" + descricao + '\'' +
                ", quantidade=" + quantidade +
                ", dataCadastro=" + dataCadastro +
                '}';
    }
}