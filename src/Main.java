import javax.swing.JOptionPane;

public class Main {

    private static ListaDupla<Cidade> cidades = new ListaDupla<>();

    public static void main(String[] args) {
        int opcao = -1;
        while (opcao != 0) {
            try {
                String entrada = JOptionPane.showInputDialog(null,
                        "1. Cadastrar cidade\n2. Cadastrar ligação\n3. Listar cidades\n4. Verificar ligação\n5. Entregas no tempo\n0. Sair",
                        "Menu", JOptionPane.QUESTION_MESSAGE);
                if (entrada == null) break;
                opcao = Integer.parseInt(entrada);

                switch (opcao) {
                    case 1 -> cadastrarCidade();
                    case 2 -> cadastrarLigacao();
                    case 3 -> listarCidades();
                    case 4 -> verificarLigacao();
                    case 5 -> entregasDentroDoTempo();
                    case 0 -> JOptionPane.showMessageDialog(null, "Saindo...");
                    default -> JOptionPane.showMessageDialog(null, "Opção inválida.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }
        }
    }

    private static void cadastrarCidade() {
        String nome = JOptionPane.showInputDialog("Nome da cidade:");
        if (nome != null && !nome.trim().isEmpty()) {
            Cidade nova = new Cidade(nome);
            if (cidades.pesquisar(nova) == null) {
                cidades.inserir(nova);
                JOptionPane.showMessageDialog(null, "Cidade cadastrada.");
            } else {
                JOptionPane.showMessageDialog(null, "Cidade já existe.");
            }
        }
    }

    private static void cadastrarLigacao() {
        String origem = JOptionPane.showInputDialog("Cidade de origem:");
        No<Cidade> noOrigem = cidades.pesquisar(new Cidade(origem));
        if (noOrigem == null) {
            JOptionPane.showMessageDialog(null, "Cidade não encontrada.");
            return;
        }

        String destino = JOptionPane.showInputDialog("Cidade de destino:");
        double distancia = Double.parseDouble(JOptionPane.showInputDialog("Distância:"));
        double trafego = Double.parseDouble(JOptionPane.showInputDialog("Fator de tráfego:"));
        int pedagios = Integer.parseInt(JOptionPane.showInputDialog("Nº de pedágios:"));

        Ligacao lig = new Ligacao(destino, distancia, trafego, pedagios);
        noOrigem.getDado().getLigacoes().inserir(lig);
        JOptionPane.showMessageDialog(null, "Ligação cadastrada.");
    }

    private static void listarCidades() {
        StringBuilder sb = new StringBuilder();
        cidades.percorrer(cidade -> {
            sb.append("Cidade: ").append(cidade.getNome()).append("\n");
            sb.append(cidade.getLigacoes().toString()).append("\n");
        });
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private static void verificarLigacao() {
        String origem = JOptionPane.showInputDialog("Cidade de origem:");
        String destino = JOptionPane.showInputDialog("Cidade de destino:");

        No<Cidade> noOrigem = cidades.pesquisar(new Cidade(origem));
        if (noOrigem == null) {
            JOptionPane.showMessageDialog(null, "Cidade de origem não encontrada.");
            return;
        }

        ListaDupla<Ligacao> ligacoes = noOrigem.getDado().getLigacoes();
        No<Ligacao> noLig = ligacoes.pesquisar(new Ligacao(destino, 0, 0, 0));

        if (noLig != null) {
            Ligacao lig = noLig.getDado();
            JOptionPane.showMessageDialog(null, "Tempo estimado: " + lig.getTempoEstimado());
        } else {
            JOptionPane.showMessageDialog(null, "Ligação não encontrada.");
        }
    }

    private static void entregasDentroDoTempo() {
        double tempoLimite = Double.parseDouble(JOptionPane.showInputDialog("Tempo limite:"));
        StringBuilder sb = new StringBuilder();
        cidades.percorrer(cidade -> {
            ListaDupla<Ligacao> ligacoes = cidade.getLigacoes();
            ligacoes.percorrer(ligacao -> {
                if (ligacao.getTempoEstimado() <= tempoLimite) {
                    sb.append("Cidade: ").append(cidade.getNome()).append(" - Ligação: ").append(ligacao.toString()).append("\n");
                }
            });
        });
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(null, sb.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma entrega dentro do tempo limite.");
        }
    }
}