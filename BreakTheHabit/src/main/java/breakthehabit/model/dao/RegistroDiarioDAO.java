package breakthehabit.model.dao;

import breakthehabit.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistroDiarioDAO {
    public static boolean insert(Connection conn, String dataRegistro, String cigarrosFumados, double nivelDoDesejoFumo) {
        try {
            String sql = "insert into registrodiario(nivel_desejo_fumar, cigarros_fumados, data_registro, ID_User) " +
                         "values (?, ?, ?, ?);";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setDouble(1, nivelDoDesejoFumo);
            stmt.setInt(2, Integer.parseInt(cigarrosFumados));
            stmt.setDate(3, java.sql.Date.valueOf(dataRegistro));
            stmt.setInt(4, Usuario.getIdUser());


            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        }catch(SQLException e){
            System.err.println("Erro ao executar a query: " + e.getMessage());
            return false;
        }
    }
    public static boolean verificadorData(Connection conn, String data) {
        String sql = "SELECT COUNT(*) AS total FROM registrodiario WHERE data_registro = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, java.sql.Date.valueOf(data));
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
