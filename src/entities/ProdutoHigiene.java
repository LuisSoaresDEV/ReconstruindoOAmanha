package entities;

public class ProdutoHigiene extends Item {
    private boolean sabonete;
    private boolean escovaDentes;
    private boolean pastaDentes;
    private boolean absorventes;

    public ProdutoHigiene(String descricao, int quantidade, boolean sabonete,
                          boolean escovaDentes, boolean pastaDentes, boolean absorventes) {
        super(descricao, quantidade);
        this.sabonete = sabonete;
        this.escovaDentes = escovaDentes;
        this.pastaDentes = pastaDentes;
        this.absorventes = absorventes;
    }

    // Getters and Setters
    public boolean isSabonete() {
        return sabonete;
    }

    public void setSabonete(boolean sabonete) {
        this.sabonete = sabonete;
    }

    public boolean isEscovaDentes() {
        return escovaDentes;
    }

    public void setEscovaDentes(boolean escovaDentes) {
        this.escovaDentes = escovaDentes;
    }

    public boolean isPastaDentes() {
        return pastaDentes;
    }

    public void setPastaDentes(boolean pastaDentes) {
        this.pastaDentes = pastaDentes;
    }

    public boolean isAbsorventes() {
        return absorventes;
    }

    public void setAbsorventes(boolean absorventes) {
        this.absorventes = absorventes;
    }

    @Override
    public String toString() {
        return "ProdutoHigiene{" +
                "descricao='" + getDescricao() + '\'' +
                ", quantidade=" + getQuantidade() +
                ", sabonete=" + sabonete +
                ", escovaDentes=" + escovaDentes +
                ", pastaDentes=" + pastaDentes +
                ", absorventes=" + absorventes +
                ", dataCadastro=" + getDataCadastro() +
                '}';
    }
}
