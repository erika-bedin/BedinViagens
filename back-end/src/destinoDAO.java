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
        String sql = "INSERT INTO Destino (id_cliente, dataPartida, horaPartida, origem, companhiaAereaPartida, numVooPartida, dataRetorno, horaRetorno, destino, companhiaAereaRetorno, numVooRetorno, qtdPessoas, qtdQuartos, categoriaQuartos) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, destino.getIdCliente());
            pstmt.setString(2, destino.getDataPartida());
            pstmt.setString(3, destino.getHoraPartida());
            pstmt.setString(4, destino.getOrigem());
            pstmt.setString(5, destino.getCompanhiaAereaPartida());
            pstmt.setString(6, destino.getNumVooPartida());
            pstmt.setString(7, destino.getDataRetorno());
            pstmt.setString(8, destino.getHoraRetorno());
            pstmt.setString(9, destino.getDestino());
            pstmt.setString(10, destino.getCompanhiaAereaRetorno());
            pstmt.setString(11, destino.getNumVooRetorno());
            pstmt.setInt(12, destino.getQtdPessoas());
            pstmt.setInt(13, destino.getQtdQuartos());
            pstmt.setString(14, destino.getCategoriaQuartos());
            pstmt.executeUpdate();
        }
    }

    public destino lerDestinoPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Destino WHERE id = ?";
        destino destino = null;
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    destino = new destino();
                    destino.setId(rs.getInt("id"));
                    destino.setIdCliente(rs.getInt("id_cliente"));
                    destino.setDataPartida(rs.getString("dataPartida"));
                    destino.setHoraPartida(rs.getString("horaPartida"));
                    destino.setOrigem(rs.getString("origem"));
                    destino.setCompanhiaAereaPartida(rs.getString("companhiaAereaPartida"));
                    destino.setNumVooPartida(rs.getString("numVooPartida"));
                    destino.setDataRetorno(rs.getString("dataRetorno"));
                    destino.setHoraRetorno(rs.getString("horaRetorno"));
                    destino.setDestino(rs.getString("destino"));
                    destino.setCompanhiaAereaRetorno(rs.getString("companhiaAereaRetorno"));
                    destino.setNumVooRetorno(rs.getString("numVooRetorno"));
                    destino.setQtdPessoas(rs.getInt("qtdPessoas"));
                    destino.setQtdQuartos(rs.getInt("qtdQuartos"));
                    destino.setCategoriaQuartos(rs.getString("categoriaQuartos"));
                }
            }
        }
        return destino;
    }

    public List<destino> listarDestinos() throws SQLException {
        String sql = "SELECT * FROM Destino";
        List<destino> destinos = new ArrayList<>();
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    destino destino = new destino();
                    destino.setId(rs.getInt("id"));
                    destino.setIdCliente(rs.getInt("id_cliente"));
                    destino.setDataPartida(rs.getString("dataPartida"));
                    destino.setHoraPartida(rs.getString("horaPartida"));
                    destino.setOrigem(rs.getString("origem"));
                    destino.setCompanhiaAereaPartida(rs.getString("companhiaAereaPartida"));
                    destino.setNumVooPartida(rs.getString("numVooPartida"));
                    destino.setDataRetorno(rs.getString("dataRetorno"));
                    destino.setHoraRetorno(rs.getString("horaRetorno"));
                    destino.setDestino(rs.getString("destino"));
                    destino.setCompanhiaAereaRetorno(rs.getString("companhiaAereaRetorno"));
                    destino.setNumVooRetorno(rs.getString("numVooRetorno"));
                    destino.setQtdPessoas(rs.getInt("qtdPessoas"));
                    destino.setQtdQuartos(rs.getInt("qtdQuartos"));
                    destino.setCategoriaQuartos(rs.getString("categoriaQuartos"));
                    destinos.add(destino);
                }
            }
        }
        return destinos;
    }

    public void atualizarDestino(destino destino) throws SQLException {
        String sql = "UPDATE Destino SET id_cliente=?, dataPartida=?, horaPartida=?, origem=?, companhiaAereaPartida=?, numVooPartida=?, dataRetorno=?, horaRetorno=?, destino=?, companhiaAereaRetorno=?, numVooRetorno=?, qtdPessoas=?, qtdQuartos=?, categoriaQuartos=? WHERE id=?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, destino.getIdCliente());
            pstmt.setString(2, destino.getDataPartida());
            pstmt.setString(3, destino.getHoraPartida());
            pstmt.setString(4, destino.getOrigem());
            pstmt.setString(5, destino.getCompanhiaAereaPartida());
            pstmt.setString(6, destino.getNumVooPartida());
            pstmt.setString(7, destino.getDataRetorno());
            pstmt.setString(8, destino.getHoraRetorno());
            pstmt.setString(9, destino.getDestino());
            pstmt.setString(10, destino.getCompanhiaAereaRetorno());
            pstmt.setString(11, destino.getNumVooRetorno());
            pstmt.setInt(12, destino.getQtdPessoas());
            pstmt.setInt(13, destino.getQtdQuartos());
            pstmt.setString(14, destino.getCategoriaQuartos());
            pstmt.setInt(15, destino.getId());
            pstmt.executeUpdate();
        }
    }

    public void deletarDestino(int id) throws SQLException {
        String sql = "DELETE FROM Destino WHERE id=?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
    
    // Método para excluir destinos relacionados a um cliente pelo ID do cliente
    public void deletarDestinosDoCliente(int idCliente) throws SQLException {
        String sql = "DELETE FROM Destino WHERE id_cliente = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            stmt.executeUpdate();
        }
    }
}
