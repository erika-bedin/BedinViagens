import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import model.Passagem;
import dao.PassagemDAO;


public class PassagemConsoleApp {
    public static void main(String[] args) {
        // Configuração do banco de dados
        String jdbcURL = "jdbc:mysql://localhost:3306/agencia_de_viagens";
        String username = "erika";
        String password = "Teste2023+";

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            PassagemDAO passagemDAO = new PassagemDAO(connection);

            Scanner scanner = new Scanner(System.in);

            while (true) {
            	System.out.println ("========== Bedin Viagens ==========");
            	System.out.println ("========= Módulo Passagem =========");
            	System.out.println("\nEscolha uma opção:");
                System.out.println("1. Adicionar Passagem");
                System.out.println("2. Atualizar Passagem");
                System.out.println("3. Excluir Passagem");
                System.out.println("4. Buscar Passagem por ID");
                System.out.println("5. Listar Todas as Passagens");
                System.out.println("6. Sair do Módulo Passagem");
                System.out.println ("\n===================================");

                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpa a quebra de linha

                switch (opcao) {
                    case 1:
                        adicionarPassagem(passagemDAO, scanner);
                        break;
                    case 2:
                        atualizarPassagem(passagemDAO, scanner);
                        break;
                    case 3:
                        excluirPassagem(passagemDAO, scanner);
                        break;
                    case 4:
                        buscarPassagemPorId(passagemDAO, scanner);
                        break;
                    case 5:
                        listarTodasPassagens(passagemDAO);
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

    private static void adicionarPassagem(PassagemDAO passagemDAO, Scanner scanner) throws SQLException {
        System.out.print("ID do Destino: ");
        int idDestino = scanner.nextInt();
        scanner.nextLine(); // Limpa a quebra de linha
        System.out.print("Companhia Aérea de Destino: ");
        String ciaAereaDestino = scanner.nextLine();
        System.out.print("Número do Voo de Destino: ");
        String vooDestino = scanner.nextLine();
        System.out.print("Data de Destino (AAAA-MM-DD): ");
        String dataDestinoStr = scanner.nextLine();
        java.sql.Date dataDestino = java.sql.Date.valueOf(dataDestinoStr);
        System.out.print("Hora de Destino (HH:MM:SS): ");
        String horaDestinoStr = scanner.nextLine();
        java.sql.Time horaDestino = java.sql.Time.valueOf(horaDestinoStr);
        System.out.print("Origem: ");
        String origem = scanner.nextLine();
        System.out.print("Companhia Aérea de Origem: ");
        String ciaAereaOrigem = scanner.nextLine();
        System.out.print("Número do Voo de Origem: ");
        String vooOrigem = scanner.nextLine();
        System.out.print("Data de Origem (AAAA-MM-DD): ");
        String dataOrigemStr = scanner.nextLine();
        java.sql.Date dataOrigem = java.sql.Date.valueOf(dataOrigemStr);
        System.out.print("Hora de Origem (HH:MM:SS): ");
        String horaOrigemStr = scanner.nextLine();
        java.sql.Time horaOrigem = java.sql.Time.valueOf(horaOrigemStr);
        System.out.print("Preço da Passagem: ");
        java.math.BigDecimal precoPassagem = scanner.nextBigDecimal();

        Passagem novaPassagem = new Passagem();
        novaPassagem.setIdDestino(idDestino);
        novaPassagem.setCiaAereaDestino(ciaAereaDestino);
        novaPassagem.setVooDestino(vooDestino);
        novaPassagem.setDataDestino(dataDestino);
        novaPassagem.setHoraDestino(horaDestino);
        novaPassagem.setOrigem(origem);
        novaPassagem.setCiaAereaOrigem(ciaAereaOrigem);
        novaPassagem.setVooOrigem(vooOrigem);
        novaPassagem.setDataOrigem(dataOrigem);
        novaPassagem.setHoraOrigem(horaOrigem);
        novaPassagem.setPrecoPassagem(precoPassagem);

        passagemDAO.adicionarPassagem(novaPassagem);
        System.out.println("Passagem adicionada com sucesso!");
    }

    private static void atualizarPassagem(PassagemDAO passagemDAO, Scanner scanner) throws SQLException {
        System.out.print("ID da Passagem que deseja atualizar: ");
        int passagemId = scanner.nextInt();
        scanner.nextLine(); // Limpa a quebra de linha

        Passagem passagemExistente = passagemDAO.buscarPassagemPorId(passagemId);

        if (passagemExistente != null) {
            System.out.print("Novo ID do Destino: ");
            int novoIdDestino = scanner.nextInt();
            scanner.nextLine(); // Limpa a quebra de linha
            System.out.print("Nova Companhia Aérea de Destino: ");
            String novoCiaAereaDestino = scanner.nextLine();
            System.out.print("Novo Número do Voo de Destino: ");
            String novoVooDestino = scanner.nextLine();
            System.out.print("Nova Data de Destino (AAAA-MM-DD): ");
            String novaDataDestinoStr = scanner.nextLine();
            java.sql.Date novaDataDestino = java.sql.Date.valueOf(novaDataDestinoStr);
            System.out.print("Nova Hora de Destino (HH:MM:SS): ");
            String novaHoraDestinoStr = scanner.nextLine();
            java.sql.Time novaHoraDestino = java.sql.Time.valueOf(novaHoraDestinoStr);
            System.out.print("Nova Origem: ");
            String novaOrigem = scanner.nextLine();
            System.out.print("Nova Companhia Aérea de Origem: ");
            String novoCiaAereaOrigem = scanner.nextLine();
            System.out.print("Novo Número do Voo de Origem: ");
            String novoVooOrigem = scanner.nextLine();
            System.out.print("Nova Data de Origem (AAAA-MM-DD): ");
            String novaDataOrigemStr = scanner.nextLine();
            java.sql.Date novaDataOrigem = java.sql.Date.valueOf(novaDataOrigemStr);
            System.out.print("Nova Hora de Origem (HH:MM:SS): ");
            String novaHoraOrigemStr = scanner.nextLine();
            java.sql.Time novaHoraOrigem = java.sql.Time.valueOf(novaHoraOrigemStr);
            System.out.print("Novo Preço da Passagem: ");
            java.math.BigDecimal novoPrecoPassagem = scanner.nextBigDecimal();

            passagemExistente.setIdDestino(novoIdDestino);
            passagemExistente.setCiaAereaDestino(novoCiaAereaDestino);
            passagemExistente.setVooDestino(novoVooDestino);
            passagemExistente.setDataDestino(novaDataDestino);
            passagemExistente.setHoraDestino(novaHoraDestino);
            passagemExistente.setOrigem(novaOrigem);
            passagemExistente.setCiaAereaOrigem(novoCiaAereaOrigem);
            passagemExistente.setVooOrigem(novoVooOrigem);
            passagemExistente.setDataOrigem(novaDataOrigem);
            passagemExistente.setHoraOrigem(novaHoraOrigem);
            passagemExistente.setPrecoPassagem(novoPrecoPassagem);

            passagemDAO.atualizarPassagem(passagemExistente);
            System.out.println("Passagem atualizada com sucesso!");
        } else {
            System.out.println("Passagem com ID " + passagemId + " não encontrada.");
        }
    }

    private static void excluirPassagem(PassagemDAO passagemDAO, Scanner scanner) throws SQLException {
        System.out.print("ID da Passagem que deseja excluir: ");
        int passagemId = scanner.nextInt();
        scanner.nextLine(); // Limpa a quebra de linha

        Passagem passagemExistente = passagemDAO.buscarPassagemPorId(passagemId);

        if (passagemExistente != null) {
            passagemDAO.excluirPassagem(passagemId);
            System.out.println("Passagem excluída com sucesso!");
        } else {
            System.out.println("Passagem com ID " + passagemId + " não encontrada.");
        }
    }

    private static void buscarPassagemPorId(PassagemDAO passagemDAO, Scanner scanner) throws SQLException {
        System.out.print("ID da Passagem que deseja buscar: ");
        int passagemId = scanner.nextInt();
        scanner.nextLine(); // Limpa a quebra de linha

        Passagem passagemExistente = passagemDAO.buscarPassagemPorId(passagemId);

        if (passagemExistente != null) {
            System.out.println("Passagem encontrada:");
            exibirDetalhesPassagem(passagemExistente);
        } else {
            System.out.println("Passagem com ID " + passagemId + " não encontrada.");
        }
    }

    private static void listarTodasPassagens(PassagemDAO passagemDAO) throws SQLException {
        List<Passagem> passagens = passagemDAO.listarPassagens();

        if (!passagens.isEmpty()) {
            System.out.println("Lista de Passagens:");
            for (Passagem passagem : passagens) {
                exibirDetalhesPassagem(passagem);
            }
        } else {
            System.out.println("Nenhuma passagem cadastrada.");
        }
    }

    private static void exibirDetalhesPassagem(Passagem passagem) {
        System.out.println("ID: " + passagem.getId());
        System.out.println("ID do Destino: " + passagem.getIdDestino());
        System.out.println("Companhia Aérea de Destino: " + passagem.getCiaAereaDestino());
        System.out.println("Número do Voo de Destino: " + passagem.getVooDestino());
        System.out.println("Data de Destino: " + passagem.getDataDestino());
        System.out.println("Hora de Destino: " + passagem.getHoraDestino());
        System.out.println("Origem: " + passagem.getOrigem());
        System.out.println("Companhia Aérea de Origem: " + passagem.getCiaAereaOrigem());
        System.out.println("Número do Voo de Origem: " + passagem.getVooOrigem());
        System.out.println("Data de Origem: " + passagem.getDataOrigem());
        System.out.println("Hora de Origem: " + passagem.getHoraOrigem());
        System.out.println("Preço da Passagem: " + passagem.getPrecoPassagem());
    }
}
