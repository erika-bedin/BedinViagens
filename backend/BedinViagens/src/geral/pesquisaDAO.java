package geral;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.util.Scanner;

public class pesquisaDAO {
    private Connection conexao;

    // Construtor que recebe a conexão
    public pesquisaDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void gerenciarPesquisa() throws SQLException {
        int escolhaPesquisa;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Escolha uma operação para Pesquisa:");
            System.out.println("1. Criar Pesquisa");
            System.out.println("2. Ler Pesquisa por ID");
            System.out.println("3. Atualizar Pesquisa");
            System.out.println("4. Deletar Pesquisa");
            System.out.println("5. Listar Pesquisas");
            System.out.println("0. Voltar");

            escolhaPesquisa = scanner.nextInt();

            switch (escolhaPesquisa) {
                case 1:
                    // Implemente a lógica do CRUD para Pesquisa aqui
                    try {
                        criarPesquisa();
                    } catch (SQLException e) {
                        System.err.println("Erro ao criar pesquisa: " + e.getMessage());
                    }
                    break;
                case 2:
                    // Lógica para ler uma pesquisa por ID
                    try {
                        lerPesquisaPorId();
                    } catch (SQLException e) {
                        System.err.println("Erro ao ler pesquisa: " + e.getMessage());
                    }
                    break;
                case 3:
                    // Lógica para atualizar uma pesquisa
                    try {
                        atualizarPesquisa();
                    } catch (SQLException e) {
                        System.err.println("Erro ao atualizar pesquisa: " + e.getMessage());
                    }
                    break;
                case 4:
                    // Lógica para deletar uma pesquisa
                    try {
                        deletarPesquisa();
                    } catch (SQLException e) {
                        System.err.println("Erro ao deletar pesquisa: " + e.getMessage());
                    }
                    break;
                case 5:
                    // Lógica para listar pesquisas
                    try {
                        listarPesquisas();
                    } catch (SQLException e) {
                        System.err.println("Erro ao listar pesquisas: " + e.getMessage());
                    }
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while (escolhaPesquisa != 0);
        scanner.close();
    }

    public void criarPesquisa() throws SQLException {
        // Lógica para criar uma nova pesquisa
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o ID do cliente:");
        int idCliente = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha após o próximoInt

        System.out.println("Informe o ID do destino:");
        int idDestino = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha após o próximoInt

        System.out.println("Informe a data da pesquisa (AAAA-MM-DD):");
        Date dataPesquisa = Date.valueOf(scanner.nextLine());

        System.out.println("Informe a data da simulação (AAAA-MM-DD):");
        Date dataSimulacao = Date.valueOf(scanner.nextLine());

        System.out.println("Informe a quantidade de passagens:");
        int qtdPassagens = scanner.nextInt();

        System.out.println("Informe a quantidade de noites de hotel:");
        int qtdNoitesHotel = scanner.nextInt();

        System.out.println("Simular passagem (true/false):");
        boolean simularPassagem = scanner.nextBoolean();

        System.out.println("Simular hotel (true/false):");
        boolean simularHotel = scanner.nextBoolean();

        System.out.println("Cliente existente (true/false):");
        boolean clienteExistente = scanner.nextBoolean();

        String sql = "INSERT INTO Pesquisa (id_cliente, id_destino, data_pesquisa, data_simulacao, qtd_passagens, qtd_noites_hotel, simular_passagem, simular_hotel, cliente_existente) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, idCliente);
            pstmt.setInt(2, idDestino);
            pstmt.setDate(3, dataPesquisa);
            pstmt.setDate(4, dataSimulacao);
            pstmt.setInt(5, qtdPassagens);
            pstmt.setInt(6, qtdNoitesHotel);
            pstmt.setBoolean(7, simularPassagem);
            pstmt.setBoolean(8, simularHotel);
            pstmt.setBoolean(9, clienteExistente);
            pstmt.executeUpdate();
            System.out.println("Pesquisa criada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao criar a pesquisa: " + e.getMessage());
        }
        scanner.close();
    }

