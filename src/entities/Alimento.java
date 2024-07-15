package entities;

import java.time.LocalDate;

public class Alimento extends Item {
    private String unidadeMedida;
    private LocalDate validade;

    public Alimento(String descricao, int quantidade, String unidadeMedida, LocalDate validade) {
        super(descricao, quantidade);
        this.unidadeMedida = unidadeMedida;
        this.validade = validade;
    }

    // Getters and Setters
    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    @Override
    public String toString() {
        return "Alimento{" +
                "descricao='" + getDescricao() + '\'' +
                ", quantidade=" + getQuantidade() +
                ", unidadeMedida='" + unidadeMedida + '\'' +
                ", validade=" + validade +
                ", dataCadastro=" + getDataCadastro() +
                '}';
    }
}
