package breakthehabit.model;

import breakthehabit.model.dao.ConnectionDAO;
import breakthehabit.model.dao.LoginDAO;
import breakthehabit.model.dao.RegistroDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class Login {

    private static String emailLogin;
    private static String senhaLogin;

    public static String getEmailLogin() {
        return emailLogin;
    }

    public static void setEmailLogin(String email) {
        Login.emailLogin = emailLogin;
    }

    public static String getSenhaLogin() {
        return senhaLogin;
    }

    public static void setSenhaLogin(String senha) {
        Login.senhaLogin = senha;
    }

    public Login(String email, String senha) {
       this.emailLogin = email;
       this.senhaLogin = senha;

    }


    public boolean checarLogin(){
        try{
            Connection conn = ConnectionDAO.getConnection("breakthehabit", "root", "");


            return LoginDAO.verificarSenha(conn, emailLogin, senhaLogin);

        }catch(ClassNotFoundException | SQLException e)

        {
            System.out.println("Não Conectou!");
            e.printStackTrace();
            return false;
        }

    }



}
