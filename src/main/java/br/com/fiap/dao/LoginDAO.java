package br.com.fiap.dao;

import br.com.fiap.to.LoginTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginDAO extends Repository{


    public boolean verifica(LoginTO login){

        String sql = "select email, senha from t_techvolt_usuario where email=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, login.getEmail());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String emailBd = rs.getString("email");
                String senhaBd = rs.getString("senha");
                if (login.getSenha().equals(senhaBd) || login.getEmail().equals(emailBd)) {
                    return true;
                } else {
                    System.out.println("Email ou senha incorretos.");
                    return false;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return false;
    }


}
