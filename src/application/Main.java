package application;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import entities.Abrigo;
import entities.Alimento;
import entities.CentroDistribuicao;
import entities.Item;
import entities.OrdemPedido;
import entities.ProdutoHigiene;
import entities.Roupa;

public class Main {
    private static List<CentroDistribuicao> centrosDistribuicao = new ArrayList<>();
    private static List<Abrigo> abrigos = new ArrayList<>();
    private static List<OrdemPedido> ordensPedido = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Inicializa os centros de distribuição (dados do PDF)
        centrosDistribuicao.add(new CentroDistribuicao("Centro de Distribuição Esperança", "Av. Boqueirão, 2450 - Igara, Canoas - RS, 92032-420"));
        centrosDistribuicao.add(new CentroDistribuicao("Centro de Distribuição Prosperidade", "Av. Borges de Medeiros, 1501 – Cidade Baixa, Porto Alegre - RS, 90119-900"));
        centrosDistribuicao.add(new CentroDistribuicao("Centro de Distribuição Reconstrução", "R. Dr. Décio Martins Costa, 312 - Vila Eunice Nova, Cachoeirinha - RS, 94920-170"));

        int opcao;
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            switch (opcao) {
                case 1:
                    cadastrarDoacao();
                    break;
                case 2:
                    cadastrarDoacaoCSV();
                    break;
                case 3:
                    cadastrarAbrigo();
                    break;
                case 4:
                    listarAbrigos();
                    break;
                case 5:
                    criarOrdemPedido();
                    break;
                case 6:
                    gerenciarOrdensPedido();
                    break;
                case 7:
                    transferirDoacoes();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    // Exibir o menu de opções
    private static void exibirMenu() {
        System.out.println("\nSistema de Gestão de Doações para Desabrigados:");
        System.out.println("1. Cadastrar Doação (individual)");
        System.out.println("2. Cadastrar Doação (CSV)");
        System.out.println("3. Cadastrar Abrigo");
        System.out.println("4. Listar Abrigos");
        System.out.println("5. Criar Ordem de Pedido");
        System.out.println("6. Gerenciar Ordens de Pedido");
        System.out.println("7. Transferir Doações");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    // Cadastrar uma doação individualmente
    private static void cadastrarDoacao() {
        System.out.println("\nCadastro de Doação:");
        System.out.print("Tipo de item (1-Roupa, 2-Higiene, 3-Alimento): ");
        int tipoItem = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        Item item = null;
        switch (tipoItem) {
            case 1:
                System.out.print("Gênero (M/F/U): ");
                String genero = scanner.nextLine();
                System.out.print("Tamanho (PP/P/M/G/GG): ");
                String tamanho = scanner.nextLine();
                item = new Roupa(descricao, quantidade, genero, tamanho);
                break;
            case 2:
                System.out.print("Sabonete (S/N): ");
                boolean sabonete = scanner.nextLine().equalsIgnoreCase("S");
                System.out.print("Escova de dentes (S/N): ");
                boolean escovaDentes = scanner.nextLine().equalsIgnoreCase("S");
                System.out.print("Pasta de dentes (S/N): ");
                boolean pastaDentes = scanner.nextLine().equalsIgnoreCase("S");
                System.out.print("Absorventes (S/N): ");
                boolean absorventes = scanner.nextLine().equalsIgnoreCase("S");
                item = new ProdutoHigiene(descricao, quantidade, sabonete, escovaDentes, pastaDentes, absorventes);
                break;
            case 3:
                System.out.print("Unidade de medida: ");
                String unidadeMedida = scanner.nextLine();
                System.out.print("Data de validade (dd/MM/yyyy): ");
                String dataValidadeStr = scanner.nextLine();
                LocalDate dataValidade = LocalDate.parse(dataValidadeStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                item = new Alimento(descricao, quantidade, unidadeMedida, dataValidade);
                break;
            default:
                System.out.println("Tipo de item inválido.");
                return;
        }

        System.out.println("\nCentros de Distribuição disponíveis:");
        for (int i = 0; i < centrosDistribuicao.size(); i++) {
            System.out.println((i + 1) + ". " + centrosDistribuicao.get(i).getNome());
        }
        System.out.print("Escolha o centro de distribuição para receber a doação: ");
        int escolhaCentro = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        if (escolhaCentro >= 1 && escolhaCentro <= centrosDistribuicao.size()) {
            CentroDistribuicao centro = centrosDistribuicao.get(escolhaCentro - 1);
            centro.adicionarItem(item);
            System.out.println("Doação cadastrada com sucesso no centro de distribuição " + centro.getNome() + "!");
        } else {
            System.out.println("Centro de distribuição inválido.");
        }
    }

    // Cadastrar doações em massa a partir de um arquivo CSV
    private static void cadastrarDoacaoCSV() {
        System.out.print("Digite o caminho do arquivo CSV: ");
        String caminhoArquivo = scanner.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length >= 3) { // Verifica se há dados suficientes na linha
                    String tipoItem = dados[0].trim();
                    String descricao = dados[1].trim();
                    int quantidade = Integer.parseInt(dados[2].trim());

                    Item item = null;
                    switch (tipoItem.toLowerCase()) {
                        case "roupa":
                            String genero = dados.length > 3 ? dados[3].trim() : "U";
                            String tamanho = dados.length > 4 ? dados[4].trim() : "M";
                            item = new Roupa(descricao, quantidade, genero, tamanho);
                            break;
                        case "higiene":
                            boolean sabonete = dados.length > 3 && dados[3].trim().equalsIgnoreCase("sim");
                            boolean escovaDentes = dados.length > 4 && dados[4].trim().equalsIgnoreCase("sim");
                            boolean pastaDentes = dados.length > 5 && dados[5].trim().equalsIgnoreCase("sim");
                            boolean absorventes = dados.length > 6 && dados[6].trim().equalsIgnoreCase("sim");
                            item = new ProdutoHigiene(descricao, quantidade, sabonete, escovaDentes, pastaDentes, absorventes);
                            break;
                        case "alimento":
                            String unidadeMedida = dados.length > 3 ? dados[3].trim() : "unidade";
                            LocalDate dataValidade = dados.length > 4 ? LocalDate.parse(dados[4].trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy")) : LocalDate.now().plusMonths(6); 
                            item = new Alimento(descricao, quantidade, unidadeMedida, dataValidade);
                            break;
                        default:
                            System.out.println("Tipo de item inválido na linha: " + linha);
                            continue; // Pula para a próxima linha do CSV
                    }

                    System.out.println("\nCentros de Distribuição disponíveis:");
                    for (int i = 0; i < centrosDistribuicao.size(); i++) {
                        System.out.println((i + 1) + ". " + centrosDistribuicao.get(i).getNome());
                    }
                    System.out.print("Escolha o centro de distribuição para receber a doação: ");
                    int escolhaCentro = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer

                    if (escolhaCentro >= 1 && escolhaCentro <= centrosDistribuicao.size()) {
                        CentroDistribuicao centro = centrosDistribuicao.get(escolhaCentro - 1);
                        centro.adicionarItem(item);
                        System.out.println("Doação cadastrada com sucesso no centro de distribuição " + centro.getNome() + "!");
                    } else {
                        System.out.println("Centro de distribuição inválido.");
                    }
                } else {
                    System.out.println("Dados insuficientes na linha: " + linha);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo CSV: " + e.getMessage());
        }
    }

    // Cadastrar um novo abrigo
    private static void cadastrarAbrigo() {
        System.out.println("\nCadastro de Abrigo:");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Capacidade: ");
        int capacidade = scanner.nextInt();
        System.out.print("Responsável: ");
        String responsavel = scanner.next();
        
        
        scanner.nextLine(); // Limpar o buffer

        Abrigo abrigo = new Abrigo(nome, endereco, responsavel, capacidade);
        abrigos.add(abrigo);
        System.out.println("Abrigo cadastrado com sucesso!");
    }

    // Listar todos os abrigos cadastrados
    private static void listarAbrigos() {
        System.out.println("\nLista de Abrigos:");
        for (Abrigo abrigo : abrigos) {
            System.out.println(abrigo);
        }
    }

    // Criar uma nova ordem de pedido
    private static void criarOrdemPedido() {
        System.out.println("\nCriação de Ordem de Pedido:");
        System.out.print("Nome do abrigo solicitante: ");
        String nomeAbrigo = scanner.nextLine();

        Abrigo abrigo = abrigos.stream().filter(a -> a.getNome().equalsIgnoreCase(nomeAbrigo)).findFirst().orElse(null);
        if (abrigo == null) {
            System.out.println("Abrigo não encontrado.");
            return;
        }

        OrdemPedido ordemPedido = new OrdemPedido(abrigos);
        ordensPedido.add(ordemPedido);
        System.out.println("Ordem de pedido criada com sucesso!");
    }

    // Gerenciar ordens de pedido (visualizar, atualizar status, etc.)
    private static void gerenciarOrdensPedido() {
        if (ordensPedido.isEmpty()) {
            System.out.println("Não há ordens de pedido para gerenciar.");
            return;
        }

        System.out.println("\nGerenciamento de Ordens de Pedido:");
        for (int i = 0; i < ordensPedido.size(); i++) {
            System.out.println((i + 1) + ". " + ordensPedido.get(i));
        }

        System.out.print("Escolha uma ordem de pedido para gerenciar (0 para voltar): ");
        int escolha = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        if (escolha < 1 || escolha > ordensPedido.size()) {
            System.out.println("Opção inválida.");
            return;
        }

        OrdemPedido ordemSelecionada = ordensPedido.get(escolha - 1);

        System.out.println("\n1. Visualizar detalhes da ordem");
        System.out.println("2. Atualizar status da ordem");
        System.out.println("3. Remover ordem");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        switch (opcao) {
            case 1:
                System.out.println(ordemSelecionada);
                break;
            case 2:
                System.out.print("Digite o novo status: ");
                String novoStatus = scanner.nextLine();
                ordemSelecionada.setStatus(novoStatus);
                System.out.println("Status atualizado com sucesso!");
                break;
            case 3:
                ordensPedido.remove(ordemSelecionada);
                System.out.println("Ordem removida com sucesso!");
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    // Transferir doações dos centros de distribuição para os abrigos
    private static void transferirDoacoes() {
        System.out.println("\nTransferência de Doações:");
        for (CentroDistribuicao centro : centrosDistribuicao) {
            System.out.println("Centro de Distribuição: " + centro.getNome());
            for (Item item : centro.getItens()) {
                System.out.println(" - " + item);
            }
        }
       
    }
}