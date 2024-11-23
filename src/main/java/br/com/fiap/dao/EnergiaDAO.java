package br.com.fiap.dao;

import br.com.fiap.exceptions.NaoEncontradoException;
import br.com.fiap.to.EnergiaTO;
import br.com.fiap.to.VeiculoTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


public class EnergiaDAO extends Repository{

    public String buscarUsuario(String email) throws NaoEncontradoException {
        System.out.println("Email usado na consulta: " + email);
        String sql = "SELECT id_usuario FROM t_techvolt_usuario WHERE email = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idUsuario = rs.getInt("id_usuario");
                System.out.println("ID Usuario: " + idUsuario);
                return String.valueOf(idUsuario);
            }
        } catch (SQLException e) {
            throw new NaoEncontradoException("Id não encontrado");
        }
        return null;
    }

    public EnergiaTO inserir(String email, EnergiaTO energia) throws NaoEncontradoException{

        energia.setIdUsuario(Integer.parseInt(buscarUsuario(email)));
        String sql ="INSERT INTO t_techvolt_energia (id_usuario, kWh, emissoes_CO2, data_emissao) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setInt(1, energia.getIdUsuario());
            ps.setInt(2, energia.getKwh());
            ps.setDouble(3, energia.getEmissoes());
            ps.setDate(4, java.sql.Date.valueOf(energia.getData()));

            if (ps.executeUpdate() > 0) {
                return energia;
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
