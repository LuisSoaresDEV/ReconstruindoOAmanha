package entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrdemPedido {
    private Abrigo abrigo;
    private CentroDistribuicao centroDistribuicao;
    private Map<Class<? extends Item>, Integer> itensSolicitados = new HashMap<>();
    private boolean aceito;
    private String motivoRecusa;

    public OrdemPedido(Abrigo abrigo, CentroDistribuicao centroDistribuicao) {
        this.abrigo = abrigo;
        this.centroDistribuicao = centroDistribuicao;
    }

    public OrdemPedido(List<Abrigo> abrigos) {
		// TODO Auto-generated constructor stub
	}

	// Getters and Setters
    public Abrigo getAbrigo() {
        return abrigo;
    }

    public void setAbrigo(Abrigo abrigo) {
        this.abrigo = abrigo;
    }

    public CentroDistribuicao getCentroDistribuicao() {
        return centroDistribuicao;
    }

    public void setCentroDistribuicao(CentroDistribuicao centroDistribuicao) {
        this.centroDistribuicao = centroDistribuicao;
    }

    public Map<Class<? extends Item>, Integer> getItensSolicitados() {
        return itensSolicitados;
    }

    public boolean isAceito() {
        return aceito;
    }

    public String getMotivoRecusa() {
        return motivoRecusa;
    }

    // Método para adicionar um item à ordem de pedido
    public void adicionarItem(Class<? extends Item> tipoItem, int quantidade) {
        itensSolicitados.put(tipoItem, quantidade);
    }

    // Método para aceitar o pedido e transferir os itens
    public boolean aceitarPedido() {
        for (Map.Entry<Class<? extends Item>, Integer> entry : itensSolicitados.entrySet()) {
            Class<? extends Item> tipoItem = entry.getKey();
            int quantidade = entry.getValue();

            if (centroDistribuicao.getQuantidadeItem(tipoItem) >= quantidade) {
                centroDistribuicao.removerItem(tipoItem, quantidade);
                try {
                    Item item = tipoItem.getDeclaredConstructor(String.class, int.class).newInstance("Doação Transferida", quantidade);
                    abrigo.adicionarItensRecebidos(item);
                } catch (Exception e) {
                    System.err.println("Erro ao criar item para transferência: " + e.getMessage());
                    return false; // Falha na transferência
                }
            } else {
                return false; // Estoque insuficiente para algum item
            }
        }
        this.aceito = true;
        return true; // Transferência concluída com sucesso
    }

    public void recusarPedido(String motivoRecusa) {
        this.aceito = false;
        this.motivoRecusa = motivoRecusa;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ordem de Pedido:\n");
        sb.append("Abrigo: ").append(abrigo.getNome()).append("\n");
        sb.append("Centro de Distribuição: ").append(centroDistribuicao.getNome()).append("\n");
        sb.append("Itens Solicitados:\n");
        itensSolicitados.forEach((tipoItem, quantidade) -> {
            sb.append("- ").append(tipoItem.getSimpleName()).append(": ").append(quantidade).append("\n");
        });
        sb.append("Status: ").append(aceito ? "Aceito" : "Recusado").append("\n");
        if (!aceito) {
            sb.append("Motivo da Recusa: ").append(motivoRecusa).append("\n");
        }
        return sb.toString();
    }

	public void setStatus(String novoStatus) {
		// TODO Auto-generated method stub
		
	}

	public void setItensSolicitados(Map<Class<? extends Item>, Integer> itensSolicitados) {
		this.itensSolicitados = itensSolicitados;
	}

	public void setAceito(boolean aceito) {
		this.aceito = aceito;
	}

	public void setMotivoRecusa(String motivoRecusa) {
		this.motivoRecusa = motivoRecusa;
	}

	public void setStatus1(String novoStatus) {
		// TODO Auto-generated method stub
		
	}}

	