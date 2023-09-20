package geral;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.math.BigDecimal;
import java.sql.Date;
import geral.compra.TipoCompra;

public class compraDAO {
    private Connection conexao;

    // Construtor que recebe a conexão
    public compraDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void gerenciarCompra() throws SQLException {
        int escolhaCompra;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Escolha uma operação para Compra:");
            System.out.println("1. Criar Compra");
            System.out.println("2. Ler Compra por ID");
            System.out.println("3. Atualizar Compra");
            System.out.println("4. Deletar Compra");
            System.out.println("5. Listar Compras");
            System.out.println("0. Voltar");

            escolhaCompra = scanner.nextInt();

            switch (escolhaCompra) {
                case 1:
                    try {
                        criarCompra();
                    } catch (SQLException e) {
                        System.err.println("Erro ao criar compra: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        lerCompraPorId();
                    } catch (SQLException e) {
                        System.err.println("Erro ao ler compra: " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        atualizarCompra();
                    } catch (SQLException e) {
                        System.err.println("Erro ao atualizar compra: " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        deletarCompra();
                    } catch (SQLException e) {
                        System.err.println("Erro ao deletar compra: " + e.getMessage());
                    }
                    break;
                case 5:
                    try {
                        listarCompras();
                    } catch (SQLException e) {
                        System.err.println("Erro ao listar compras: " + e.getMessage());
                    }
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while (escolhaCompra != 0);
        scanner.close();
    }

    public void criarCompra() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o ID do cliente:");
        int idCliente = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha após o próximoInt

        System.out.println("Informe o ID do destino:");
        int idDestino = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha após o próximoInt

        System.out.println("Informe a data da compra (AAAA-MM-DD):");
        Date dataCompra = Date.valueOf(scanner.nextLine());

        System.out.println("Informe o tipo da compra:");
        String tipoCompraStr = scanner.nextLine();
        TipoCompra tipoCompra = TipoCompra.valueOf(tipoCompraStr); // Converte a String para enumeração

        System.out.println("Informe a quantidade de passagens:");
        int qtdPassagens = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha após o próximoInt

        System.out.println("Informe a quantidade de noites de hotel:");
        int qtdNoitesHotel = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha após o próximoInt

        System.out.println("Informe o preço total:");
        BigDecimal precoTotal = scanner.nextBigDecimal();

        String sql = "INSERT INTO compra (id_cliente, id_destino, data_compra, tipo_compra, qtd_passagens, qtd_noites_hotel, preco_total) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, idCliente);
            pstmt.setInt(2, idDestino);
            pstmt.setDate(3, dataCompra);
            pstmt.setString(4, tipoCompra.toString()); // Converte enum para string
            pstmt.setInt(5, qtdPassagens);
            pstmt.setInt(6, qtdNoitesHotel);
            pstmt.setBigDecimal(7, precoTotal);
            pstmt.executeUpdate();
            System.out.println("Compra criada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao criar a compra: " + e.getMessage());
        }
        scanner.close();
    }

    public void lerCompraPorId() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o ID da compra que deseja ler:");
        int id = scanner.nextInt();

        String sql = "SELECT * FROM compra WHERE id = ?";
        compra compra = null;
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    compra = new compra();
                    compra.setId(rs.getInt("id"));
                    compra.setIdCliente(rs.getInt("id_cliente"));
                    compra.setIdDestino(rs.getInt("id_destino"));
                    compra.setDataCompra(rs.getDate("data_compra"));
                    compra.setTipoCompra(TipoCompra.valueOf(rs.getString("tipo_compra"))); // Converte a String para enumeração
                    compra.setQtdPassagens(rs.getInt("qtd_passagens"));
                    compra.setQtdNoitesHotel(rs.getInt("qtd_noites_hotel"));
                    compra.setPrecoTotal(rs.getBigDecimal("preco_total"));

                    System.out.println("Informações da compra:");
                    System.out.println("ID: " + compra.getId());
                    System.out.println("ID do Cliente: " + compra.getIdCliente());
                    System.out.println("ID do Destino: " + compra.getIdDestino());
                    System.out.println("Data da Compra: " + compra.getDataCompra());
                    System.out.println("Tipo da Compra: " + compra.getTipoCompra());
                    System.out.println("Quantidade de Passagens: " + compra.getQtdPassagens());
                    System.out.println("Quantidade de Noites de Hotel: " + compra.getQtdNoitesHotel());
                    System.out.println("Preço Total: " + compra.getPrecoTotal());
                } else {
                    System.out.println("Compra não encontrada com o ID informado.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao ler a compra: " + e.getMessage());
        }
        scanner.close();
    }

