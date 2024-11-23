package br.com.fiap.dao;

import br.com.fiap.exceptions.NaoEncontradoException;
import br.com.fiap.to.AguaTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AguaDAO extends Repository{

    public String buscarUsuario(String email) throws NaoEncontradoException {
        System.out.println("Email usado na consulta: " + email);
        String sql = "SELECT id_usuario FROM t_techvolt_usuario WHERE email = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int IdUsuario = rs.getInt("id_usuario");
                System.out.println("ID Usuario: " + IdUsuario);
                return String.valueOf(IdUsuario);
            }
        } catch (SQLException e) {
            throw new NaoEncontradoException("Id não encontrado");
        }
        return null;
    }

    public AguaTO inserir(String email, AguaTO agua) throws NaoEncontradoException{

        agua.setIdUsuario(Integer.parseInt(buscarUsuario(email)));
        String sql ="INSERT INTO t_techvolt_agua (id_usuario, quantidade, emissoes_CO2, data_emissao) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setInt(1, agua.getIdUsuario());
            ps.setInt(2, agua.getQuantidade());
            ps.setDouble(3, agua.getEmissoes());
            ps.setDate(4, java.sql.Date.valueOf(agua.getData()));

            if (ps.executeUpdate() > 0) {
                return agua;
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println( "Erro de SQL: " + e.getMessage());
        }catch (Exception e){
            System.out.println("erro: " +e.getMessage());
        }finally {
            closeConnection();
        }
        return null;
    }



}
