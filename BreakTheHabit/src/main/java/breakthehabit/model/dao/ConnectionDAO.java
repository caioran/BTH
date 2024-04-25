package breakthehabit.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDAO {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/";

    public static Connection getConnection()
            throws ClassNotFoundException, SQLException
    {
        String nomeBanco = "breakthehabit";
        String usuario = "root";
        String senha = "";
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL+nomeBanco, usuario, senha);
    }
}
