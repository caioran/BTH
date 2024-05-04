package breakthehabit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    public static boolean verificarSenha(Connection conn, String email, String senha){
        String sql = "SELECT COUNT(*) AS total FROM usuario WHERE email =? and senha = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);
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

