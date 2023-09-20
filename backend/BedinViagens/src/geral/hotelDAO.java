package geral;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.util.Scanner;

public class hotelDAO {
    private Connection conexao;

    // Construtor que recebe a conexão
    public hotelDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void gerenciarHotel() throws SQLException {
        int escolhaHotel;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Escolha uma operação para Hotel:");
            System.out.println("1. Criar Hotel");
            System.out.println("2. Ler Hotel por ID");
            System.out.println("3. Atualizar Hotel");
            System.out.println("4. Deletar Hotel");
            System.out.println("5. Listar Hotéis");
            System.out.println("0. Voltar");

            escolhaHotel = scanner.nextInt();

            switch (escolhaHotel) {
                case 1:
                	// Implemente a lógica do CRUD para Hotel aqui
                	try {                        
                    criarHotel();
                    } catch (SQLException e) {
                        System.err.println("Erro ao criar hotéis: " + e.getMessage());
                    }
                    break;
                case 2:
                	// Lógica para ler um hotel por ID
                	try {                		
                    lerHotelPorId();
                    } catch (SQLException e) {
                        System.err.println("Erro ao ler hotéis: " + e.getMessage());
                    }                    
                    break;
                case 3:
                    // Lógica para atualizar um hotel
                	try {
                	atualizarHotel();
                    } catch (SQLException e) {
                        System.err.println("Erro ao atualizar hotéis: " + e.getMessage());
                    }
                    break;
                case 4:
                    // Lógica para deletar um hotel
                	try {
                    deletarHotel();
                	} catch (SQLException e) {
                        System.err.println("Erro ao deletar hotéis: " + e.getMessage());
                    }
                    break;
                case 5:
                    // Lógica para listar hotéis
                	try {
                    listarHoteis();
                	} catch (SQLException e) {
                        System.err.println("Erro ao listar hotéis: " + e.getMessage());
                    }
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while (escolhaHotel != 0);
        scanner.close();
    }

    public void criarHotel() throws SQLException {
        // Lógica para criar um novo hotel
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o nome do hotel:");
        String nomeHotel = scanner.nextLine();

        System.out.println("Informe o ID do destino:");
        int idDestino = scanner.nextInt();

        scanner.nextLine(); // Consumir a quebra de linha após o próximoInt

        System.out.println("Informe o tipo de quarto:");
        String tipoQuarto = scanner.nextLine();

        System.out.println("Informe a quantidade de quartos:");
        String qtdeQuarto = scanner.nextLine();

        System.out.println("Informe o preço por noite:");
        BigDecimal precoPorNoite = scanner.nextBigDecimal();

        String sql = "INSERT INTO Hotel (nome_hotel, id_destino, tipo_quarto, qtde_quarto, preco_por_noite) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, nomeHotel);
            pstmt.setInt(2, idDestino);
            pstmt.setString(3, tipoQuarto);
            pstmt.setString(4, qtdeQuarto);
            pstmt.setBigDecimal(5, precoPorNoite);
            pstmt.executeUpdate();
            System.out.println("Hotel criado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao criar o hotel: " + e.getMessage());
        }
        scanner.close();
    }

