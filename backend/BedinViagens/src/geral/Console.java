package geral;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Console {
    public static void main(String[] args) {
        Connection conexao = null;
        try {
            // Estabeleça a conexão com o banco de dados
            String url = "jdbc:mysql://127.0.0.1:3306/agencia_de_viagens";
            String usuario = "erika";
            String senha = "P@norama76";
            conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão bem-sucedida!");

            // Crie objetos DAO para cada classe
            hotelDAO hotelDao = new hotelDAO(conexao);
            passagemDAO passagemDao = new passagemDAO(conexao);
            compraDAO compraDao = new compraDAO(conexao);
            pesquisaDAO pesquisaDao = new pesquisaDAO(conexao);
            mensagemDAO mensagemDao = new mensagemDAO(conexao);

            Scanner scanner = new Scanner(System.in);
            int opcao;

            do {
                // Exiba um menu de opções para o usuário
                System.out.println("Escolha uma opção:");
                System.out.println("1. CRUD Hotel");
                System.out.println("2. CRUD Passagem");
                System.out.println("3. CRUD Compra");
                System.out.println("4. CRUD Pesquisa");
                System.out.println("5. CRUD Mensagem");
                System.out.println("0. Sair");
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                    	// Programa do CRUD para Hotel
                        try {                            
                            hotelDao.gerenciarHotel();
                        } catch (SQLException e) {
                            System.err.println("Erro ao acessar CRUD para hotel: " + e.getMessage());
                        }
                        break;
                    case 2:
                    	// Programa do CRUD para Passagem
                        try {                            
                            passagemDao.gerenciarPassagem();
                        } catch (SQLException e) {
                            System.err.println("Erro ao acessar CRUD para passagem: " + e.getMessage());
                        }
                        break;
                    case 3:
                        // Programa do CRUD para Compra
                    	try {
                    		compraDao.gerenciarCompra();
                    	} catch (SQLException e) {
                            System.err.println("Erro ao acessar CRUD para compra: " + e.getMessage());
                        }	
                        break;
                    case 4:
                        // Programa do CRUD para Pesquisa
                    	try {
                    		pesquisaDao.gerenciarPesquisa();
                    	} catch (SQLException e) {
                            System.err.println("Erro ao acessar CRUD para pesquisa: " + e.getMessage());
                    	}
                        break;
                    case 5:
                        // Programa do CRUD para Mensagem
                    	try {
                    		mensagemDao.gerenciarMensagem();
                    	} catch (SQLException e) {
                            System.err.println("Erro ao acessar CRUD para mensagem: " + e.getMessage());
                    	}	
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
            } while (opcao != 0);
            scanner.close();
        } catch (SQLException e) {
            System.err.println("Erro na conexão: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}