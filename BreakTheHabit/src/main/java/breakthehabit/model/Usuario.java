package breakthehabit.model;

import breakthehabit.model.dao.ConnectionDAO;
import breakthehabit.model.dao.UserDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class Usuario {

    private static String nomeUser;
    private static String emailUser;
    private static String dataNascUser;
    private static String dataInicioFumoUser;
    private static int mediaCigarrosDiaUser;
    private static double reaisGastoDiaUser;
    private static int metaUser;
    private static double dependenciaUser;
    private static int idUser;

    public static int getIdUser() {
        return idUser;
    }

    public static void setIdUser(int idUser) {
        Usuario.idUser = idUser;
    }

    public static String getNomeUser() {
        return nomeUser;
    }

    public static void setNomeUser(String nomeUser) {
        Usuario.nomeUser = nomeUser;
    }

    public static String getEmailUser() {
        return emailUser;
    }

    public static void setEmailUser(String emailUser) {
        Usuario.emailUser = emailUser;
    }

    public static String getDataNascUser() {
        return dataNascUser;
    }

    public static void setDataNascUser(String dataNascUser) {
        Usuario.dataNascUser = dataNascUser;
    }

    public static String getDataInicioFumoUser() {
        return dataInicioFumoUser;
    }

    public static void setDataInicioFumoUser(String dataInicioFumoUser) {
        Usuario.dataInicioFumoUser = dataInicioFumoUser;
    }

    public static int getMediaCigarrosDiaUser() {
        return mediaCigarrosDiaUser;
    }

    public static void setMediaCigarrosDiaUser(int mediaCigarrosDiaUser) {
        Usuario.mediaCigarrosDiaUser = mediaCigarrosDiaUser;
    }

    public static double getReaisGastoDiaUser() {
        return reaisGastoDiaUser;
    }

    public static void setReaisGastoDiaUser(double reaisGastoDiaUser) {
        Usuario.reaisGastoDiaUser = reaisGastoDiaUser;
    }

    public static int getMetaUser() {
        return metaUser;
    }

    public static void setMetaUser(int metaUser) {
        Usuario.metaUser = metaUser;
    }

    public static double getDependenciaUser() {
        return dependenciaUser;
    }

    public static void setDependenciaUser(double dependenciaUser) {
        Usuario.dependenciaUser = dependenciaUser;
    }


    public static void atribuicaoDosDados(String email){

        try {
            Connection conn = ConnectionDAO.getConnection("breakthehabit", "root", "");
            UserDAO.selectUserData(conn, email);

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Não Conectou!");
            e.printStackTrace();

        }
    }
}
