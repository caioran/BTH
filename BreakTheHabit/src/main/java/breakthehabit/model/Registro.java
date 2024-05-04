package breakthehabit.model;

import breakthehabit.model.dao.ConnectionDAO;
import breakthehabit.model.dao.RegistroDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class Registro {
    private static String nome;
    private static String email;
    private static String dataNasc;
    private static String senha;
    private static String confirmarSenha;
    private static String dataInicioFumo;
    private static String mediaCigarrosDia;
    private static String reaisGastoDia;
    private static String meta;
    private static String dependencia;

    public static String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getConfirmarSenha() {
        return confirmarSenha;
    }

    public void setConfirmarSenha(String confirmarSenha) {
        this.confirmarSenha = confirmarSenha;
    }

    public String getDataInicioFumo() {
        return dataInicioFumo;
    }

    public void setDataInicioFumo(String dataInicioFumo) {
        this.dataInicioFumo = dataInicioFumo;
    }

    public String getMediaCigarrosDia() {
        return mediaCigarrosDia;
    }

    public void setMediaCigarrosDia(String mediaCigarrosDia) {
        this.mediaCigarrosDia = mediaCigarrosDia;
    }

    public String getReaisGastoDia() {
        return reaisGastoDia;
    }

    public void setReaisGastoDia(String reaisGastoDia) {
        this.reaisGastoDia = reaisGastoDia;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }


    public void primeiraInsercao(String nome, String email, String DataNasc, String senha){
        this.nome = nome;
        this.email = email;
        this.dataNasc = DataNasc;
        this.senha = senha;


    }

    public void segundaInsercao(String dataInicioFumo, String mediaCigarrosDia, String reaisGastoDia, String meta, String dependencia){
        this.dataInicioFumo = dataInicioFumo;
        this.mediaCigarrosDia = mediaCigarrosDia;
        this.reaisGastoDia = reaisGastoDia;
        this.meta = meta;
        this.dependencia = dependencia;

    }
    public boolean inserirNoBD() {
        try {
            Connection conn = ConnectionDAO.getConnection("breakthehabit", "root", "");
            RegistroDAO.insert(conn, this.getNome(), this.getDataNasc(), this.getDataInicioFumo(), this.getEmail(), this.getSenha(),
                    this.getMediaCigarrosDia(), this.getMeta(), this.getReaisGastoDia(), this.getDependencia());
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Não Conectou!");
            e.printStackTrace();
            return false;


        }
    }

}
