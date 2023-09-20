package geral;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.Scanner;

public class passagemDAO {
    private Connection conexao;

    // Construtor que recebe a conexão
    public passagemDAO(Connection conexao) {
        this.conexao = conexao;
    }
    
    public void gerenciarPassagem() throws SQLException {
        int escolhaPassagem;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Escolha uma operação para Passagem:");
            System.out.println("1. Criar Passagem");
            System.out.println("2. Ler Passagem por ID");
            System.out.println("3. Atualizar Passagem");
            System.out.println("4. Deletar Passagem");
            System.out.println("5. Listar Passagens");
            System.out.println("0. Voltar");

            escolhaPassagem = scanner.nextInt();

            switch (escolhaPassagem) {
                case 1:
                	// Implemente a lógica do CRUD para Passagem aqui
                	try {                        
                    criarPassagem();
                    } catch (SQLException e) {
                        System.err.println("Erro ao criar passagens: " + e.getMessage());
                    }
                    break;
                case 2:
                	// Lógica para ler uma passagem por ID
                	try {                		
                    lerPassagemPorId();
                    } catch (SQLException e) {
                        System.err.println("Erro ao ler passagens: " + e.getMessage());
                    }                    
                    break;
                case 3:
                    // Lógica para atualizar uma passagem
                	try {
                	atualizarPassagem();
                    } catch (SQLException e) {
                        System.err.println("Erro ao atualizar passagens: " + e.getMessage());
                    }
                    break;
                case 4:
                    // Lógica para deletar uma passagem
                	try {
                    deletarPassagem();
                	} catch (SQLException e) {
                        System.err.println("Erro ao deletar passagens: " + e.getMessage());
                    }
                    break;
                case 5:
                    // Lógica para listar passagens
                	try {
                    listarPassagens();
                	} catch (SQLException e) {
                        System.err.println("Erro ao listar passagens: " + e.getMessage());
                    }
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while (escolhaPassagem != 0);
        scanner.close();
    }

    public void criarPassagem() throws SQLException {
        // Lógica para criar uma nova passagem
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o ID do destino:");
        int idDestino = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha após o próximoInt

        System.out.println("Informe a companhia aérea de destino:");
        String ciaAereaDestino = scanner.nextLine();

        System.out.println("Informe o voo de destino:");
        String vooDestino = scanner.nextLine();

        System.out.println("Informe a data de destino (AAAA-MM-DD):");
        Date dataDestino = Date.valueOf(scanner.nextLine());

        System.out.println("Informe a hora de destino (HH:MM:SS):");
        Time horaDestino = Time.valueOf(scanner.nextLine());

        System.out.println("Informe a origem:");
        String origem = scanner.nextLine();

        System.out.println("Informe a companhia aérea de origem:");
        String ciaAereaOrigem = scanner.nextLine();

        System.out.println("Informe o voo de origem:");
        String vooOrigem = scanner.nextLine();

        System.out.println("Informe a data de origem (AAAA-MM-DD):");
        Date dataOrigem = Date.valueOf(scanner.nextLine());

        System.out.println("Informe a hora de origem (HH:MM:SS):");
        Time horaOrigem = Time.valueOf(scanner.nextLine());

        System.out.println("Informe o preço da passagem:");
        BigDecimal precoPassagem = scanner.nextBigDecimal();

        String sql = "INSERT INTO Passagem (id_destino, cia_aerea_destino, voo_destino, data_destino, hora_destino, origem, cia_aerea_origem, voo_origem, data_origem, hora_origem, preco_passagem) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, idDestino);
            pstmt.setString(2, ciaAereaDestino);
            pstmt.setString(3, vooDestino);
            pstmt.setDate(4, dataDestino);
            pstmt.setTime(5, horaDestino);
            pstmt.setString(6, origem);
            pstmt.setString(7, ciaAereaOrigem);
            pstmt.setString(8, vooOrigem);
            pstmt.setDate(9, dataOrigem);
            pstmt.setTime(10, horaOrigem);
            pstmt.setBigDecimal(11, precoPassagem);
            pstmt.executeUpdate();
            System.out.println("Passagem criada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao criar a passagem: " + e.getMessage());
        }
        scanner.close();
    }

