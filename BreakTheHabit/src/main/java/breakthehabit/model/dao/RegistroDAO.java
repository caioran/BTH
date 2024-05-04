package breakthehabit.model.dao;

import java.sql.*;

public class RegistroDAO {



    public static boolean insert(Connection conn, String nome, String dataNasc, String dataComecoFumo, String email,
                                 String senha, String media_cigarro, String meta, String custoDiario, String nivelDependencia) {
        try {
            String sql = "insert into usuario(Nome, data_nascimento, data_comeco_fumo, Email, Senha, media_cigarro, " +
                    "meta, custo_diario, nivel_dependencia)" + "values (?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = conn.prepareStatement(sql);

            // Setar os valores no PreparedStatement
            statement.setString(1, nome);
            statement.setDate(2, java.sql.Date.valueOf(dataNasc));
            statement.setDate(3, java.sql.Date.valueOf(dataComecoFumo));
            statement.setString(4, email);
            statement.setString(5, senha);
            statement.setInt(6, Integer.parseInt(media_cigarro));
            statement.setInt(7, Integer.parseInt(meta));
            statement.setDouble(8, Double.parseDouble(custoDiario));
            statement.setDouble(9, Double.parseDouble(nivelDependencia));

            // Executar a query
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        }catch(SQLException e){
            System.err.println("Erro ao executar a query: " + e.getMessage());
            return false;
        }
    }

    public static boolean verificadorEmail(Connection conn, String email) {
        String sql = "SELECT COUNT(*) AS total FROM usuario WHERE email = ?;";
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
