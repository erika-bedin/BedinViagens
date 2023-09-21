package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Compra;
import model.TipoCompra; 


public class CompraDAO {
    private Connection connection;

    // Construtor que recebe uma conexão JDBC
    public CompraDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para adicionar uma nova compra ao banco de dados
    public void adicionarCompra(Compra compra) throws SQLException {
        String sql = "INSERT INTO compra (idCliente, idDestino, dataCompra, tipoCompra, qtdPassagens, qtdNoitesHotel, precoTotal) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, compra.getIdCliente());
            statement.setInt(2, compra.getIdDestino());
            statement.setDate(3, new java.sql.Date(compra.getDataCompra().getTime()));
            statement.setString(4, compra.getTipoCompra().name()); // Alteração aqui
            statement.setInt(5, compra.getQtdPassagens());
            statement.setInt(6, compra.getQtdNoitesHotel());
            statement.setDouble(7, compra.getPrecoTotal());

            statement.executeUpdate();
        }
    }

    // Método para atualizar uma compra no banco de dados
    public void atualizarCompra(Compra compra) throws SQLException {
        String sql = "UPDATE compra SET idCliente=?, idDestino=?, dataCompra=?, tipoCompra=?, qtdPassagens=?, qtdNoitesHotel=?, precoTotal=? " +
                     "WHERE id=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, compra.getIdCliente());
            statement.setInt(2, compra.getIdDestino());
            statement.setDate(3, new java.sql.Date(compra.getDataCompra().getTime()));
            statement.setString(4, compra.getTipoCompra().name()); // Alteração aqui
            statement.setInt(5, compra.getQtdPassagens());
            statement.setInt(6, compra.getQtdNoitesHotel());
            statement.setDouble(7, compra.getPrecoTotal());
            statement.setInt(8, compra.getId());

            statement.executeUpdate();
        }
    }

    // Método para consultar uma compra por ID
    public Compra consultarCompraPorId(int id) throws SQLException {
        String sql = "SELECT * FROM compra WHERE id=?";
        Compra compra = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                compra = mapearCompra(resultSet);
            }
        }

        return compra;
    }

    // Método para listar todas as compras no banco de dados
    public List<Compra> listarCompras() throws SQLException {
        String sql = "SELECT * FROM compra";
        List<Compra> compras = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Compra compra = mapearCompra(resultSet);
                compras.add(compra);
            }
        }

        return compras;
    }

    // Método para deletar uma compra por ID
    public void deletarCompra(int id) throws SQLException {
        String sql = "DELETE FROM compra WHERE id=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    // Método para mapear os resultados da consulta para um objeto Compra
    private Compra mapearCompra(ResultSet resultSet) throws SQLException {
        Compra compra = new Compra();
        compra.setId(resultSet.getInt("id"));
        compra.setIdCliente(resultSet.getInt("idCliente"));
        compra.setIdDestino(resultSet.getInt("idDestino"));
        compra.setDataCompra(resultSet.getDate("dataCompra"));
        
        // Alteração para ler e converter o enum TipoCompra
        String tipoCompraString = resultSet.getString("tipoCompra");
        TipoCompra tipoCompra = TipoCompra.valueOf(tipoCompraString);
        compra.setTipoCompra(tipoCompra);
        
        compra.setQtdPassagens(resultSet.getInt("qtdPassagens"));
        compra.setQtdNoitesHotel(resultSet.getInt("qtdNoitesHotel"));
        compra.setPrecoTotal(resultSet.getDouble("precoTotal"));

        return compra;
    }
}
