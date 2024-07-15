package entities;

import java.util.HashMap;
import java.util.Map;

public class CentroDistribuicao {
    private String nome;
    private String endereco;
    private Map<Class<? extends Item>, Integer> estoque = new HashMap<>();

    public CentroDistribuicao(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }

    // Getters and Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Map<Class<? extends Item>, Integer> getEstoque() {
        return estoque;
    }

    public void adicionarItem(Item item) {
        estoque.put(item.getClass(), estoque.getOrDefault(item.getClass(), 0) + item.getQuantidade());
    }
    public void removerItem(Class<? extends Item> tipoItem, int quantidade) {
        int quantidadeAtual = estoque.getOrDefault(tipoItem, 0);
        if (quantidadeAtual >= quantidade) {
            estoque.put(tipoItem, quantidadeAtual - quantidade); // 
        } else {
            System.out.println("Quantidade insuficiente no estoque.");
        }
    
    }

    public int getQuantidadeItem(Class<? extends Item> tipoItem) {
        return estoque.getOrDefault(tipoItem, 0);
    }

    @Override
    public String toString() {
        return "CentroDistribuicao{" +
                "nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", estoque=" + estoque +
                '}';
    }

	public Item[] getItens() {
		// TODO Auto-generated method stub
		return null;
	}
}
