package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Hotel;

public class HotelDAO {
    private Connection connection;

    // Construtor que recebe uma conexão JDBC
    public HotelDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para adicionar um novo hotel ao banco de dados
    public void adicionarHotel(Hotel hotel) throws SQLException {
        String sql = "INSERT INTO hotel (nomeHotel, idDestino, tipoQuarto, qtdeQuarto, precoPorNoite) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, hotel.getNomeHotel());
            statement.setInt(2, hotel.getIdDestino());
            statement.setString(3, hotel.getTipoQuarto());
            statement.setString(4, hotel.getQtdeQuarto());
            statement.setDouble(5, hotel.getPrecoPorNoite());

            statement.executeUpdate();
        }
    }

    // Método para atualizar um hotel no banco de dados
    public void atualizarHotel(Hotel hotel) throws SQLException {
        String sql = "UPDATE hotel SET nomeHotel=?, idDestino=?, tipoQuarto=?, qtdeQuarto=?, precoPorNoite=? WHERE id=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, hotel.getNomeHotel());
            statement.setInt(2, hotel.getIdDestino());
            statement.setString(3, hotel.getTipoQuarto());
            statement.setString(4, hotel.getQtdeQuarto());
            statement.setDouble(5, hotel.getPrecoPorNoite());
            statement.setInt(6, hotel.getId());

            statement.executeUpdate();
        }
    }

    // Método para excluir um hotel do banco de dados por ID
    public void excluirHotel(int hotelId) throws SQLException {
        String sql = "DELETE FROM hotel WHERE id=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, hotelId);
            statement.executeUpdate();
        }
    }

    // Método para buscar um hotel pelo ID
    public Hotel buscarHotelPorId(int hotelId) throws SQLException {
        String sql = "SELECT * FROM hotel WHERE id=?";
        Hotel hotel = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, hotelId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                hotel = mapearHotel(resultSet);
            }
        }

        return hotel;
    }

    // Método para listar todos os hotéis no banco de dados
    public List<Hotel> listarHoteis() throws SQLException {
        String sql = "SELECT * FROM hotel";
        List<Hotel> hoteis = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Hotel hotel = mapearHotel(resultSet);
                hoteis.add(hotel);
            }
        }

        return hoteis;
    }

    // Método para mapear os resultados da consulta para um objeto Hotel
    private Hotel mapearHotel(ResultSet resultSet) throws SQLException {
        Hotel hotel = new Hotel();
        hotel.setId(resultSet.getInt("id"));
        hotel.setNomeHotel(resultSet.getString("nomeHotel"));
        hotel.setIdDestino(resultSet.getInt("idDestino"));
        hotel.setTipoQuarto(resultSet.getString("tipoQuarto"));
        hotel.setQtdeQuarto(resultSet.getString("qtdeQuarto"));
        hotel.setPrecoPorNoite(resultSet.getDouble("precoPorNoite"));

        return hotel;
    }
}
