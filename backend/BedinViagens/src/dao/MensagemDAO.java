package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Mensagem;
import model.PreferenciaContato;
import model.MelhorPeriodoContato;

public class MensagemDAO {
    private Connection connection;

    // Construtor que recebe uma conexão JDBC
    public MensagemDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para adicionar uma nova mensagem ao banco de dados
    public void adicionarMensagem(Mensagem mensagem) throws SQLException {
        String sql = "INSERT INTO mensagem (idCliente, nomeRemetente, telefoneRemetente, emailRemetente, mensagem, " +
                     "preferenciaContato, melhorPeriodoContato, receberDescontos, receberNovidades) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, mensagem.getIdCliente());
            statement.setString(2, mensagem.getNomeRemetente());
            statement.setString(3, mensagem.getTelefoneRemetente());
            statement.setString(4, mensagem.getEmailRemetente());
            statement.setString(5, mensagem.getMensagem());
            statement.setString(6, mensagem.getPreferenciaContato().name()); // Converta para string
            statement.setString(7, mensagem.getMelhorPeriodoContato().name()); // Converta para string
            statement.setBoolean(8, mensagem.isReceberDescontos());
            statement.setBoolean(9, mensagem.isReceberNovidades());

            statement.executeUpdate();
        }
    }

    // Método para atualizar uma mensagem no banco de dados
    public void atualizarMensagem(Mensagem mensagem) throws SQLException {
        String sql = "UPDATE mensagem SET idCliente=?, nomeRemetente=?, telefoneRemetente=?, emailRemetente=?, " +
                     "mensagem=?, preferenciaContato=?, melhorPeriodoContato=?, receberDescontos=?, receberNovidades=? " +
                     "WHERE id=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, mensagem.getIdCliente());
            statement.setString(2, mensagem.getNomeRemetente());
            statement.setString(3, mensagem.getTelefoneRemetente());
            statement.setString(4, mensagem.getEmailRemetente());
            statement.setString(5, mensagem.getMensagem());
            statement.setString(6, mensagem.getPreferenciaContato().name()); // Converta para string
            statement.setString(7, mensagem.getMelhorPeriodoContato().name()); // Converta para string
            statement.setBoolean(8, mensagem.isReceberDescontos());
            statement.setBoolean(9, mensagem.isReceberNovidades());
            statement.setInt(10, mensagem.getId());

            statement.executeUpdate();
        }
    }

    // Método para excluir uma mensagem do banco de dados por ID
    public void excluirMensagem(int mensagemId) throws SQLException {
        String sql = "DELETE FROM mensagem WHERE id=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, mensagemId);
            statement.executeUpdate();
        }
    }

    // Método para buscar uma mensagem pelo ID
    public Mensagem buscarMensagemPorId(int mensagemId) throws SQLException {
        String sql = "SELECT * FROM mensagem WHERE id=?";
        Mensagem mensagem = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, mensagemId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                mensagem = mapearMensagem(resultSet);
            }
        }

        return mensagem;
    }

    // Método para listar todas as mensagens no banco de dados
    public List<Mensagem> listarMensagens() throws SQLException {
        String sql = "SELECT * FROM mensagem";
        List<Mensagem> mensagens = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Mensagem mensagem = mapearMensagem(resultSet);
                mensagens.add(mensagem);
            }
        }

        return mensagens;
    }

    // Método para mapear os resultados da consulta para um objeto Mensagem
    private Mensagem mapearMensagem(ResultSet resultSet) throws SQLException {
        Mensagem mensagem = new Mensagem();
        mensagem.setId(resultSet.getInt("id"));
        mensagem.setIdCliente(resultSet.getInt("idCliente"));
        mensagem.setNomeRemetente(resultSet.getString("nomeRemetente"));
        mensagem.setTelefoneRemetente(resultSet.getString("telefoneRemetente"));
        mensagem.setEmailRemetente(resultSet.getString("emailRemetente"));
        mensagem.setMensagem(resultSet.getString("mensagem"));
        mensagem.setPreferenciaContato(PreferenciaContato.valueOf(resultSet.getString("preferenciaContato"))); // Converta de string para enum
        mensagem.setMelhorPeriodoContato(MelhorPeriodoContato.valueOf(resultSet.getString("melhorPeriodoContato"))); // Converta de string para enum
        mensagem.setReceberDescontos(resultSet.getBoolean("receberDescontos"));
        mensagem.setReceberNovidades(resultSet.getBoolean("receberNovidades"));

        return mensagem;
    }
}