    public void lerPassagemPorId() throws SQLException {
        // Lógica para ler uma passagem por ID
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o ID da passagem que deseja ler:");
        int id = scanner.nextInt();

        String sql = "SELECT * FROM Passagem WHERE id = ?";
        passagem passagem = null;
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    passagem = new passagem();
                    passagem.setId(rs.getInt("id"));
                    passagem.setIdDestino(rs.getInt("id_destino"));
                    passagem.setCiaAereaDestino(rs.getString("cia_aerea_destino"));
                    passagem.setVooDestino(rs.getString("voo_destino"));
                    passagem.setDataDestino(rs.getDate("data_destino"));
                    passagem.setHoraDestino(rs.getTime("hora_destino"));
                    passagem.setOrigem(rs.getString("origem"));
                    passagem.setCiaAereaOrigem(rs.getString("cia_aerea_origem"));
                    passagem.setVooOrigem(rs.getString("voo_origem"));
                    passagem.setDataOrigem(rs.getDate("data_origem"));
                    passagem.setHoraOrigem(rs.getTime("hora_origem"));
                    passagem.setPrecoPassagem(rs.getBigDecimal("preco_passagem"));

                    System.out.println("Informações da passagem:");
                    System.out.println("ID: " + passagem.getId());
                    System.out.println("ID do Destino: " + passagem.getIdDestino());
                    System.out.println("Companhia Aérea de Destino: " + passagem.getCiaAereaDestino());
                    System.out.println("Voo de Destino: " + passagem.getVooDestino());
                    System.out.println("Data de Destino: " + passagem.getDataDestino());
                    System.out.println("Hora de Destino: " + passagem.getHoraDestino());
                    System.out.println("Origem: " + passagem.getOrigem());
                    System.out.println("Companhia Aérea de Origem: " + passagem.getCiaAereaOrigem());
                    System.out.println("Voo de Origem: " + passagem.getVooOrigem());
                    System.out.println("Data de Origem: " + passagem.getDataOrigem());
                    System.out.println("Hora de Origem: " + passagem.getHoraOrigem());
                    System.out.println("Preço da Passagem: " + passagem.getPrecoPassagem());
                } else {
                    System.out.println("Passagem não encontrada com o ID informado.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao ler a passagem: " + e.getMessage());
        }
        scanner.close();
    }

    public void listarPassagens() throws SQLException {
        // Lógica para listar todas as passagens
        String sql = "SELECT * FROM Passagem";
        List<passagem> passagens = new ArrayList<>();
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    passagem passagem = new passagem();
                    passagem.setId(rs.getInt("id"));
                    passagem.setIdDestino(rs.getInt("id_destino"));
                    passagem.setCiaAereaDestino(rs.getString("cia_aerea_destino"));
                    passagem.setVooDestino(rs.getString("voo_destino"));
                    passagem.setDataDestino(rs.getDate("data_destino"));
                    passagem.setHoraDestino(rs.getTime("hora_destino"));
                    passagem.setOrigem(rs.getString("origem"));
                    passagem.setCiaAereaOrigem(rs.getString("cia_aerea_origem"));
                    passagem.setVooOrigem(rs.getString("voo_origem"));
                    passagem.setDataOrigem(rs.getDate("data_origem"));
                    passagem.setHoraOrigem(rs.getTime("hora_origem"));
                    passagem.setPrecoPassagem(rs.getBigDecimal("preco_passagem"));
                    passagens.add(passagem);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar as passagens: " + e.getMessage());
        }

        if (!passagens.isEmpty()) {
            System.out.println("Lista de Passagens:");
            for (passagem passagem : passagens) {
                System.out.println("ID: " + passagem.getId());
                System.out.println("ID do Destino: " + passagem.getIdDestino());
                System.out.println("Companhia Aérea de Destino: " + passagem.getCiaAereaDestino());
                System.out.println("Voo de Destino: " + passagem.getVooDestino());
                System.out.println("Data de Destino: " + passagem.getDataDestino());
                System.out.println("Hora de Destino: " + passagem.getHoraDestino());
                System.out.println("Origem: " + passagem.getOrigem());
                System.out.println("Companhia Aérea de Origem: " + passagem.getCiaAereaOrigem());
                System.out.println("Voo de Origem: " + passagem.getVooOrigem());
                System.out.println("Data de Origem: " + passagem.getDataOrigem());
                System.out.println("Hora de Origem: " + passagem.getHoraOrigem());
                System.out.println("Preço da Passagem: " + passagem.getPrecoPassagem());
                System.out.println("---------------");
            }
        } else {
            System.out.println("Nenhuma passagem encontrada.");
        }
    }

    public void atualizarPassagem() throws SQLException {
        // Lógica para atualizar uma passagem
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o ID da passagem que deseja atualizar:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Quebra de linha após o próximoInt

        System.out.println("Informe o novo ID do destino:");
        int idDestino = scanner.nextInt();
        scanner.nextLine(); // Quebra de linha após o próximoInt

        System.out.println("Informe a nova companhia aérea de destino:");
        String ciaAereaDestino = scanner.nextLine();

        System.out.println("Informe o novo voo de destino:");
        String vooDestino = scanner.nextLine();

        System.out.println("Informe a nova data de destino (AAAA-MM-DD):");
        Date dataDestino = Date.valueOf(scanner.nextLine());

        System.out.println("Informe a nova hora de destino (HH:MM:SS):");
        Time horaDestino = Time.valueOf(scanner.nextLine());

        System.out.println("Informe a nova origem:");
        String origem = scanner.nextLine();

        System.out.println("Informe a nova companhia aérea de origem:");
        String ciaAereaOrigem = scanner.nextLine();

        System.out.println("Informe o novo voo de origem:");
        String vooOrigem = scanner.nextLine();

        System.out.println("Informe a nova data de origem (AAAA-MM-DD):");
        Date dataOrigem = Date.valueOf(scanner.nextLine());

        System.out.println("Informe a nova hora de origem (HH:MM:SS):");
        Time horaOrigem = Time.valueOf(scanner.nextLine());

        System.out.println("Informe o novo preço da passagem:");
        BigDecimal precoPassagem = scanner.nextBigDecimal();

        String sql = "UPDATE Passagem SET id_destino=?, cia_aerea_destino=?, voo_destino=?, data_destino=?, hora_destino=?, origem=?, cia_aerea_origem=?, voo_origem=?, data_origem=?, hora_origem=?, preco_passagem=? WHERE id=?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, idDestino);
            pstmt.setString(2, ciaAereaDestino);
            pstmt.setString(3, vooDestino);
            pstmt.setDate(4, dataDestino);
            pstmt.setTime(5, horaDestino);
            pstmt.setString(6, origem);
            pstmt.setString(7, ciaAereaOrigem);
            pstmt.setString(8, vooOrigem);
            pstmt.setDate(9, dataOrigem);
            pstmt.setTime(10, horaOrigem);
            pstmt.setBigDecimal(11, precoPassagem);
            pstmt.setInt(12, id);
            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Passagem atualizada com sucesso!");
            } else {
                System.out.println("Nenhuma passagem encontrada com o ID informado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar a passagem: " + e.getMessage());
        }
        scanner.close();
    }

    public void deletarPassagem() throws SQLException {
        // Lógica para deletar uma passagem
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o ID da passagem que deseja deletar:");
        int id = scanner.nextInt();

        String sql = "DELETE FROM Passagem WHERE id=?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Passagem deletada com sucesso!");
            } else {
                System.out.println("Nenhuma passagem encontrada com o ID informado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar a passagem: " + e.getMessage());
        }
        scanner.close();
    }
}