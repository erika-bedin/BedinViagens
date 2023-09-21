import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import model.Cliente;
import dao.ClienteDAO;

public class ClienteConsoleApp {
    public static void main(String[] args) {
        // Configuração do banco de dados
        String jdbcURL = "jdbc:mysql://localhost:3306/agencia_de_viagens";
        String username = "erika";
        String password = "Teste2023+";

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            ClienteDAO clienteDAO = new ClienteDAO(connection);

            Scanner scanner = new Scanner(System.in);

            while (true) {
            	System.out.println ("========== Bedin Viagens ==========");
            	System.out.println ("========= Módulo Cliente ==========");
            	System.out.println("\nEscolha uma opção:");
                System.out.println("1. Adicionar Cliente");
                System.out.println("2. Atualizar Cliente");
                System.out.println("3. Excluir Cliente");
                System.out.println("4. Buscar Cliente por ID");
                System.out.println("5. Listar Todos os Clientes");
                System.out.println("6. Sair do Módulo Cliente");
                System.out.println ("\n===================================");

                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpa a quebra de linha

                switch (opcao) {
                    case 1:
                        adicionarCliente(clienteDAO, scanner);
                        break;
                    case 2:
                        atualizarCliente(clienteDAO, scanner);
                        break;
                    case 3:
                        excluirCliente(clienteDAO, scanner);
                        break;
                    case 4:
                        buscarClientePorId(clienteDAO, scanner);
                        break;
                    case 5:
                        listarTodosClientes(clienteDAO);
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

    private static void adicionarCliente(ClienteDAO clienteDAO, Scanner scanner) throws SQLException {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("RG: ");
        String rg = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Data de Nascimento (YYYY-MM-DD): ");
        String dataNascimentoStr = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Número: ");
        int numero = scanner.nextInt();
        scanner.nextLine(); // Limpa a quebra de linha
        System.out.print("Bairro: ");
        String bairro = scanner.nextLine();
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("Estado: ");
        String estado = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Celular: ");
        String celular = scanner.nextLine();
        System.out.print("WhatsApp (true/false): ");
        boolean whatsapp = scanner.nextBoolean();

        Cliente novoCliente = new Cliente();
        novoCliente.setNome(nome);
        novoCliente.setRg(rg);
        novoCliente.setCpf(cpf);
        novoCliente.setDataNascimento(java.sql.Date.valueOf(dataNascimentoStr));
        novoCliente.setEndereco(endereco);
        novoCliente.setNumero(numero);
        novoCliente.setBairro(bairro);
        novoCliente.setCidade(cidade);
        novoCliente.setEstado(estado);
        novoCliente.setEmail(email);
        novoCliente.setCelular(celular);
        novoCliente.setWhatsapp(whatsapp);

        clienteDAO.adicionarCliente(novoCliente);
        System.out.println("Cliente adicionado com sucesso!");
    }

    private static void atualizarCliente(ClienteDAO clienteDAO, Scanner scanner) throws SQLException {
        System.out.print("ID do Cliente que deseja atualizar: ");
        int clienteId = scanner.nextInt();
        scanner.nextLine(); // Limpa a quebra de linha

        Cliente clienteExistente = clienteDAO.buscarClientePorId(clienteId);

        if (clienteExistente != null) {
            System.out.print("Novo Nome: ");
            String novoNome = scanner.nextLine();
            System.out.print("Novo RG: ");
            String novoRg = scanner.nextLine();
            System.out.print("Novo CPF: ");
            String novoCpf = scanner.nextLine();
            System.out.print("Nova Data de Nascimento (YYYY-MM-DD): ");
            String novaDataNascimentoStr = scanner.nextLine();
            System.out.print("Novo Endereço: ");
            String novoEndereco = scanner.nextLine();
            System.out.print("Novo Número: ");
            int novoNumero = scanner.nextInt();
            scanner.nextLine(); // Limpa a quebra de linha
            System.out.print("Novo Bairro: ");
            String novoBairro = scanner.nextLine();
            System.out.print("Nova Cidade: ");
            String novaCidade = scanner.nextLine();
            System.out.print("Novo Estado: ");
            String novoEstado = scanner.nextLine();
            System.out.print("Novo Email: ");
            String novoEmail = scanner.nextLine();
            System.out.print("Novo Celular: ");
            String novoCelular = scanner.nextLine();
            System.out.print("Novo WhatsApp (true/false): ");
            boolean novoWhatsapp = scanner.nextBoolean();

            clienteExistente.setNome(novoNome);
            clienteExistente.setRg(novoRg);
            clienteExistente.setCpf(novoCpf);
            clienteExistente.setDataNascimento(java.sql.Date.valueOf(novaDataNascimentoStr));
            clienteExistente.setEndereco(novoEndereco);
            clienteExistente.setNumero(novoNumero);
            clienteExistente.setBairro(novoBairro);
            clienteExistente.setCidade(novaCidade);
            clienteExistente.setEstado(novoEstado);
            clienteExistente.setEmail(novoEmail);
            clienteExistente.setCelular(novoCelular);
            clienteExistente.setWhatsapp(novoWhatsapp);

            clienteDAO.atualizarCliente(clienteExistente);
            System.out.println("Cliente atualizado com sucesso!");
        } else {
            System.out.println("Cliente com ID " + clienteId + " não encontrado.");
        }
    }

    private static void excluirCliente(ClienteDAO clienteDAO, Scanner scanner) throws SQLException {
        System.out.print("ID do Cliente que deseja excluir: ");
        int clienteId = scanner.nextInt();
        scanner.nextLine(); // Limpa a quebra de linha

        Cliente clienteExistente = clienteDAO.buscarClientePorId(clienteId);

        if (clienteExistente != null) {
            clienteDAO.excluirCliente(clienteId);
            System.out.println("Cliente excluído com sucesso!");
        } else {
            System.out.println("Cliente com ID " + clienteId + " não encontrado.");
        }
    }

    private static void buscarClientePorId(ClienteDAO clienteDAO, Scanner scanner) throws SQLException {
        System.out.print("ID do Cliente que deseja buscar: ");
        int clienteId = scanner.nextInt();
        scanner.nextLine(); // Limpa a quebra de linha

        Cliente clienteExistente = clienteDAO.buscarClientePorId(clienteId);

        if (clienteExistente != null) {
            System.out.println("Cliente encontrado:");
            exibirDetalhesCliente(clienteExistente);
        } else {
            System.out.println("Cliente com ID " + clienteId + " não encontrado.");
        }
    }

    private static void listarTodosClientes(ClienteDAO clienteDAO) throws SQLException {
        List<Cliente> clientes = clienteDAO.listarClientes();

        if (!clientes.isEmpty()) {
            System.out.println("Lista de Clientes:");
            for (Cliente cliente : clientes) {
                exibirDetalhesCliente(cliente);
            }
        } else {
            System.out.println("Nenhum cliente cadastrado.");
        }
    }

    private static void exibirDetalhesCliente(Cliente cliente) {
        System.out.println("ID: " + cliente.getId());
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("RG: " + cliente.getRg());
        System.out.println("CPF: " + cliente.getCpf());
        System.out.println("Data de Nascimento: " + cliente.getDataNascimento());
        System.out.println("Endereço: " + cliente.getEndereco());
        System.out.println("Número: " + cliente.getNumero());
        System.out.println("Bairro: " + cliente.getBairro());
        System.out.println("Cidade: " + cliente.getCidade());
        System.out.println("Estado: " + cliente.getEstado());
        System.out.println("Email: " + cliente.getEmail());
        System.out.println("Celular: " + cliente.getCelular());
        System.out.println("WhatsApp: " + cliente.isWhatsapp());
    }
}
