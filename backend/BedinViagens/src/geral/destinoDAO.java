package geral;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class destinoDAO {
    private Connection conexao;

    // Construtor que recebe a conexão
    public destinoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void criarDestino(destino destino) throws SQLException {
        String sql = "INSERT INTO destino (nome_destino, descricao_destino) VALUES (?, ?)";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, destino.getNomeDestino());
            pstmt.setString(2, destino.getDescricaoDestino());
            pstmt.executeUpdate();
        }
    }

    public destino lerDestinoPorId(int id) throws SQLException {
        String sql = "SELECT * FROM destino WHERE id = ?";
        destino destino = null;
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    destino = new destino();
                    destino.setId(rs.getInt("id"));
                    destino.setNomeDestino(rs.getString("nome_destino"));
                    destino.setDescricaoDestino(rs.getString("descricao_destino"));
                }
            }
        }
        return destino;
    }

    public List<destino> listarDestinos() throws SQLException {
        String sql = "SELECT * FROM destino";
        List<destino> destinos = new ArrayList<>();
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    destino destino = new destino();
                    destino.setId(rs.getInt("id"));
                    destino.setNomeDestino(rs.getString("nome_destino"));
                    destino.setDescricaoDestino(rs.getString("descricao_destino"));
                    destinos.add(destino);
                }
            }
        }
        return destinos;
    }

    public void atualizarDestino(destino destino) throws SQLException {
        String sql = "UPDATE destino SET nome_destino=?, descricao_destino=? WHERE id=?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, destino.getNomeDestino());
            pstmt.setString(2, destino.getDescricaoDestino());
            pstmt.setInt(3, destino.getId());
            pstmt.executeUpdate();
        }
    }

    public void deletarDestino(int id) throws SQLException {
        String sql = "DELETE FROM destino WHERE id=?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    // Método para excluir destinos relacionados a um cliente pelo ID do cliente
    public void deletarDestinosDoCliente(int idCliente) throws SQLException {
        String sql = "DELETE FROM destino WHERE id_cliente = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            stmt.executeUpdate();
        }
    }
}