    public void lerPesquisaPorId() throws SQLException {
        // Lógica para ler uma pesquisa por ID
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o ID da pesquisa que deseja ler:");
        int id = scanner.nextInt();

        String sql = "SELECT * FROM Pesquisa WHERE id = ?";
        pesquisa pesquisa = null;
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    pesquisa = new pesquisa();
                    pesquisa.setId(rs.getInt("id"));
                    pesquisa.setIdCliente(rs.getInt("id_cliente"));
                    pesquisa.setIdDestino(rs.getInt("id_destino"));
                    pesquisa.setDataPesquisa(rs.getDate("data_pesquisa"));
                    pesquisa.setDataSimulacao(rs.getDate("data_simulacao"));
                    pesquisa.setQtdPassagens(rs.getInt("qtd_passagens"));
                    pesquisa.setQtdNoitesHotel(rs.getInt("qtd_noites_hotel"));
                    pesquisa.setSimularPassagem(rs.getBoolean("simular_passagem"));
                    pesquisa.setSimularHotel(rs.getBoolean("simular_hotel"));
                    pesquisa.setClienteExistente(rs.getBoolean("cliente_existente"));

                    System.out.println("Informações da pesquisa:");
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
                    System.out.println("Pesquisa não encontrada com o ID informado.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao ler a pesquisa: " + e.getMessage());
        }
        scanner.close();
    }

    public void listarPesquisas() throws SQLException {
        // Lógica para listar todas as pesquisas
        String sql = "SELECT * FROM Pesquisa";
        List<pesquisa> pesquisas = new ArrayList<>();
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    pesquisa pesquisa = new pesquisa();
                    pesquisa.setId(rs.getInt("id"));
                    pesquisa.setIdCliente(rs.getInt("id_cliente"));
                    pesquisa.setIdDestino(rs.getInt("id_destino"));
                    pesquisa.setDataPesquisa(rs.getDate("data_pesquisa"));
                    pesquisa.setDataSimulacao(rs.getDate("data_simulacao"));
                    pesquisa.setQtdPassagens(rs.getInt("qtd_passagens"));
                    pesquisa.setQtdNoitesHotel(rs.getInt("qtd_noites_hotel"));
                    pesquisa.setSimularPassagem(rs.getBoolean("simular_passagem"));
                    pesquisa.setSimularHotel(rs.getBoolean("simular_hotel"));
                    pesquisa.setClienteExistente(rs.getBoolean("cliente_existente"));
                    pesquisas.add(pesquisa);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar as pesquisas: " + e.getMessage());
        }

        if (!pesquisas.isEmpty()) {
            System.out.println("Lista de Pesquisas:");
            for (pesquisa pesquisa : pesquisas) {
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
                System.out.println("---------------");
            }
        } else {
            System.out.println("Nenhuma pesquisa encontrada.");
        }
    }

    public void atualizarPesquisa() throws SQLException {
        // Lógica para atualizar uma pesquisa
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o ID da pesquisa que deseja atualizar:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Quebra de linha após o próximoInt

        System.out.println("Informe o novo ID do cliente:");
        int idCliente = scanner.nextInt();
        scanner.nextLine(); // Quebra de linha após o próximoInt

        System.out.println("Informe o novo ID do destino:");
        int idDestino = scanner.nextInt();
        scanner.nextLine(); // Quebra de linha após o próximoInt

        System.out.println("Informe a nova data da pesquisa (AAAA-MM-DD):");
        Date dataPesquisa = Date.valueOf(scanner.nextLine());

        System.out.println("Informe a nova data da simulação (AAAA-MM-DD):");
        Date dataSimulacao = Date.valueOf(scanner.nextLine());

        System.out.println("Informe a nova quantidade de passagens:");
        int qtdPassagens = scanner.nextInt();

        System.out.println("Informe a nova quantidade de noites de hotel:");
        int qtdNoitesHotel = scanner.nextInt();

        System.out.println("Simular passagem (true/false):");
        boolean simularPassagem = scanner.nextBoolean();

        System.out.println("Simular hotel (true/false):");
        boolean simularHotel = scanner.nextBoolean();

        System.out.println("Cliente existente (true/false):");
        boolean clienteExistente = scanner.nextBoolean();

        String sql = "UPDATE Pesquisa SET id_cliente=?, id_destino=?, data_pesquisa=?, data_simulacao=?, qtd_passagens=?, qtd_noites_hotel=?, simular_passagem=?, simular_hotel=?, cliente_existente=? WHERE id=?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, idCliente);
            pstmt.setInt(2, idDestino);
            pstmt.setDate(3, dataPesquisa);
            pstmt.setDate(4, dataSimulacao);
            pstmt.setInt(5, qtdPassagens);
            pstmt.setInt(6, qtdNoitesHotel);
            pstmt.setBoolean(7, simularPassagem);
            pstmt.setBoolean(8, simularHotel);
            pstmt.setBoolean(9, clienteExistente);
            pstmt.setInt(10, id);
            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Pesquisa atualizada com sucesso!");
            } else {
                System.out.println("Nenhuma pesquisa encontrada com o ID informado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar a pesquisa: " + e.getMessage());
        }
        scanner.close();
    }

    public void deletarPesquisa() throws SQLException {
        // Lógica para deletar uma pesquisa
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o ID da pesquisa que deseja deletar:");
        int id = scanner.nextInt();

        String sql = "DELETE FROM Pesquisa WHERE id=?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Pesquisa deletada com sucesso!");
            } else {
                System.out.println("Nenhuma pesquisa encontrada com o ID informado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar a pesquisa: " + e.getMessage());
        }
        scanner.close();
    }
}