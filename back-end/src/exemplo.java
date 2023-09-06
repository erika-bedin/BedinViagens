import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class exemplo {
    public static void main(String[] args) {
        Connection conexao = null;

        try {
            // Configurar a URL de conexão
            String url = "jdbc:mysql://127.0.0.1:3306/agencia_de_viagens";
            String usuario = "erika";
            String senha = "P@norama76";
            
        try {
            conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão bem-sucedida!");
            
        } catch (SQLException e) {
            System.err.println("Erro na conexão: " + e.getMessage());
        }
        
        // Criar instância do DAO para Cliente e Destino
        clienteDAO clienteDAO = new clienteDAO(conexao);
        destinoDAO destinoDAO = new destinoDAO(conexao);            

        // Verifique se o cliente com ID 1 existe
        cliente clienteExistente = clienteDAO.lerClientePorId(1);
        if (clienteExistente == null) {
            // Se não existir, crie o cliente primeiro
            cliente criarCliente = new cliente("João da Silva", "RG123456", "CPF123456", "2000-01-01", "Endereço", 123, "Bairro", "Cidade", "Estado", "1234567890", true);
            clienteDAO.criarCliente(criarCliente);
            System.out.println("Cliente com ID 1 criado com sucesso!"); 
            // Agora, o cliente com ID 1 deve existir

            // Crie o destino relacionado ao cliente (com ID 1)
            destino criarDestino = new destino(1, "2023-09-10", "10:00:00", "Origem A", "Companhia Partida A", "Voo123", "2023-09-20", "12:00:00", "Destino B", "Companhia Retorno B", "Voo456", 2, 1, "Categoria X");
            destinoDAO.criarDestino(criarDestino);
            System.out.println("Destino relacionado ao Cliente ID 1 criado com sucesso!"); 
        } else {
            System.out.println("Cliente com ID 1 já existe."); 
        }
        
        // Lista de todos os clientes após a inclusão
        List<cliente> clientes = clienteDAO.listarClientes();
        for (cliente cliente : clientes) {
            System.out.println("Cliente: " + cliente.getNome());
        }

        // Lista de todos os destinos após a inclusão
        List<destino> destinos = destinoDAO.listarDestinos();
        for (destino destino : destinos) {
            System.out.println("Destino: " + destino.getDestino());
        }
        
        // Exemplo de leitura de um cliente por ID
        cliente clienteLido = clienteDAO.lerClientePorId(1);
        if (clienteLido != null) {
            System.out.println("Cliente lido: " + clienteLido.getNome());
        } else {
            System.out.println("Cliente não encontrado.");
        }         

        // Exemplo de atualização de um cliente
        clienteLido.setNome("Novo Nome do Cliente");
        clienteDAO.atualizarCliente(clienteLido);

        // Exemplo de exclusão de destinos relacionados ao cliente (com ID 1)
        destinoDAO.deletarDestinosDoCliente(1);

        // Exemplo de exclusão de um cliente
        clienteDAO.deletarCliente(1);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
