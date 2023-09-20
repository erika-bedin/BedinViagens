package geral;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class mensagemDAO {
    private Connection conexao;

    public mensagemDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void gerenciarMensagem() throws SQLException {
        int escolhaMensagem;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Escolha uma operação para Mensagem:");
            System.out.println("1. Criar Mensagem");
            System.out.println("2. Ler Mensagem por ID");
            System.out.println("3. Atualizar Mensagem");
            System.out.println("4. Deletar Mensagem");
            System.out.println("5. Listar Mensagens");
            System.out.println("0. Voltar");

            escolhaMensagem = scanner.nextInt();

            switch (escolhaMensagem) {
                case 1:
                    // Implemente a lógica para criar uma mensagem aqui
                    try {
                        criarMensagem();
                    } catch (SQLException e) {
                        System.err.println("Erro ao criar mensagem: " + e.getMessage());
                    }
                    break;
                case 2:
                    // Lógica para ler uma mensagem por ID
                    try {
                        lerMensagemPorId();
                    } catch (SQLException e) {
                        System.err.println("Erro ao ler mensagem: " + e.getMessage());
                    }
                    break;
                case 3:
                    // Lógica para atualizar uma mensagem
                    try {
                        atualizarMensagem();
                    } catch (SQLException e) {
                        System.err.println("Erro ao atualizar mensagem: " + e.getMessage());
                    }
                    break;
                case 4:
                    // Lógica para deletar uma mensagem
                    try {
                        deletarMensagem();
                    } catch (SQLException e) {
                        System.err.println("Erro ao deletar mensagem: " + e.getMessage());
                    }
                    break;
                case 5:
                    // Lógica para listar mensagens
                    try {
                        listarMensagens();
                    } catch (SQLException e) {
                        System.err.println("Erro ao listar mensagens: " + e.getMessage());
                    }
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while (escolhaMensagem != 0);
        scanner.close();
    }

    public void criarMensagem() throws SQLException {
        // Lógica para criar uma nova mensagem
        Scanner scanner = new Scanner(System.in);

        // Solicite as informações necessárias ao usuário e crie um objeto mensagem

        String sql = "INSERT INTO mensagem (id_cliente, nome_remetente, telefone_remetente, email_remetente, mensagem, preferencia_contato, melhor_periodo_contato, receber_descontos, receber_novidades) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            // Configure os parâmetros do PreparedStatement com os valores do objeto mensagem
            pstmt.executeUpdate();
            System.out.println("Mensagem criada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao criar a mensagem: " + e.getMessage());
        }
        scanner.close();
    }

    public void lerMensagemPorId() throws SQLException {
        // Lógica para ler uma mensagem por ID
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o ID da mensagem que deseja ler:");
        int id = scanner.nextInt();

        String sql = "SELECT * FROM mensagem WHERE id = ?";
        
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Preencha o objeto mensagem com os dados do ResultSet
                    System.out.println("Informações da mensagem:");
                    // Imprima as informações da mensagem
                } else {
                    System.out.println("Mensagem não encontrada com o ID informado.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao ler a mensagem: " + e.getMessage());
        }
        scanner.close();
    }

    public void listarMensagens() throws SQLException {
        // Lógica para listar todas as mensagens
        String sql = "SELECT * FROM mensagem";
        List<mensagem> mensagens = new ArrayList<>();
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // Crie objetos mensagem, preencha-os com os dados do ResultSet e adicione-os à lista
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar as mensagens: " + e.getMessage());
        }

        if (!mensagens.isEmpty()) {
            System.out.println("Lista de Mensagens:");
            for (int i = 0; i < mensagens.size(); i++) {
                // Imprima as informações da mensagem
                System.out.println("---------------");
            }
        } else {
            System.out.println("Nenhuma mensagem encontrada.");
        }
    }

    public void atualizarMensagem() throws SQLException {
        // Lógica para atualizar uma mensagem
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o ID da mensagem que deseja atualizar:");
        int id = scanner.nextInt();

        // Solicite as informações necessárias ao usuário e atualize o objeto mensagem

        String sql = "UPDATE mensagem SET id_cliente=?, nome_remetente=?, telefone_remetente=?, email_remetente=?, mensagem=?, preferencia_contato=?, melhor_periodo_contato=?, receber_descontos=?, receber_novidades=? WHERE id=?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            // Configure os parâmetros do PreparedStatement com os valores atualizados do objeto mensagem
            pstmt.setInt(10, id);
            pstmt.executeUpdate();
            System.out.println("Mensagem atualizada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar a mensagem: " + e.getMessage());
        }
        scanner.close();
    }

    public void deletarMensagem() throws SQLException {
        // Lógica para deletar uma mensagem
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o ID da mensagem que deseja deletar:");
        int id = scanner.nextInt();

        String sql = "DELETE FROM mensagem WHERE id=?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Mensagem deletada com sucesso!");
            } else {
                System.out.println("Nenhuma mensagem encontrada com o ID informado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar a mensagem: " + e.getMessage());
        }
        scanner.close();
    }
}