package br.com.fiap.dao;

import br.com.fiap.exceptions.NaoEncontradoException;
import br.com.fiap.to.GrauTO;
import br.com.fiap.to.RelatorioTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class GrauDAO extends Repository{

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

    public String buscarEmissao(int idUsuario) throws NaoEncontradoException{

        System.out.println("entrei aqui - idUsuario: " + idUsuario);

        String sql = "SELECT \n" +
                "    e.emissoes_CO2 AS energia_emissoes_CO2,\n" +
                "    v.emissoes_CO2 AS veiculo_emissoes_CO2,\n" +
                "    a.emissoes_CO2 AS agua_emissoes_CO2\n" +
                "FROM \n" +
                "    t_techvolt_energia e\n" +
                "LEFT JOIN \n" +
                "    t_techvolt_veiculo v ON e.data_emissao = v.data_emissao AND e.id_usuario = v.id_usuario\n" +
                "LEFT JOIN \n" +
                "    t_techvolt_agua a ON e.data_emissao = a.data_emissao AND e.id_usuario = a.id_usuario\n" +
                "WHERE \n" +
                "    e.data_emissao = ?\n" +
                "    AND \n" +
                "    e.id_usuario = ?\n";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setDate(1,  java.sql.Date.valueOf(LocalDate.now()));
            ps.setInt(2,  idUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                GrauTO grau = new GrauTO();
                grau.setEmissaoEnergia(rs.getInt("energia_emissoes_CO2"));
                System.out.println("emissao energia: " + grau.getEmissaoEnergia());
                grau.setEmissaoAgua(rs.getInt("agua_emissoes_CO2"));
                System.out.println("emissao agua: " + grau.getEmissaoAgua());
                grau.setEmissaoVeiculo(rs.getInt("veiculo_emissoes_CO2"));
                System.out.println("emissao veiculo: " + grau.getEmissaoVeiculo());

                double soma = grau.soma();
                System.out.println("Soma: " + soma);
                return String.valueOf(soma);
            }
        } catch (SQLException e) {
            throw new NaoEncontradoException("Emissão não encontrada");
        }
        return null;
    }




    public GrauTO inserir(String email, GrauTO grau) throws NaoEncontradoException{

        grau.setIdUsuario(Integer.parseInt(buscarUsuario(email)));
        grau.setSoma(Double.parseDouble(buscarEmissao(grau.getIdUsuario())));
        grau.setPorcentagem(grau.calcularPorcentagem(grau.getSoma()));

        System.out.println(grau.getPorcentagem());
        String sql ="INSERT INTO t_techvolt_grau_sustentabilidade (id_usuario, porcentagem, soma_emissao, data_emissao) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setInt(1, grau.getIdUsuario());
            ps.setDouble(2, grau.getPorcentagem());
            ps.setDouble(3, grau.getSoma());
            ps.setDate(4, java.sql.Date.valueOf(grau.getData()));

            if (ps.executeUpdate() > 0) {
                return grau;
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


    public boolean verifica(String email) throws NaoEncontradoException{

        int idUsuario = Integer.parseInt(buscarUsuario(email));

        String sql = "SELECT data_emissao FROM t_techvolt_grau_sustentabilidade WHERE id_usuario = ?";

        ArrayList<LocalDate> listaDatas = new ArrayList<>();

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                listaDatas.add(rs.getDate("data_emissao").toLocalDate());
            }

            for (LocalDate data : listaDatas) {
                if (data.equals(LocalDate.now())) {
                    return true;
                }
            }
            return false;

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            closeConnection();
        }

        return false;
    }


}
