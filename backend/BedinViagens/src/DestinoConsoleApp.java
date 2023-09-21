import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import model.Destino;
import dao.DestinoDAO;

public class DestinoConsoleApp {
    public static void main(String[] args) {
        // Configuração do banco de dados
        String jdbcURL = "jdbc:mysql://localhost:3306/agencia_de_viagens";
        String username = "erika";
        String password = "Teste2023+";

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            DestinoDAO destinoDAO = new DestinoDAO(connection);

            Scanner scanner = new Scanner(System.in);

            while (true) {
            	System.out.println ("========== Bedin Viagens ==========");
            	System.out.println ("========= Módulo Destino ==========");
            	System.out.println("\nEscolha uma opção:");
                System.out.println("1. Adicionar Destino");
                System.out.println("2. Atualizar Destino");
                System.out.println("3. Excluir Destino");
                System.out.println("4. Buscar Destino por ID");
                System.out.println("5. Listar Todos os Destinos");
                System.out.println("6. Sair do Módulo Destino");
                System.out.println ("\n===================================");

                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpa a quebra de linha

                switch (opcao) {
                    case 1:
                        adicionarDestino(destinoDAO, scanner);
                        break;
                    case 2:
                        atualizarDestino(destinoDAO, scanner);
                        break;
                    case 3:
                        excluirDestino(destinoDAO, scanner);
                        break;
                    case 4:
                        buscarDestinoPorId(destinoDAO, scanner);
                        break;
                    case 5:
                        listarTodosDestinos(destinoDAO);
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

    private static void adicionarDestino(DestinoDAO destinoDAO, Scanner scanner) throws SQLException {
        System.out.print("Nome do Destino: ");
        String nomeDestino = scanner.nextLine();
        System.out.print("Descrição do Destino: ");
        String descricaoDestino = scanner.nextLine();

        Destino novoDestino = new Destino();
        novoDestino.setNomeDestino(nomeDestino);
        novoDestino.setDescricaoDestino(descricaoDestino);

        destinoDAO.adicionarDestino(novoDestino);
        System.out.println("Destino adicionado com sucesso!");
    }

    private static void atualizarDestino(DestinoDAO destinoDAO, Scanner scanner) throws SQLException {
        System.out.print("ID do Destino que deseja atualizar: ");
        int destinoId = scanner.nextInt();
        scanner.nextLine(); // Limpa a quebra de linha

        Destino destinoExistente = destinoDAO.buscarDestinoPorId(destinoId);

        if (destinoExistente != null) {
            System.out.print("Novo Nome do Destino: ");
            String novoNomeDestino = scanner.nextLine();
            System.out.print("Nova Descrição do Destino: ");
            String novaDescricaoDestino = scanner.nextLine();

            destinoExistente.setNomeDestino(novoNomeDestino);
            destinoExistente.setDescricaoDestino(novaDescricaoDestino);

            destinoDAO.atualizarDestino(destinoExistente);
            System.out.println("Destino atualizado com sucesso!");
        } else {
            System.out.println("Destino com ID " + destinoId + " não encontrado.");
        }
    }

    private static void excluirDestino(DestinoDAO destinoDAO, Scanner scanner) throws SQLException {
        System.out.print("ID do Destino que deseja excluir: ");
        int destinoId = scanner.nextInt();
        scanner.nextLine(); // Limpa a quebra de linha

        Destino destinoExistente = destinoDAO.buscarDestinoPorId(destinoId);

        if (destinoExistente != null) {
            destinoDAO.excluirDestino(destinoId);
            System.out.println("Destino excluído com sucesso!");
        } else {
            System.out.println("Destino com ID " + destinoId + " não encontrado.");
        }
    }

    private static void buscarDestinoPorId(DestinoDAO destinoDAO, Scanner scanner) throws SQLException {
        System.out.print("ID do Destino que deseja buscar: ");
        int destinoId = scanner.nextInt();
        scanner.nextLine(); // Limpa a quebra de linha

        Destino destinoExistente = destinoDAO.buscarDestinoPorId(destinoId);

        if (destinoExistente != null) {
            System.out.println("Destino encontrado:");
            exibirDetalhesDestino(destinoExistente);
        } else {
            System.out.println("Destino com ID " + destinoId + " não encontrado.");
        }
    }

    private static void listarTodosDestinos(DestinoDAO destinoDAO) throws SQLException {
        List<Destino> destinos = destinoDAO.listarDestinos();

        if (!destinos.isEmpty()) {
            System.out.println("Lista de Destinos:");
            for (Destino destino : destinos) {
                exibirDetalhesDestino(destino);
            }
        } else {
            System.out.println("Nenhum destino cadastrado.");
        }
    }

    private static void exibirDetalhesDestino(Destino destino) {
        System.out.println("ID: " + destino.getId());
        System.out.println("Nome do Destino: " + destino.getNomeDestino());
        System.out.println("Descrição do Destino: " + destino.getDescricaoDestino());
    }
}