    public void lerHotelPorId() throws SQLException {
        // Lógica para ler um hotel por ID
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o ID do hotel que deseja ler:");
        int id = scanner.nextInt();

        String sql = "SELECT * FROM Hotel WHERE id = ?";
        hotel hotel = null;
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    hotel = new hotel();
                    hotel.setId(rs.getInt("id"));
                    hotel.setNomeHotel(rs.getString("nome_hotel"));
                    hotel.setIdDestino(rs.getInt("id_destino"));
                    hotel.setTipoQuarto(rs.getString("tipo_quarto"));
                    hotel.setQtdeQuarto(rs.getString("qtde_quarto"));
                    hotel.setPrecoPorNoite(rs.getBigDecimal("preco_por_noite"));

                    System.out.println("Informações do hotel:");
                    System.out.println("ID: " + hotel.getId());
                    System.out.println("Nome do Hotel: " + hotel.getNomeHotel());
                    System.out.println("ID do Destino: " + hotel.getIdDestino());
                    System.out.println("Tipo de Quarto: " + hotel.getTipoQuarto());
                    System.out.println("Quantidade de Quartos: " + hotel.getQtdeQuarto());
                    System.out.println("Preço por Noite: " + hotel.getPrecoPorNoite());
                } else {
                    System.out.println("Hotel não encontrado com o ID informado.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao ler o hotel: " + e.getMessage());
        }
        scanner.close();
    }

    public void listarHoteis() throws SQLException {
        // Lógica para listar todos os hotéis
        String sql = "SELECT * FROM Hotel";
        List<hotel> hoteis = new ArrayList<>();
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    hotel hotel = new hotel();
                    hotel.setId(rs.getInt("id"));
                    hotel.setNomeHotel(rs.getString("nome_hotel"));
                    hotel.setIdDestino(rs.getInt("id_destino"));
                    hotel.setTipoQuarto(rs.getString("tipo_quarto"));
                    hotel.setQtdeQuarto(rs.getString("qtde_quarto"));
                    hotel.setPrecoPorNoite(rs.getBigDecimal("preco_por_noite"));
                    hoteis.add(hotel);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar os hotéis: " + e.getMessage());
        }

        if (!hoteis.isEmpty()) {
            System.out.println("Lista de Hotéis:");
            for (hotel hotel : hoteis) {
                System.out.println("ID: " + hotel.getId());
                System.out.println("Nome do Hotel: " + hotel.getNomeHotel());
                System.out.println("ID do Destino: " + hotel.getIdDestino());
                System.out.println("Tipo de Quarto: " + hotel.getTipoQuarto());
                System.out.println("Quantidade de Quartos: " + hotel.getQtdeQuarto());
                System.out.println("Preço por Noite: " + hotel.getPrecoPorNoite());
                System.out.println("---------------");
            }
        } else {
            System.out.println("Nenhum hotel encontrado.");
        }
    }

    public void atualizarHotel() throws SQLException {
        // Lógica para atualizar um hotel
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o ID do hotel que deseja atualizar:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Quebra de linha após o próximoInt

        System.out.println("Informe o novo nome do hotel:");
        String nomeHotel = scanner.nextLine();

        System.out.println("Informe o novo ID do destino:");
        int idDestino = scanner.nextInt();
        scanner.nextLine(); // Quebra de linha após o próximoInt

        System.out.println("Informe o novo tipo de quarto:");
        String tipoQuarto = scanner.nextLine();

        System.out.println("Informe a nova quantidade de quartos:");
        String qtdeQuarto = scanner.nextLine();

        System.out.println("Informe o novo preço por noite:");
        BigDecimal precoPorNoite = scanner.nextBigDecimal();

        String sql = "UPDATE Hotel SET nome_hotel=?, id_destino=?, tipo_quarto=?, qtde_quarto=?, preco_por_noite=? WHERE id=?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, nomeHotel);
            pstmt.setInt(2, idDestino);
            pstmt.setString(3, tipoQuarto);
            pstmt.setString(4, qtdeQuarto);
            pstmt.setBigDecimal(5, precoPorNoite);
            pstmt.setInt(6, id);
            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Hotel atualizado com sucesso!");
            } else {
                System.out.println("Nenhum hotel encontrado com o ID informado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o hotel: " + e.getMessage());
        }
        scanner.close();
    }

    public void deletarHotel() throws SQLException {
        // Lógica para deletar um hotel
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o ID do hotel que deseja deletar:");
        int id = scanner.nextInt();

        String sql = "DELETE FROM Hotel WHERE id=?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Hotel deletado com sucesso!");
            } else {
                System.out.println("Nenhum hotel encontrado com o ID informado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar o hotel: " + e.getMessage());
        }
        scanner.close();
    }

    // Método para excluir hotéis relacionados a um destino pelo ID do destino
    public void deletarHoteisDoDestino(int idDestino) throws SQLException {
        String sql = "DELETE FROM Hotel WHERE id_destino = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idDestino);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar os hotéis do destino: " + e.getMessage());
        }
    }
}
