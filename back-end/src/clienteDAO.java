import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class clienteDAO {
    private Connection conexao;

    public clienteDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void criarCliente(cliente cliente) throws SQLException {
        String sql = "INSERT INTO Cliente (nome, rg, cpf, dataNascimento, endereco, numero, bairro, cidade, estado, celular, whatsapp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getRg());
            pstmt.setString(3, cliente.getCpf());
            pstmt.setString(4, cliente.getDataNascimento());
            pstmt.setString(5, cliente.getEndereco());
            pstmt.setInt(6, cliente.getNumero());
            pstmt.setString(7, cliente.getBairro());
            pstmt.setString(8, cliente.getCidade());
            pstmt.setString(9, cliente.getEstado());
            pstmt.setString(10, cliente.getCelular());
            pstmt.setBoolean(11, cliente.isWhatsapp());
            pstmt.executeUpdate();
        }
    }

    public cliente lerClientePorId(int id) throws SQLException {
        String sql = "SELECT * FROM Cliente WHERE id = ?";
        cliente cliente = null;
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    cliente = new cliente();
                    cliente.setId(rs.getInt("id"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setRg(rs.getString("rg"));
                    cliente.setCpf(rs.getString("cpf"));
                    cliente.setDataNascimento(rs.getString("dataNascimento"));
                    cliente.setEndereco(rs.getString("endereco"));
                    cliente.setNumero(rs.getInt("numero"));
                    cliente.setBairro(rs.getString("bairro"));
                    cliente.setCidade(rs.getString("cidade"));
                    cliente.setEstado(rs.getString("estado"));
                    cliente.setCelular(rs.getString("celular"));
                    cliente.setWhatsapp(rs.getBoolean("whatsapp"));
                }
            }
        }
        return cliente;
    }

    public List<cliente> listarClientes() throws SQLException {
        String sql = "SELECT * FROM Cliente";
        List<cliente> clientes = new ArrayList<>();
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    cliente cliente = new cliente();
                    cliente.setId(rs.getInt("id"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setRg(rs.getString("rg"));
                    cliente.setCpf(rs.getString("cpf"));
                    cliente.setDataNascimento(rs.getString("dataNascimento"));
                    cliente.setEndereco(rs.getString("endereco"));
                    cliente.setNumero(rs.getInt("numero"));
                    cliente.setBairro(rs.getString("bairro"));
                    cliente.setCidade(rs.getString("cidade"));
                    cliente.setEstado(rs.getString("estado"));
                    cliente.setCelular(rs.getString("celular"));
                    cliente.setWhatsapp(rs.getBoolean("whatsapp"));
                    clientes.add(cliente);
                }
            }
        }
        return clientes;
    }

    public void atualizarCliente(cliente cliente) throws SQLException {
        String sql = "UPDATE Cliente SET nome=?, rg=?, cpf=?, dataNascimento=?, endereco=?, numero=?, bairro=?, cidade=?, estado=?, celular=?, whatsapp=? WHERE id=?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getRg());
            pstmt.setString(3, cliente.getCpf());
            pstmt.setString(4, cliente.getDataNascimento());
            pstmt.setString(5, cliente.getEndereco());
            pstmt.setInt(6, cliente.getNumero());
            pstmt.setString(7, cliente.getBairro());
            pstmt.setString(8, cliente.getCidade());
            pstmt.setString(9, cliente.getEstado());
            pstmt.setString(10, cliente.getCelular());
            pstmt.setBoolean(11, cliente.isWhatsapp());
            pstmt.setInt(12, cliente.getId());
            pstmt.executeUpdate();
        }
    }

    public void deletarCliente(int id) throws SQLException {
        String sql = "DELETE FROM Cliente WHERE id=?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