    public void listarCompras() throws SQLException {
        String sql = "SELECT * FROM compra";
        List<compra> compras = new ArrayList<>();
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    compra compra = new compra();
                    compra.setId(rs.getInt("id"));
                    compra.setIdCliente(rs.getInt("id_cliente"));
                    compra.setIdDestino(rs.getInt("id_destino"));
                    compra.setDataCompra(rs.getDate("data_compra"));
                    compra.setTipoCompra(TipoCompra.valueOf(rs.getString("tipo_compra"))); // Converte a String para enumeração
                    compra.setQtdPassagens(rs.getInt("qtd_passagens"));
                    compra.setQtdNoitesHotel(rs.getInt("qtd_noites_hotel"));
                    compra.setPrecoTotal(rs.getBigDecimal("preco_total"));
                    compras.add(compra);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar as compras: " + e.getMessage());
        }

        if (!compras.isEmpty()) {
            System.out.println("Lista de Compras:");
            for (compra compra : compras) {
                System.out.println("ID: " + compra.getId());
                System.out.println("ID do Cliente: " + compra.getIdCliente());
                System.out.println("ID do Destino: " + compra.getIdDestino());
                System.out.println("Data da Compra: " + compra.getDataCompra());
                System.out.println("Tipo da Compra: " + compra.getTipoCompra());
                System.out.println("Quantidade de Passagens: " + compra.getQtdPassagens());
                System.out.println("Quantidade de Noites de Hotel: " + compra.getQtdNoitesHotel());
                System.out.println("Preço Total: " + compra.getPrecoTotal());
                System.out.println("---------------");
            }
        } else {
            System.out.println("Nenhuma compra encontrada.");
        }
    }

    public void atualizarCompra() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o ID da compra que deseja atualizar:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Quebra de linha após o próximoInt

        System.out.println("Informe o novo ID do cliente:");
        int idCliente = scanner.nextInt();
        scanner.nextLine(); // Quebra de linha após o próximoInt

        System.out.println("Informe o novo ID do destino:");
        int idDestino = scanner.nextInt();
        scanner.nextLine(); // Quebra de linha após o próximoInt

        System.out.println("Informe a nova data da compra (AAAA-MM-DD):");
        Date dataCompra = Date.valueOf(scanner.nextLine());

        System.out.println("Informe o novo tipo da compra:");
        String tipoCompraStr = scanner.nextLine();
        TipoCompra tipoCompra = TipoCompra.valueOf(tipoCompraStr); // Converte a String para enumeração

        System.out.println("Informe a nova quantidade de passagens:");
        int qtdPassagens = scanner.nextInt();
        scanner.nextLine(); // Quebra de linha após o próximoInt

        System.out.println("Informe a nova quantidade de noites de hotel:");
        int qtdNoitesHotel = scanner.nextInt();
        scanner.nextLine(); // Quebra de linha após o próximoInt

        System.out.println("Informe o novo preço total:");
        BigDecimal precoTotal = scanner.nextBigDecimal();

        String sql = "UPDATE compra SET id_cliente=?, id_destino=?, data_compra=?, tipo_compra=?, qtd_passagens=?, qtd_noites_hotel=?, preco_total=? WHERE id=?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, idCliente);
            pstmt.setInt(2, idDestino);
            pstmt.setDate(3, dataCompra);
            pstmt.setString(4, tipoCompra.toString()); // Converte enum para string
            pstmt.setInt(5, qtdPassagens);
            pstmt.setInt(6, qtdNoitesHotel);
            pstmt.setBigDecimal(7, precoTotal);
            pstmt.setInt(8, id);
            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Compra atualizada com sucesso!");
            } else {
                System.out.println("Nenhuma compra encontrada com o ID informado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar a compra: " + e.getMessage());
        }
        scanner.close();
    }

    public void deletarCompra() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o ID da compra que deseja deletar:");
        int id = scanner.nextInt();

        String sql = "DELETE FROM compra WHERE id=?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Compra deletada com sucesso!");
            } else {
                System.out.println("Nenhuma compra encontrada com o ID informado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar a compra: " + e.getMessage());
        }
        scanner.close();
    }
}