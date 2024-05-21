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
    private static int contagemCigarros;
    private static int qtdDiasVicio;
    private static int qtdDiasSemFumar;
    private static int mediaDiaria;
    private static double mediaVontadeDiaria;
    private static int qtdDiasSobrio;
    private static double valorEconomizado;

    public static int getQtdDiasSobrio() {
        return qtdDiasSobrio;
    }

    public static void setQtdDiasSobrio(int qtdDiasSobrio) {
        Usuario.qtdDiasSobrio = qtdDiasSobrio;
    }

    public static double getValorEconomizado() {
        return valorEconomizado;
    }

    public static void setValorEconomizado(double valorEconomizado) {
        Usuario.valorEconomizado = valorEconomizado;
    }

    public static int getMediaDiaria() {
        return mediaDiaria;
    }

    public static void setMediaDiaria(int mediaDiaria) {
        Usuario.mediaDiaria = mediaDiaria;
    }

    public static double getMediaVontadeDiaria() {
        return mediaVontadeDiaria;
    }

    public static void setMediaVontadeDiaria(double mediaVontadeDiaria) {
        Usuario.mediaVontadeDiaria = mediaVontadeDiaria;
    }

    public static int getContagemCigarros() {
        return contagemCigarros;
    }

    public static void setContagemCigarros(int contagemCigarros) {
        Usuario.contagemCigarros = contagemCigarros;
    }

    public static int getQtdDiasVicio() {
        return qtdDiasVicio;
    }

    public static void setQtdDiasVicio(int qtdDiasVicio) {
        Usuario.qtdDiasVicio = qtdDiasVicio;
    }

    public static int getQtdDiasSemFumar() {
        return qtdDiasSemFumar;
    }

    public static void setQtdDiasSemFumar(int qtdDiasSemFumar) {
        Usuario.qtdDiasSemFumar = qtdDiasSemFumar;
    }

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
    public static void atribuicaoValoresCalculados(){
        try {
            Connection conn = ConnectionDAO.getConnection("breakthehabit", "root", "");
            UserDAO.getValoresPorID(conn, Usuario.getIdUser());

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Não Conectou!");
            e.printStackTrace();

        }
    }
    public static void atribuicaoMedia(){
        try {
            Connection conn = ConnectionDAO.getConnection("breakthehabit", "root", "");
            UserDAO.calculoMedia(conn, Usuario.getIdUser());

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Não Conectou!");
            e.printStackTrace();

        }
    }
    public static void atualizarDados(String email){
        atribuicaoDosDados(email);
        atribuicaoValoresCalculados();
        atribuicaoMedia();


    }

    public static double calcularGasto(){

        double valorGasto;
        Usuario.atribuicaoValoresCalculados();

        valorGasto = (Usuario.getQtdDiasVicio() * Usuario.getReaisGastoDiaUser());

        return valorGasto;
    }

    public static double calcularEconomia(){
        double valorEconomizado;
        try {
            Connection conn = ConnectionDAO.getConnection("breakthehabit", "root", "");
            UserDAO.verificadorEconomia(conn, getIdUser());
            Usuario.atribuicaoValoresCalculados();
            valorEconomizado = (Usuario.getQtdDiasSobrio() * Usuario.getReaisGastoDiaUser());
            return valorEconomizado;

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Não Conectou!");
            e.printStackTrace();
            return 0;

        }


    }

    public static void atualizarMetricas(){
        calcularEconomia();
        calcularEconomia();
    }

    public static void sairDaAplicacao(){
        setNomeUser("");
        setEmailUser("");
        setDataNascUser("");
        setDataInicioFumoUser("");
        setMediaCigarrosDiaUser(0);
        setReaisGastoDiaUser(0);
        setMetaUser(0);
        setDependenciaUser(0);
        setIdUser(0);
        setContagemCigarros(0);
        setQtdDiasVicio(0);
        setQtdDiasSemFumar(0);
        setMediaDiaria(0);
        setMediaVontadeDiaria(0);
    }
}
