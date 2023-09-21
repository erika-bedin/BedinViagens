import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class BedinViagensConsoleApp {
    public static void main(String[] args) {
        // Configuração do banco de dados
        String jdbcURL = "jdbc:mysql://localhost:3306/agencia_de_viagens";
        String username = "erika";
        String password = "Teste2023+";

        Scanner scanner = new Scanner(System.in);

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            while (true) {
            	System.out.print ("========== Bedin Viagens ==========");
                System.out.println("\nEscolha um módulo:");
                System.out.println("1. Cliente");
                System.out.println("2. Destino");
                System.out.println("3. Passagem");
                System.out.println("4. Hotel");
                System.out.println("5. Compra");
                System.out.println("6. Pesquisa");
                System.out.println("7. Mensagem");
                System.out.println("8. Sair do Programa");
                System.out.println ("\n===================================");

                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpa a quebra de linha

                if (opcao == 8) {
                    System.out.println("Saindo...");
                    break;
                }

                switch (opcao) {
                    case 1:
                        ClienteConsoleApp.main(args);
                        break;
                    case 2:
                        DestinoConsoleApp.main(args);
                        break;
                    case 3:
                        PassagemConsoleApp.main(args);
                        break;
                    case 4:
                        HotelConsoleApp.main(args);
                        break;
                    case 5:
                        CompraConsoleApp.main(args);
                        break;
                    case 6:
                        PesquisaConsoleApp.main(args);
                        break;
                    case 7:
                        MensagemConsoleApp.main(args);
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Feche o Scanner aqui para evitar o aviso de vazamento de recursos
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}
