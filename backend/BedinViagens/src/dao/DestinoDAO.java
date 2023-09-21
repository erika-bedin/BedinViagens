package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Destino;

public class DestinoDAO {
    private Connection connection;

    // Construtor que recebe uma conexão JDBC
    public DestinoDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para adicionar um novo destino ao banco de dados
    public void adicionarDestino(Destino destino) throws SQLException {
        String sql = "INSERT INTO destino (nomeDestino, descricaoDestino) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, destino.getNomeDestino());
            statement.setString(2, destino.getDescricaoDestino());

            statement.executeUpdate();
        }
    }

    // Método para atualizar um destino no banco de dados
    public void atualizarDestino(Destino destino) throws SQLException {
        String sql = "UPDATE destino SET nomeDestino=?, descricaoDestino=? WHERE id=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, destino.getNomeDestino());
            statement.setString(2, destino.getDescricaoDestino());
            statement.setInt(3, destino.getId());

            statement.executeUpdate();
        }
    }

    // Método para excluir um destino do banco de dados por ID
    public void excluirDestino(int destinoId) throws SQLException {
        String sql = "DELETE FROM destino WHERE id=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, destinoId);
            statement.executeUpdate();
        }
    }

    // Método para buscar um destino pelo ID
    public Destino buscarDestinoPorId(int destinoId) throws SQLException {
        String sql = "SELECT * FROM destino WHERE id=?";
        Destino destino = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, destinoId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                destino = mapearDestino(resultSet);
            }
        }

        return destino;
    }

    // Método para listar todos os destinos no banco de dados
    public List<Destino> listarDestinos() throws SQLException {
        String sql = "SELECT * FROM destino";
        List<Destino> destinos = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Destino destino = mapearDestino(resultSet);
                destinos.add(destino);
            }
        }

        return destinos;
    }

    // Método para mapear os resultados da consulta para um objeto Destino
    private Destino mapearDestino(ResultSet resultSet) throws SQLException {
        Destino destino = new Destino();
        destino.setId(resultSet.getInt("id"));
        destino.setNomeDestino(resultSet.getString("nomeDestino"));
        destino.setDescricaoDestino(resultSet.getString("descricaoDestino"));

        return destino;
    }
}
