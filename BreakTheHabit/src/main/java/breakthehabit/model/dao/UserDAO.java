package breakthehabit.model.dao;

import breakthehabit.model.Usuario;

import java.sql.*;

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

}
