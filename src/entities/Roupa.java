package entities;

public class Roupa extends Item {
    private String genero;
    private String tamanho;

    public Roupa(String descricao, int quantidade, String genero, String tamanho) {
        super(descricao, quantidade);
        this.genero = genero;
        this.tamanho = tamanho;
    }

    // Getters and Setters
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    @Override
    public String toString() {
        return "Roupa{" +
                "descricao='" + getDescricao() + '\'' +
                ", quantidade=" + getQuantidade() +
                ", genero='" + genero + '\'' +
                ", tamanho='" + tamanho + '\'' +
                ", dataCadastro=" + getDataCadastro() +
                '}';
    }
}
