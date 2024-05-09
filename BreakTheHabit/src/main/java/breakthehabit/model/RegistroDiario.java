package breakthehabit.model;

import breakthehabit.model.dao.ConnectionDAO;
import breakthehabit.model.dao.RegistroDAO;
import breakthehabit.model.dao.RegistroDiarioDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class RegistroDiario {

    private String dataRegistro;
    private String cigarrosFumados;
    private double nivelDoDesejoFumo;

    public String getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(String dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getCigarrosFumados() {
        return cigarrosFumados;
    }

    public void setCigarrosFumados(String cigarrosFumados) {
        this.cigarrosFumados = cigarrosFumados;
    }

    public double getNivelDoDesejoFumo() {
        return nivelDoDesejoFumo;
    }

    public void setNivelDoDesejoFumo(double nivelDoDesejoFumo) {
        this.nivelDoDesejoFumo = nivelDoDesejoFumo;
    }

    public RegistroDiario(String dataRegistro, String cigarrosFumados, double nivelDoDesejoFumo) {
        this.dataRegistro = dataRegistro;
        this.cigarrosFumados = cigarrosFumados;
        this.nivelDoDesejoFumo = nivelDoDesejoFumo;

    }

    public boolean inserirNoBD(){
        try {
            Connection conn = ConnectionDAO.getConnection("breakthehabit", "root", "");
            RegistroDiarioDAO.insert(conn, this.getDataRegistro(), this.getCigarrosFumados(), this.getNivelDoDesejoFumo());
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Não Conectou!");
            e.printStackTrace();
            return false;


        }
    }
}
