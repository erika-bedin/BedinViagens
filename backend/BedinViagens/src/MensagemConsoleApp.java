import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import model.Mensagem;
import model.PreferenciaContato;
import model.MelhorPeriodoContato;
import dao.MensagemDAO;


public class MensagemConsoleApp {
    public static void main(String[] args) {
        // Configuração do banco de dados
        String jdbcURL = "jdbc:mysql://localhost:3306/agencia_de_viagens";
        String username = "erika";
        String password = "Teste2023+";

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            MensagemDAO mensagemDAO = new MensagemDAO(connection);

            Scanner scanner = new Scanner(System.in);

            while (true) {
            	System.out.println ("========== Bedin Viagens ==========");
            	System.out.println ("========= Módulo Mensagem =========");
            	System.out.println("\nEscolha uma opção:");
                System.out.println("1. Adicionar Mensagem");
                System.out.println("2. Atualizar Mensagem");
                System.out.println("3. Excluir Mensagem");
                System.out.println("4. Buscar Mensagem por ID");
                System.out.println("5. Listar Todas as Mensagens");
                System.out.println("6. Sair do Módulo Mensagem");
                System.out.println ("\n===================================");

                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpa a quebra de linha

                switch (opcao) {
                    case 1:
                        adicionarMensagem(mensagemDAO, scanner);
                        break;
                    case 2:
                        atualizarMensagem(mensagemDAO, scanner);
                        break;
                    case 3:
                        excluirMensagem(mensagemDAO, scanner);
                        break;
                    case 4:
                        buscarMensagemPorId(mensagemDAO, scanner);
                        break;
                    case 5:
                        listarTodasMensagens(mensagemDAO);
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

    private static void adicionarMensagem(MensagemDAO mensagemDAO, Scanner scanner) throws SQLException {
        Mensagem novaMensagem = new Mensagem();

        System.out.print("ID do Cliente: ");
        novaMensagem.setIdCliente(scanner.nextInt());
        scanner.nextLine(); // Limpa a quebra de linha

        System.out.print("Nome do Remetente: ");
        novaMensagem.setNomeRemetente(scanner.nextLine());

        System.out.print("Telefone do Remetente: ");
        novaMensagem.setTelefoneRemetente(scanner.nextLine());

        System.out.print("Email do Remetente: ");
        novaMensagem.setEmailRemetente(scanner.nextLine());

        System.out.print("Mensagem: ");
        novaMensagem.setMensagem(scanner.nextLine());

        System.out.print("Preferência de Contato (Telefone/E-mail/WhatsApp): ");
        String preferenciaContato = scanner.nextLine();
        PreferenciaContato preferencia = PreferenciaContato.valueOf(preferenciaContato);
        novaMensagem.setPreferenciaContato(preferencia);

        System.out.print("Melhor Período de Contato (Manhã/Tarde/Noite): ");
        String periodoContato = scanner.nextLine();
        MelhorPeriodoContato periodo = MelhorPeriodoContato.valueOf(periodoContato);
        novaMensagem.setMelhorPeriodoContato(periodo);

        System.out.print("Receber Descontos (true/false): ");
        novaMensagem.setReceberDescontos(scanner.nextBoolean());

        System.out.print("Receber Novidades (true/false): ");
        novaMensagem.setReceberNovidades(scanner.nextBoolean());

        mensagemDAO.adicionarMensagem(novaMensagem);
        System.out.println("Mensagem adicionada com sucesso!");
    }

    private static void atualizarMensagem(MensagemDAO mensagemDAO, Scanner scanner) throws SQLException {
        System.out.print("ID da Mensagem que deseja atualizar: ");
        int mensagemId = scanner.nextInt();
        scanner.nextLine(); // Limpa a quebra de linha

        Mensagem mensagemExistente = mensagemDAO.buscarMensagemPorId(mensagemId);

        if (mensagemExistente != null) {
            System.out.print("Novo ID do Cliente: ");
            mensagemExistente.setIdCliente(scanner.nextInt());
            scanner.nextLine(); // Limpa a quebra de linha

            System.out.print("Novo Nome do Remetente: ");
            mensagemExistente.setNomeRemetente(scanner.nextLine());

            System.out.print("Novo Telefone do Remetente: ");
            mensagemExistente.setTelefoneRemetente(scanner.nextLine());

            System.out.print("Novo Email do Remetente: ");
            mensagemExistente.setEmailRemetente(scanner.nextLine());

            System.out.print("Nova Mensagem: ");
            mensagemExistente.setMensagem(scanner.nextLine());

            System.out.print("Nova Preferência de Contato (Telefone/E-mail/WhatsApp): ");
            String preferenciaContato = scanner.nextLine();
            PreferenciaContato preferencia = null;

            try {
                preferencia = PreferenciaContato.valueOf(preferenciaContato);
            } catch (IllegalArgumentException e) {
                System.out.println("Preferência de Contato inválida. Use um valor válido.");
                return; // Sai da atualização se a preferência for inválida
            }

            mensagemExistente.setPreferenciaContato(preferencia);

            System.out.print("Novo Melhor Período de Contato (Manhã/Tarde/Noite): ");
            String periodoContato = scanner.nextLine();
            MelhorPeriodoContato periodo = null;

            try {
                periodo = MelhorPeriodoContato.valueOf(periodoContato);
            } catch (IllegalArgumentException e) {
                System.out.println("Melhor Período de Contato inválido. Use um valor válido.");
                return; // Sai da atualização se o período for inválido
            }

            mensagemExistente.setMelhorPeriodoContato(periodo);

            System.out.print("Receber Descontos (true/false): ");
            mensagemExistente.setReceberDescontos(scanner.nextBoolean());

            System.out.print("Receber Novidades (true/false): ");
            mensagemExistente.setReceberNovidades(scanner.nextBoolean());

            mensagemDAO.atualizarMensagem(mensagemExistente);
            System.out.println("Mensagem atualizada com sucesso!");
        } else {
            System.out.println("Mensagem com ID " + mensagemId + " não encontrada.");
        }
    }

    private static void excluirMensagem(MensagemDAO mensagemDAO, Scanner scanner) throws SQLException {
        System.out.print("ID da Mensagem que deseja excluir: ");
        int mensagemId = scanner.nextInt();
        scanner.nextLine(); // Limpa a quebra de linha

        Mensagem mensagemExistente = mensagemDAO.buscarMensagemPorId(mensagemId);

        if (mensagemExistente != null) {
            mensagemDAO.excluirMensagem(mensagemId);
            System.out.println("Mensagem excluída com sucesso!");
        } else {
            System.out.println("Mensagem com ID " + mensagemId + " não encontrada.");
        }
    }

    private static void buscarMensagemPorId(MensagemDAO mensagemDAO, Scanner scanner) throws SQLException {
        System.out.print("ID da Mensagem que deseja buscar: ");
        int mensagemId = scanner.nextInt();
        scanner.nextLine(); // Limpa a quebra de linha

        Mensagem mensagemExistente = mensagemDAO.buscarMensagemPorId(mensagemId);

        if (mensagemExistente != null) {
            exibirDetalhesMensagem(mensagemExistente);
        } else {
            System.out.println("Mensagem com ID " + mensagemId + " não encontrada.");
        }
    }

    private static void listarTodasMensagens(MensagemDAO mensagemDAO) throws SQLException {
        List<Mensagem> mensagens = mensagemDAO.listarMensagens();

        if (!mensagens.isEmpty()) {
            System.out.println("\nLista de Mensagens:");
            for (Mensagem mensagem : mensagens) {
                exibirDetalhesMensagem(mensagem);
            }
        } else {
            System.out.println("Nenhuma mensagem encontrada.");
        }
    }

    private static void exibirDetalhesMensagem(Mensagem mensagem) {
        System.out.println("\nDetalhes da Mensagem:");
        System.out.println("ID: " + mensagem.getId());
        System.out.println("ID do Cliente: " + mensagem.getIdCliente());
        System.out.println("Nome do Remetente: " + mensagem.getNomeRemetente());
        System.out.println("Telefone do Remetente: " + mensagem.getTelefoneRemetente());
        System.out.println("Email do Remetente: " + mensagem.getEmailRemetente());
        System.out.println("Mensagem: " + mensagem.getMensagem());
        System.out.println("Preferência de Contato: " + mensagem.getPreferenciaContato());
        System.out.println("Melhor Período de Contato: " + mensagem.getMelhorPeriodoContato());
        System.out.println("Receber Descontos: " + mensagem.isReceberDescontos());
        System.out.println("Receber Novidades: " + mensagem.isReceberNovidades());
    }
}
