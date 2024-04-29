package breakthehabit.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class RegistroDAO {

    public boolean verificarEmail() {
        try{
            Connection conn = ConnectionDAO.getConnection("breakthehabit", "root", "");

        } catch (ClassNotFoundException | SQLException e){
            System.out.println("Não Conectou!");
            e.printStackTrace();
        }
        return false;
    }

    public static boolean insert(Connection conn, String nome, String email)
            throws SQLException {

        String sql = String.format("insert into usuario(nome, email)" + "values('%s', '%s');", nome, email);
        Statement cmd = conn.createStatement();
        cmd.executeUpdate(sql);
        return true;
    }
}
