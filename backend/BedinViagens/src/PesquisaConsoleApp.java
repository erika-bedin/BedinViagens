import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import model.Pesquisa;
import dao.PesquisaDAO;

public class PesquisaConsoleApp {
    public static void main(String[] args) {
        // Configuração do banco de dados
        String jdbcURL = "jdbc:mysql://localhost:3306/agencia_de_viagens";
        String username = "erika";
        String password = "Teste2023+";

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            PesquisaDAO pesquisaDAO = new PesquisaDAO(connection);

            Scanner scanner = new Scanner(System.in);

            while (true) {
            	System.out.println ("========== Bedin Viagens ==========");
            	System.out.println ("========= Módulo Pesquisa =========");
            	System.out.println("\nEscolha uma opção:");
                System.out.println("1. Adicionar Pesquisa");
                System.out.println("2. Atualizar Pesquisa");
                System.out.println("3. Excluir Pesquisa");
                System.out.println("4. Buscar Pesquisa por ID");
                System.out.println("5. Listar Todas as Pesquisas");
                System.out.println("6. Sair do Módulo Pesquisa");
                System.out.println ("\n===================================");

                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpa a quebra de linha

                switch (opcao) {
                    case 1:
                        adicionarPesquisa(pesquisaDAO, scanner);
                        break;
                    case 2:
                        atualizarPesquisa(pesquisaDAO, scanner);
                        break;
                    case 3:
                        excluirPesquisa(pesquisaDAO, scanner);
                        break;
                    case 4:
                        buscarPesquisaPorId(pesquisaDAO, scanner);
                        break;
                    case 5:
                        listarTodasPesquisas(pesquisaDAO);
                        break;
                    case 6:
                        System.out.println("Saindo...");
                        return;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void adicionarPesquisa(PesquisaDAO pesquisaDAO, Scanner scanner) throws SQLException {
        System.out.print("ID do Cliente: ");
        int idCliente = scanner.nextInt();
        scanner.nextLine(); // Limpa a quebra de linha
        System.out.print("ID do Destino: ");
        int idDestino = scanner.nextInt();
        scanner.nextLine(); // Limpa a quebra de linha
        System.out.print("Data da Pesquisa (AAAA-MM-DD): ");
        String dataPesquisaStr = scanner.nextLine();
        Date dataPesquisa = Date.valueOf(dataPesquisaStr);
        System.out.print("Data da Simulação (AAAA-MM-DD): ");
        String dataSimulacaoStr = scanner.nextLine();
        Date dataSimulacao = Date.valueOf(dataSimulacaoStr);
        System.out.print("Quantidade de Passagens: ");
        int qtdPassagens = scanner.nextInt();
        scanner.nextLine(); // Limpa a quebra de linha
        System.out.print("Quantidade de Noites de Hotel: ");
        int qtdNoitesHotel = scanner.nextInt();
        scanner.nextLine(); // Limpa a quebra de linha
        System.out.print("Simular Passagem (true/false): ");
        boolean simularPassagem = scanner.nextBoolean();
        scanner.nextLine(); // Limpa a quebra de linha
        System.out.print("Simular Hotel (true/false): ");
        boolean simularHotel = scanner.nextBoolean();
        scanner.nextLine(); // Limpa a quebra de linha
        System.out.print("Cliente Existente (true/false): ");
        boolean clienteExistente = scanner.nextBoolean();
        scanner.nextLine(); // Limpa a quebra de linha

        Pesquisa novaPesquisa = new Pesquisa();
        novaPesquisa.setIdCliente(idCliente);
        novaPesquisa.setIdDestino(idDestino);
        novaPesquisa.setDataPesquisa(dataPesquisa);
        novaPesquisa.setDataSimulacao(dataSimulacao);
        novaPesquisa.setQtdPassagens(qtdPassagens);
        novaPesquisa.setQtdNoitesHotel(qtdNoitesHotel);
        novaPesquisa.setSimularPassagem(simularPassagem);
        novaPesquisa.setSimularHotel(simularHotel);
        novaPesquisa.setClienteExistente(clienteExistente);

        pesquisaDAO.adicionarPesquisa(novaPesquisa);
        System.out.println("Pesquisa adicionada com sucesso!");
    }

    private static void atualizarPesquisa(PesquisaDAO pesquisaDAO, Scanner scanner) throws SQLException {
        System.out.print("ID da Pesquisa que deseja atualizar: ");
        int pesquisaId = scanner.nextInt();
        scanner.nextLine(); // Limpa a quebra de linha

        Pesquisa pesquisaExistente = pesquisaDAO.buscarPesquisaPorId(pesquisaId);

        if (pesquisaExistente != null) {
            System.out.print("Novo ID do Cliente: ");
            int novoIdCliente = scanner.nextInt();
            scanner.nextLine(); // Limpa a quebra de linha
            System.out.print("Novo ID do Destino: ");
            int novoIdDestino = scanner.nextInt();
            scanner.nextLine(); // Limpa a quebra de linha
            System.out.print("Nova Data da Pesquisa (AAAA-MM-DD): ");
            String novaDataPesquisaStr = scanner.nextLine();
            Date novaDataPesquisa = Date.valueOf(novaDataPesquisaStr);
            System.out.print("Nova Data da Simulação (AAAA-MM-DD): ");
            String novaDataSimulacaoStr = scanner.nextLine();
            Date novaDataSimulacao = Date.valueOf(novaDataSimulacaoStr);
            System.out.print("Nova Quantidade de Passagens: ");
            int novaQtdPassagens = scanner.nextInt();
            scanner.nextLine(); // Limpa a quebra de linha
            System.out.print("Nova Quantidade de Noites de Hotel: ");
            int novaQtdNoitesHotel = scanner.nextInt();
            scanner.nextLine(); // Limpa a quebra de linha
            System.out.print("Nova Simulação de Passagem (true/false): ");
            boolean novaSimularPassagem = scanner.nextBoolean();
            scanner.nextLine(); // Limpa a quebra de linha
            System.out.print("Nova Simulação de Hotel (true/false): ");
            boolean novaSimularHotel = scanner.nextBoolean();
            scanner.nextLine(); // Limpa a quebra de linha
            System.out.print("Novo Cliente Existente (true/false): ");
            boolean novoClienteExistente = scanner.nextBoolean();
            scanner.nextLine(); // Limpa a quebra de linha

            pesquisaExistente.setIdCliente(novoIdCliente);
            pesquisaExistente.setIdDestino(novoIdDestino);
            pesquisaExistente.setDataPesquisa(novaDataPesquisa);
            pesquisaExistente.setDataSimulacao(novaDataSimulacao);
            pesquisaExistente.setQtdPassagens(novaQtdPassagens);
            pesquisaExistente.setQtdNoitesHotel(novaQtdNoitesHotel);
            pesquisaExistente.setSimularPassagem(novaSimularPassagem);
            pesquisaExistente.setSimularHotel(novaSimularHotel);
            pesquisaExistente.setClienteExistente(novoClienteExistente);

            pesquisaDAO.atualizarPesquisa(pesquisaExistente);
            System.out.println("Pesquisa atualizada com sucesso!");
        } else {
            System.out.println("Pesquisa não encontrada.");
        }
    }

    private static void excluirPesquisa(PesquisaDAO pesquisaDAO, Scanner scanner) throws SQLException {
        System.out.print("ID da Pesquisa que deseja excluir: ");
        int pesquisaId = scanner.nextInt();
        scanner.nextLine(); // Limpa a quebra de linha

        Pesquisa pesquisaExistente = pesquisaDAO.buscarPesquisaPorId(pesquisaId);

        if (pesquisaExistente != null) {
            pesquisaDAO.excluirPesquisa(pesquisaId);
            System.out.println("Pesquisa excluída com sucesso!");
        } else {
            System.out.println("Pesquisa não encontrada.");
        }
    }

    private static void buscarPesquisaPorId(PesquisaDAO pesquisaDAO, Scanner scanner) throws SQLException {
        System.out.print("ID da Pesquisa que deseja buscar: ");
        int pesquisaId = scanner.nextInt();
        scanner.nextLine(); // Limpa a quebra de linha

        Pesquisa pesquisa = pesquisaDAO.buscarPesquisaPorId(pesquisaId);

        if (pesquisa != null) {
            System.out.println("Detalhes da Pesquisa:");
            System.out.println("ID: " + pesquisa.getId());
            System.out.println("ID do Cliente: " + pesquisa.getIdCliente());
            System.out.println("ID do Destino: " + pesquisa.getIdDestino());
            System.out.println("Data da Pesquisa: " + pesquisa.getDataPesquisa());
            System.out.println("Data da Simulação: " + pesquisa.getDataSimulacao());
            System.out.println("Quantidade de Passagens: " + pesquisa.getQtdPassagens());
            System.out.println("Quantidade de Noites de Hotel: " + pesquisa.getQtdNoitesHotel());
            System.out.println("Simular Passagem: " + pesquisa.isSimularPassagem());
            System.out.println("Simular Hotel: " + pesquisa.isSimularHotel());
            System.out.println("Cliente Existente: " + pesquisa.isClienteExistente());
        } else {
            System.out.println("Pesquisa não encontrada.");
        }
    }

    private static void listarTodasPesquisas(PesquisaDAO pesquisaDAO) throws SQLException {
        List<Pesquisa> pesquisas = pesquisaDAO.listarPesquisas();

        if (!pesquisas.isEmpty()) {
            System.out.println("Lista de Pesquisas:");
            for (Pesquisa pesquisa : pesquisas) {
                System.out.println("ID: " + pesquisa.getId());
                System.out.println("ID do Cliente: " + pesquisa.getIdCliente());
                System.out.println("ID do Destino: " + pesquisa.getIdDestino());
                System.out.println("Data da Pesquisa: " + pesquisa.getDataPesquisa());
                System.out.println("Data da Simulação: " + pesquisa.getDataSimulacao());
                System.out.println("Quantidade de Passagens: " + pesquisa.getQtdPassagens());
                System.out.println("Quantidade de Noites de Hotel: " + pesquisa.getQtdNoitesHotel());
                System.out.println("Simular Passagem: " + pesquisa.isSimularPassagem());
                System.out.println("Simular Hotel: " + pesquisa.isSimularHotel());
                System.out.println("Cliente Existente: " + pesquisa.isClienteExistente());
                System.out.println();
            }
        } else {
            System.out.println("Nenhuma pesquisa encontrada.");
        }
    }
}
