package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Pesquisa;

public class PesquisaDAO {
    private Connection connection;

    // Construtor que recebe uma conexão JDBC
    public PesquisaDAO(Connection connection) {
        this.connection = connection;
    }

 // Método para adicionar uma nova pesquisa ao banco de dados
    public void adicionarPesquisa(Pesquisa pesquisa) throws SQLException {
        String sql = "INSERT INTO pesquisa (idCliente, idDestino, dataPesquisa, dataSimulacao, qtdPassagens, " +
                     "qtdNoitesHotel, simularPassagem, simularHotel, clienteExistente) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, pesquisa.getIdCliente());
            statement.setInt(2, pesquisa.getIdDestino());
            statement.setDate(3, new java.sql.Date(pesquisa.getDataPesquisa().getTime())); // Convert java.util.Date to java.sql.Date
            statement.setDate(4, new java.sql.Date(pesquisa.getDataSimulacao().getTime())); // Convert java.util.Date to java.sql.Date
            statement.setInt(5, pesquisa.getQtdPassagens());
            statement.setInt(6, pesquisa.getQtdNoitesHotel());
            statement.setBoolean(7, pesquisa.isSimularPassagem());
            statement.setBoolean(8, pesquisa.isSimularHotel());
            statement.setBoolean(9, pesquisa.isClienteExistente());

            statement.executeUpdate();

            // Recuperar o ID gerado automaticamente
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                pesquisa.setId(generatedKeys.getInt(1));
            }
        }
    }

    // Método para atualizar uma pesquisa no banco de dados
    public void atualizarPesquisa(Pesquisa pesquisa) throws SQLException {
        String sql = "UPDATE pesquisa SET idCliente=?, idDestino=?, dataPesquisa=?, dataSimulacao=?, qtdPassagens=?, " +
                     "qtdNoitesHotel=?, simularPassagem=?, simularHotel=?, clienteExistente=? WHERE id=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, pesquisa.getIdCliente());
            statement.setInt(2, pesquisa.getIdDestino());
            statement.setDate(3, new java.sql.Date(pesquisa.getDataPesquisa().getTime())); // Convert java.util.Date to java.sql.Date
            statement.setDate(4, new java.sql.Date(pesquisa.getDataSimulacao().getTime())); // Convert java.util.Date to java.sql.Date
            statement.setInt(5, pesquisa.getQtdPassagens());
            statement.setInt(6, pesquisa.getQtdNoitesHotel());
            statement.setBoolean(7, pesquisa.isSimularPassagem());
            statement.setBoolean(8, pesquisa.isSimularHotel());
            statement.setBoolean(9, pesquisa.isClienteExistente());
            statement.setInt(10, pesquisa.getId());

            statement.executeUpdate();
        }
    }

    // Método para excluir uma pesquisa do banco de dados por ID
    public void excluirPesquisa(int pesquisaId) throws SQLException {
        String sql = "DELETE FROM pesquisa WHERE id=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, pesquisaId);
            statement.executeUpdate();
        }
    }

    // Método para buscar uma pesquisa pelo ID
    public Pesquisa buscarPesquisaPorId(int pesquisaId) throws SQLException {
        String sql = "SELECT * FROM pesquisa WHERE id=?";
        Pesquisa pesquisa = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, pesquisaId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                pesquisa = mapearPesquisa(resultSet);
            }
        }

        return pesquisa;
    }

    // Método para listar todas as pesquisas no banco de dados
    public List<Pesquisa> listarPesquisas() throws SQLException {
        String sql = "SELECT * FROM pesquisa";
        List<Pesquisa> pesquisas = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Pesquisa pesquisa = mapearPesquisa(resultSet);
                pesquisas.add(pesquisa);
            }
        }

        return pesquisas;
    }

    // Método para mapear os resultados da consulta para um objeto Pesquisa
    private Pesquisa mapearPesquisa(ResultSet resultSet) throws SQLException {
        Pesquisa pesquisa = new Pesquisa();
        pesquisa.setId(resultSet.getInt("id"));
        pesquisa.setIdCliente(resultSet.getInt("idCliente"));
        pesquisa.setIdDestino(resultSet.getInt("idDestino"));
        pesquisa.setDataPesquisa(resultSet.getDate("dataPesquisa"));
        pesquisa.setDataSimulacao(resultSet.getDate("dataSimulacao"));
        pesquisa.setQtdPassagens(resultSet.getInt("qtdPassagens"));
        pesquisa.setQtdNoitesHotel(resultSet.getInt("qtdNoitesHotel"));
        pesquisa.setSimularPassagem(resultSet.getBoolean("simularPassagem"));
        pesquisa.setSimularHotel(resultSet.getBoolean("simularHotel"));
        pesquisa.setClienteExistente(resultSet.getBoolean("clienteExistente"));

        return pesquisa;
    }
}
