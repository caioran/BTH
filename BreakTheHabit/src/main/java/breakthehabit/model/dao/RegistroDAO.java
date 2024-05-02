package breakthehabit.model.dao;

import java.sql.*;

public class RegistroDAO {



    public static boolean insert(Connection conn, String nome, String email)
            throws SQLException {

        String sql = String.format("insert into usuario(nome, email)" + "values('%s', '%s');", nome, email);
        Statement cmd = conn.createStatement();
        cmd.executeUpdate(sql);
        return true;
    }

    public static boolean verificadorEmail(Connection conn, String email) {
        String sql = "SELECT COUNT(*) AS total FROM usuario WHERE email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int total = rs.getInt("total");
                return total > 0; // Se o total for maior que zero, o email já existe
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar se o email existe: " + e.getMessage());
        }
        return false;
    }
}
