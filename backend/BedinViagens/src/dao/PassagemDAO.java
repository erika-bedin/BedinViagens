package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Passagem;

public class PassagemDAO {
    private Connection connection;

    // Construtor que recebe uma conexão JDBC
    public PassagemDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para adicionar uma nova passagem ao banco de dados
    public void adicionarPassagem(Passagem passagem) throws SQLException {
        String sql = "INSERT INTO passagem (idDestino, ciaAereaDestino, vooDestino, dataDestino, horaDestino, " +
                     "origem, ciaAereaOrigem, vooOrigem, dataOrigem, horaOrigem, precoPassagem) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, passagem.getIdDestino());
            statement.setString(2, passagem.getCiaAereaDestino());
            statement.setString(3, passagem.getVooDestino());
            statement.setDate(4, passagem.getDataDestino());
            statement.setTime(5, passagem.getHoraDestino());
            statement.setString(6, passagem.getOrigem());
            statement.setString(7, passagem.getCiaAereaOrigem());
            statement.setString(8, passagem.getVooOrigem());
            statement.setDate(9, passagem.getDataOrigem());
            statement.setTime(10, passagem.getHoraOrigem());
            statement.setBigDecimal(11, passagem.getPrecoPassagem());

            statement.executeUpdate();
        }
    }

    // Método para atualizar uma passagem no banco de dados
    public void atualizarPassagem(Passagem passagem) throws SQLException {
        String sql = "UPDATE passagem SET idDestino=?, ciaAereaDestino=?, vooDestino=?, dataDestino=?, " +
                     "horaDestino=?, origem=?, ciaAereaOrigem=?, vooOrigem=?, dataOrigem=?, horaOrigem=?, " +
                     "precoPassagem=? WHERE id=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, passagem.getIdDestino());
            statement.setString(2, passagem.getCiaAereaDestino());
            statement.setString(3, passagem.getVooDestino());
            statement.setDate(4, passagem.getDataDestino());
            statement.setTime(5, passagem.getHoraDestino());
            statement.setString(6, passagem.getOrigem());
            statement.setString(7, passagem.getCiaAereaOrigem());
            statement.setString(8, passagem.getVooOrigem());
            statement.setDate(9, passagem.getDataOrigem());
            statement.setTime(10, passagem.getHoraOrigem());
            statement.setBigDecimal(11, passagem.getPrecoPassagem());
            statement.setInt(12, passagem.getId());

            statement.executeUpdate();
        }
    }

    // Método para excluir uma passagem do banco de dados por ID
    public void excluirPassagem(int passagemId) throws SQLException {
        String sql = "DELETE FROM passagem WHERE id=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, passagemId);
            statement.executeUpdate();
        }
    }

    // Método para buscar uma passagem pelo ID
    public Passagem buscarPassagemPorId(int passagemId) throws SQLException {
        String sql = "SELECT * FROM passagem WHERE id=?";
        Passagem passagem = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, passagemId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                passagem = mapearPassagem(resultSet);
            }
        }

        return passagem;
    }

    // Método para listar todas as passagens no banco de dados
    public List<Passagem> listarPassagens() throws SQLException {
        String sql = "SELECT * FROM passagem";
        List<Passagem> passagens = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Passagem passagem = mapearPassagem(resultSet);
                passagens.add(passagem);
            }
        }

        return passagens;
    }

    // Método para mapear os resultados da consulta para um objeto Passagem
    private Passagem mapearPassagem(ResultSet resultSet) throws SQLException {
        Passagem passagem = new Passagem();
        passagem.setId(resultSet.getInt("id"));
        passagem.setIdDestino(resultSet.getInt("idDestino"));
        passagem.setCiaAereaDestino(resultSet.getString("ciaAereaDestino"));
        passagem.setVooDestino(resultSet.getString("vooDestino"));
        passagem.setDataDestino(resultSet.getDate("dataDestino"));
        passagem.setHoraDestino(resultSet.getTime("horaDestino"));
        passagem.setOrigem(resultSet.getString("origem"));
        passagem.setCiaAereaOrigem(resultSet.getString("ciaAereaOrigem"));
        passagem.setVooOrigem(resultSet.getString("vooOrigem"));
        passagem.setDataOrigem(resultSet.getDate("dataOrigem"));
        passagem.setHoraOrigem(resultSet.getTime("horaOrigem"));
        passagem.setPrecoPassagem(resultSet.getBigDecimal("precoPassagem"));

        return passagem;
    }
}
