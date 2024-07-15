package entities;

import java.util.HashMap;
import java.util.Map;

public class Abrigo {
    private String nome;
    private String endereco;
    private String responsavel;
    private String telefone;
    private String email;
    private int capacidade;
    private double ocupacao;
    private Map<Class<? extends Item>, Integer> itensRecebidos = new HashMap<>();

    public Abrigo(String nome, String endereco, String responsavel, int capacidade) {
        this.nome = nome;
        this.endereco = endereco;
        this.responsavel = responsavel;
        this.telefone = telefone;
        this.email = email;
        this.capacidade = capacidade;
        this.ocupacao = 0.0;
    }

    // Getters and Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    
 

    // ... (getters e setters para os demais atributos)

    public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public void adicionarItensRecebidos(Item item) {
        itensRecebidos.put(item.getClass(), itensRecebidos.getOrDefault(item.getClass(), 0) + item.getQuantidade());
        atualizarOcupacao();
    }

    private void atualizarOcupacao() {
        int totalItens = itensRecebidos.values().stream().mapToInt(Integer::intValue).sum();
        this.ocupacao = (double) totalItens / capacidade * 100;
    }

    @Override
    public String toString() {
        return "Abrigo{" +
                "nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", responsavel='" + responsavel + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", capacidade=" + capacidade +
                ", ocupacao=" + ocupacao +
                ", itensRecebidos=" + itensRecebidos +
                '}';
    }
}

