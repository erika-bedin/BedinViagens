package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;

public class ClienteDAO {
    private Connection connection;

    // Construtor que recebe uma conexão JDBC
    public ClienteDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para adicionar um novo cliente ao banco de dados
    public void adicionarCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO cliente (nome, rg, cpf, dataNascimento, endereco, numero, bairro, cidade, estado, email, celular, whatsapp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getRg());
            statement.setString(3, cliente.getCpf());
            statement.setDate(4, new java.sql.Date(cliente.getDataNascimento().getTime()));
            statement.setString(5, cliente.getEndereco());
            statement.setInt(6, cliente.getNumero());
            statement.setString(7, cliente.getBairro());
            statement.setString(8, cliente.getCidade());
            statement.setString(9, cliente.getEstado());
            statement.setString(10, cliente.getEmail());
            statement.setString(11, cliente.getCelular());
            statement.setBoolean(12, cliente.isWhatsapp());

            statement.executeUpdate();
        }
    }

    // Método para atualizar um cliente no banco de dados
    public void atualizarCliente(Cliente cliente) throws SQLException {
        String sql = "UPDATE cliente SET nome=?, rg=?, cpf=?, dataNascimento=?, endereco=?, numero=?, bairro=?, cidade=?, estado=?, email=?, celular=?, whatsapp=? WHERE id=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getRg());
            statement.setString(3, cliente.getCpf());
            statement.setDate(4, new java.sql.Date(cliente.getDataNascimento().getTime()));
            statement.setString(5, cliente.getEndereco());
            statement.setInt(6, cliente.getNumero());
            statement.setString(7, cliente.getBairro());
            statement.setString(8, cliente.getCidade());
            statement.setString(9, cliente.getEstado());
            statement.setString(10, cliente.getEmail());
            statement.setString(11, cliente.getCelular());
            statement.setBoolean(12, cliente.isWhatsapp());
            statement.setInt(13, cliente.getId());

            statement.executeUpdate();
        }
    }

    // Método para excluir um cliente do banco de dados por ID
    public void excluirCliente(int clienteId) throws SQLException {
        String sql = "DELETE FROM cliente WHERE id=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, clienteId);
            statement.executeUpdate();
        }
    }

    // Método para buscar um cliente pelo ID
    public Cliente buscarClientePorId(int clienteId) throws SQLException {
        String sql = "SELECT * FROM cliente WHERE id=?";
        Cliente cliente = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, clienteId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                cliente = mapearCliente(resultSet);
            }
        }

        return cliente;
    }

    // Método para listar todos os clientes no banco de dados
    public List<Cliente> listarClientes() throws SQLException {
        String sql = "SELECT * FROM cliente";
        List<Cliente> clientes = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Cliente cliente = mapearCliente(resultSet);
                clientes.add(cliente);
            }
        }

        return clientes;
    }

    // Método para mapear os resultados da consulta para um objeto Cliente
    private Cliente mapearCliente(ResultSet resultSet) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(resultSet.getInt("id"));
        cliente.setNome(resultSet.getString("nome"));
        cliente.setRg(resultSet.getString("rg"));
        cliente.setCpf(resultSet.getString("cpf"));
        cliente.setDataNascimento(resultSet.getDate("dataNascimento"));
        cliente.setEndereco(resultSet.getString("endereco"));
        cliente.setNumero(resultSet.getInt("numero"));
        cliente.setBairro(resultSet.getString("bairro"));
        cliente.setCidade(resultSet.getString("cidade"));
        cliente.setEstado(resultSet.getString("estado"));
        cliente.setEmail(resultSet.getString("email"));
        cliente.setCelular(resultSet.getString("celular"));
        cliente.setWhatsapp(resultSet.getBoolean("whatsapp"));

        return cliente;
    }
}
