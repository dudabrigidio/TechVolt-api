package br.com.fiap.dao;

import br.com.fiap.exceptions.NaoEncontradoException;
import br.com.fiap.to.VeiculoTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VeiculoDAO extends Repository{

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

    public VeiculoTO inserir(String email, VeiculoTO veiculo) throws NaoEncontradoException {

        veiculo.setIdUsuario(Integer.parseInt(buscarUsuario(email)));
        String sql ="INSERT INTO t_techvolt_veiculo (id_usuario, distancia, combustivel, emissoes_CO2, data_emissao) VALUES (?, ?, ?, ?,?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setInt(1, veiculo.getIdUsuario());
            ps.setDouble(2, veiculo.getDistancia());
            ps.setString(3, veiculo.getCombustivel());
            ps.setDouble(4, veiculo.getEmissoes());
            ps.setDate(5, java.sql.Date.valueOf(veiculo.getData()));

            if (ps.executeUpdate() > 0) {
                return veiculo;
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
