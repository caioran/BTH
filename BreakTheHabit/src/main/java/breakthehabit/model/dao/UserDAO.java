package breakthehabit.model.dao;

import breakthehabit.model.Usuario;

import java.sql.*;
import java.time.LocalDate;

public class UserDAO {


    public static void selectUserData(Connection conn, String email) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            // Query para selecionar os dados do usuário a partir do email
            String query = "SELECT * FROM Usuario WHERE Email = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                // Armazenando os dados nas variáveis estáticas da classe Usuario
                Usuario.setNomeUser(rs.getString("Nome"));
                Usuario.setEmailUser(rs.getString("Email"));
                Usuario.setDataNascUser(rs.getString("data_nascimento"));
                Usuario.setDataInicioFumoUser(rs.getString("data_comeco_fumo"));
                Usuario.setMediaCigarrosDiaUser(rs.getInt("media_cigarro"));
                Usuario.setReaisGastoDiaUser(rs.getDouble("custo_diario"));
                Usuario.setMetaUser(rs.getInt("meta"));
                Usuario.setDependenciaUser(rs.getDouble("nivel_dependencia"));
                Usuario.setIdUser(rs.getInt("ID_User"));

                System.out.println("Dados do usuário selecionados e armazenados com sucesso.");
            } else {
                System.out.println("Nenhum resultado encontrado para o email informado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void getValoresPorID(Connection conn, int id) {
        String query = "SELECT ContagemCigarros, qtdDiasVicio, qtdDiasSemFumar FROM Usuario WHERE ID_User = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario.setContagemCigarros(rs.getInt("ContagemCigarros"));
                Usuario.setQtdDiasVicio(rs.getInt("qtdDiasVicio"));
                Usuario.setQtdDiasSemFumar(rs.getInt("qtdDiasSemFumar"));

                int qtdDiasVicio = rs.getInt("qtdDiasVicio");
                int qtdDiasSemFumar = rs.getInt("qtdDiasSemFumar");
            } else {
                System.out.println("Nenhum usuário encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar valores por ID: " + e.getMessage());
        }
    }

    public static void calculoMedia(Connection conn, int idUsuario) {
        // Calcular a data de 7 dias atrás
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataSeteDiasAtras = dataAtual.minusDays(7);

        // Consulta para calcular as médias dos últimos 7 dias
        String query = "SELECT AVG(cigarros_fumados) AS media_cigarros, AVG(nivel_desejo_fumar) AS media_nivel_desejo " +
                "FROM RegistroDiario " +
                "WHERE ID_User = ? AND data_registro >= ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idUsuario);
            stmt.setDate(2, java.sql.Date.valueOf(dataSeteDiasAtras));
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario.setMediaDiaria(rs.getInt("media_cigarros"));
                Usuario.setMediaVontadeDiaria(rs.getDouble("media_nivel_desejo"));

            } else {
                System.out.println("Nenhum registro encontrado nos últimos 7 dias para o usuário com ID " + idUsuario);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao calcular médias dos últimos 7 dias: " + e.getMessage());
        }
    }

}
