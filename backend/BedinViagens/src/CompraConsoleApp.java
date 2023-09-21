import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import model.Compra;
import model.TipoCompra; 
import dao.CompraDAO;

public class CompraConsoleApp {
    public static void main(String[] args) {
        // Configuração do banco de dados
        String jdbcURL = "jdbc:mysql://localhost:3306/agencia_de_viagens";
        String username = "erika";
        String password = "Teste2023+";

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            CompraDAO compraDAO = new CompraDAO(connection);

            Scanner scanner = new Scanner(System.in);

            while (true) {
            	System.out.println ("========== Bedin Viagens ==========");
            	System.out.println ("========== Módulo Compra ==========");
            	System.out.println("\nEscolha uma opção:");
                System.out.println("1. Adicionar Compra");
                System.out.println("2. Atualizar Compra");
                System.out.println("3. Consultar Compra por ID");
                System.out.println("4. Listar Todas as Compras");
                System.out.println("5. Deletar Compra por ID");
                System.out.println("6. Sair do Módulo Compra");
                System.out.println ("\n===================================");

                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpa a quebra de linha

                switch (opcao) {
                    case 1:
                        adicionarCompra(compraDAO, scanner);
                        break;
                    case 2:
                        atualizarCompra(compraDAO, scanner);
                        break;
                    case 3:
                        consultarCompraPorId(compraDAO, scanner);
                        break;
                    case 4:
                        listarTodasCompras(compraDAO);
                        break;
                    case 5:
                        deletarCompraPorId(compraDAO, scanner);
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

    private static void adicionarCompra(CompraDAO compraDAO, Scanner scanner) throws SQLException {
        Compra novaCompra = criarCompra(scanner);
        compraDAO.adicionarCompra(novaCompra);
        System.out.println("Compra adicionada com sucesso!");
    }

    private static void atualizarCompra(CompraDAO compraDAO, Scanner scanner) throws SQLException {
        System.out.print("Digite o ID da Compra que deseja atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpa a quebra de linha

        Compra compraExistente = compraDAO.consultarCompraPorId(id);

        if (compraExistente != null) {
            Compra compraAtualizada = criarCompra(scanner);
            compraAtualizada.setId(id);
            compraDAO.atualizarCompra(compraAtualizada);
            System.out.println("Compra atualizada com sucesso!");
        } else {
            System.out.println("Compra com ID " + id + " não encontrada.");
        }
    }

    private static void consultarCompraPorId(CompraDAO compraDAO, Scanner scanner) throws SQLException {
        System.out.print("Digite o ID da Compra que deseja consultar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpa a quebra de linha

        Compra compra = compraDAO.consultarCompraPorId(id);

        if (compra != null) {
            exibirDetalhesCompra(compra);
        } else {
            System.out.println("Compra com ID " + id + " não encontrada.");
        }
    }

    private static void listarTodasCompras(CompraDAO compraDAO) throws SQLException {
        List<Compra> compras = compraDAO.listarCompras();

        if (!compras.isEmpty()) {
            System.out.println("Lista de Compras:");
            for (Compra compra : compras) {
                exibirDetalhesCompra(compra);
            }
        } else {
            System.out.println("Nenhuma compra cadastrada.");
        }
    }

    private static void deletarCompraPorId(CompraDAO compraDAO, Scanner scanner) throws SQLException {
        System.out.print("Digite o ID da Compra que deseja deletar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpa a quebra de linha

        Compra compraExistente = compraDAO.consultarCompraPorId(id);

        if (compraExistente != null) {
            compraDAO.deletarCompra(id);
            System.out.println("Compra com ID " + id + " deletada com sucesso!");
        } else {
            System.out.println("Compra com ID " + id + " não encontrada.");
        }
    }

    private static Compra criarCompra(Scanner scanner) {
        Compra compra = new Compra();
        System.out.print("ID do Cliente: ");
        compra.setIdCliente(scanner.nextInt());
        scanner.nextLine(); // Limpa a quebra de linha
        System.out.print("ID do Destino: ");
        compra.setIdDestino(scanner.nextInt());
        scanner.nextLine(); // Limpa a quebra de linha
        System.out.print("Data da Compra (AAAA-MM-DD): ");
        String dataCompraStr = scanner.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date dataCompra = dateFormat.parse(dataCompraStr);
            compra.setDataCompra(dataCompra);
        } catch (ParseException e) {
            System.out.println("Formato de data inválido. Use o formato AAAA-MM-DD.");
        }
        
        System.out.print("Tipo de Compra: ('passagem', 'hotel', 'ambos') ");
        String tipoCompraStr = scanner.nextLine();
        try {
            TipoCompra tipoCompra = TipoCompra.valueOf(tipoCompraStr.toUpperCase()); // Converte para maiúsculas e usa o valueOf para obter o enum
            compra.setTipoCompra(tipoCompra);
        } catch (IllegalArgumentException e) {
            System.out.println("Tipo de compra inválido. Use 'passagem', 'hotel' ou 'ambos'.");
            // Lide com o erro de tipo de compra inválido de acordo com a lógica do seu aplicativo
        }
        System.out.print("Quantidade de Passagens: ");
        compra.setQtdPassagens(scanner.nextInt());
        scanner.nextLine(); // Limpa a quebra de linha
        System.out.print("Quantidade de Noites no Hotel: ");
        compra.setQtdNoitesHotel(scanner.nextInt());
        scanner.nextLine(); // Limpa a quebra de linha
        System.out.print("Preço Total: ");
        compra.setPrecoTotal(scanner.nextDouble());
        scanner.nextLine(); // Limpa a quebra de linha

        return compra;
    }

    private static void exibirDetalhesCompra(Compra compra) {
        System.out.println("ID: " + compra.getId());
        System.out.println("ID do Cliente: " + compra.getIdCliente());
        System.out.println("ID do Destino: " + compra.getIdDestino());
        System.out.println("Data da Compra: " + compra.getDataCompra());
        System.out.println("Tipo de Compra: " + compra.getTipoCompra());
        System.out.println("Quantidade de Passagens: " + compra.getQtdPassagens());
        System.out.println("Quantidade de Noites no Hotel: " + compra.getQtdNoitesHotel());
        System.out.println("Preço Total: " + compra.getPrecoTotal());
    }
}
