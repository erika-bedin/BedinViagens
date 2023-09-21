import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import model.Hotel;
import dao.HotelDAO;

public class HotelConsoleApp {
    public static void main(String[] args) {
        // Configuração do banco de dados
        String jdbcURL = "jdbc:mysql://localhost:3306/agencia_de_viagens";
        String username = "erika";
        String password = "Teste2023+";

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            HotelDAO hotelDAO = new HotelDAO(connection);

            Scanner scanner = new Scanner(System.in);

            while (true) {
            	System.out.println ("========== Bedin Viagens ==========");
            	System.out.println ("========== Módulo Hotel ===========");
            	System.out.println("\nEscolha uma opção:");
                System.out.println("1. Adicionar Hotel");
                System.out.println("2. Atualizar Hotel");
                System.out.println("3. Excluir Hotel");
                System.out.println("4. Buscar Hotel por ID");
                System.out.println("5. Listar Todos os Hotéis");
                System.out.println("6. Sair do Módulo Hotel");
                System.out.println ("\n===================================");

                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpa a quebra de linha

                switch (opcao) {
                    case 1:
                        adicionarHotel(hotelDAO, scanner);
                        break;
                    case 2:
                        atualizarHotel(hotelDAO, scanner);
                        break;
                    case 3:
                        excluirHotel(hotelDAO, scanner);
                        break;
                    case 4:
                        buscarHotelPorId(hotelDAO, scanner);
                        break;
                    case 5:
                        listarTodosHoteis(hotelDAO);
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

    private static void adicionarHotel(HotelDAO hotelDAO, Scanner scanner) throws SQLException {
        System.out.print("Nome do Hotel: ");
        String nomeHotel = scanner.nextLine();
        System.out.print("ID do Destino: ");
        int idDestino = scanner.nextInt();
        scanner.nextLine(); // Limpa a quebra de linha
        System.out.print("Tipo de Quarto: ");
        String tipoQuarto = scanner.nextLine();
        System.out.print("Quantidade de Quartos: ");
        String qtdeQuarto = scanner.nextLine();
        System.out.print("Preço por Noite: ");
        double precoPorNoite = scanner.nextDouble();

        Hotel novoHotel = new Hotel();
        novoHotel.setNomeHotel(nomeHotel);
        novoHotel.setIdDestino(idDestino);
        novoHotel.setTipoQuarto(tipoQuarto);
        novoHotel.setQtdeQuarto(qtdeQuarto);
        novoHotel.setPrecoPorNoite(precoPorNoite);

        hotelDAO.adicionarHotel(novoHotel);
        System.out.println("Hotel adicionado com sucesso!");
    }

    private static void atualizarHotel(HotelDAO hotelDAO, Scanner scanner) throws SQLException {
        System.out.print("ID do Hotel que deseja atualizar: ");
        int hotelId = scanner.nextInt();
        scanner.nextLine(); // Limpa a quebra de linha

        Hotel hotelExistente = hotelDAO.buscarHotelPorId(hotelId);

        if (hotelExistente != null) {
            System.out.print("Novo Nome do Hotel: ");
            String novoNomeHotel = scanner.nextLine();
            System.out.print("Novo ID do Destino: ");
            int novoIdDestino = scanner.nextInt();
            scanner.nextLine(); // Limpa a quebra de linha
            System.out.print("Novo Tipo de Quarto: ");
            String novoTipoQuarto = scanner.nextLine();
            System.out.print("Nova Quantidade de Quartos: ");
            String novaQtdeQuarto = scanner.nextLine();
            System.out.print("Novo Preço por Noite: ");
            double novoPrecoPorNoite = scanner.nextDouble();

            hotelExistente.setNomeHotel(novoNomeHotel);
            hotelExistente.setIdDestino(novoIdDestino);
            hotelExistente.setTipoQuarto(novoTipoQuarto);
            hotelExistente.setQtdeQuarto(novaQtdeQuarto);
            hotelExistente.setPrecoPorNoite(novoPrecoPorNoite);

            hotelDAO.atualizarHotel(hotelExistente);
            System.out.println("Hotel atualizado com sucesso!");
        } else {
            System.out.println("Hotel com ID " + hotelId + " não encontrado.");
        }
    }

    private static void excluirHotel(HotelDAO hotelDAO, Scanner scanner) throws SQLException {
        System.out.print("ID do Hotel que deseja excluir: ");
        int hotelId = scanner.nextInt();
        scanner.nextLine(); // Limpa a quebra de linha

        Hotel hotelExistente = hotelDAO.buscarHotelPorId(hotelId);

        if (hotelExistente != null) {
            hotelDAO.excluirHotel(hotelId);
            System.out.println("Hotel excluído com sucesso!");
        } else {
            System.out.println("Hotel com ID " + hotelId + " não encontrado.");
        }
    }

    private static void buscarHotelPorId(HotelDAO hotelDAO, Scanner scanner) throws SQLException {
        System.out.print("ID do Hotel que deseja buscar: ");
        int hotelId = scanner.nextInt();
        scanner.nextLine(); // Limpa a quebra de linha

        Hotel hotelExistente = hotelDAO.buscarHotelPorId(hotelId);

        if (hotelExistente != null) {
            System.out.println("Hotel encontrado:");
            exibirDetalhesHotel(hotelExistente);
        } else {
            System.out.println("Hotel com ID " + hotelId + " não encontrado.");
        }
    }

    private static void listarTodosHoteis(HotelDAO hotelDAO) throws SQLException {
        List<Hotel> hoteis = hotelDAO.listarHoteis();

        if (!hoteis.isEmpty()) {
            System.out.println("Lista de Hotéis:");
            for (Hotel hotel : hoteis) {
                exibirDetalhesHotel(hotel);
            }
        } else {
            System.out.println("Nenhum hotel cadastrado.");
        }
    }

    private static void exibirDetalhesHotel(Hotel hotel) {
        System.out.println("ID: " + hotel.getId());
        System.out.println("Nome do Hotel: " + hotel.getNomeHotel());
        System.out.println("ID do Destino: " + hotel.getIdDestino());
        System.out.println("Tipo de Quarto: " + hotel.getTipoQuarto());
        System.out.println("Quantidade de Quartos: " + hotel.getQtdeQuarto());
        System.out.println("Preço por Noite: " + hotel.getPrecoPorNoite());
    }
}
