package br.com.fiap.dao;

import br.com.fiap.to.RelatorioTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class RelatorioDAO extends Repository{

    public String buscarUsuario(String email) {
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
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<RelatorioTO> listAll(String email) {

        int idUsuario = Integer.parseInt(buscarUsuario(email));

        String sql = "SELECT u.id_usuario, u.nome, u.email, u.data_nascimento, u.telefone, " +
                "e.kWh AS energia_kWh, e.emissoes_CO2 AS energia_emissoes_CO2, " +
                "v.distancia AS veiculo_distancia, v.combustivel AS veiculo_combustivel, " +
                "v.emissoes_CO2 AS veiculo_emissoes_CO2, " +
                "a.quantidade AS agua_quantidade, a.emissoes_CO2 AS agua_emissoes_CO2, " +
                "g.soma_emissao AS grau_sustentabilidade_soma, g.porcentagem AS grau_sustentabilidade_porcentagem, " +
                "g.data_emissao AS grau_sustentabilidade_data_emissao " +
                "FROM t_techvolt_usuario u " +
                "LEFT JOIN t_techvolt_energia e ON u.id_usuario = e.id_usuario " +
                "LEFT JOIN t_techvolt_veiculo v ON u.id_usuario = v.id_usuario " +
                "LEFT JOIN t_techvolt_agua a ON u.id_usuario = a.id_usuario " +
                "LEFT JOIN t_techvolt_grau_sustentabilidade g ON u.id_usuario = g.id_usuario " +
                "WHERE u.id_usuario = ?";

        ArrayList<RelatorioTO> listaRelatorio = new ArrayList<>();

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                RelatorioTO relatorio = new RelatorioTO();
                relatorio.setIdUsuario(rs.getInt("id_usuario"));
                relatorio.setNome(rs.getString("nome"));
                relatorio.setEmail(rs.getString("email"));
                relatorio.setDataNasc(rs.getDate("data_nascimento").toLocalDate());
                relatorio.setTelefone(rs.getString("telefone"));
                relatorio.setEnergiaKwh(rs.getInt("energia_kWh"));
                relatorio.setEnergiaEmissoes(rs.getDouble("energia_emissoes_CO2"));
                relatorio.setDistanciaKm(rs.getInt("veiculo_distancia"));
                relatorio.setCombustivel(rs.getString("veiculo_combustivel"));
                relatorio.setVeiculoEmissoes(rs.getDouble("veiculo_emissoes_CO2"));
                relatorio.setQuantidadeL(rs.getInt("agua_quantidade"));
                relatorio.setAguaEmissoes(rs.getDouble("agua_emissoes_CO2"));
                relatorio.setGrauSustentab(rs.getDouble("grau_sustentabilidade_porcentagem"));
                relatorio.setSomaEmissao(rs.getDouble("grau_sustentabilidade_soma"));
                relatorio.setData(rs.getDate("grau_sustentabilidade_data_emissao").toLocalDate());

                listaRelatorio.add(relatorio);
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return listaRelatorio;
    }

    public RelatorioTO listHoje(String email) {

        int idUsuario = Integer.parseInt(buscarUsuario(email));
        RelatorioTO relatorio = new RelatorioTO();
        String sql = "SELECT DISTINCT\n" +
                "    u.id_usuario, \n" +
                "    u.nome, \n" +
                "    u.email, \n" +
                "    u.data_nascimento, \n" +
                "    u.telefone, \n" +
                "    e.kWh AS energia_kWh, \n" +
                "    e.emissoes_CO2 AS energia_emissoes_CO2, \n" +
                "    v.distancia AS veiculo_distancia, \n" +
                "    v.combustivel AS veiculo_combustivel, \n" +
                "    v.emissoes_CO2 AS veiculo_emissoes_CO2, \n" +
                "    a.quantidade AS agua_quantidade, \n" +
                "    a.emissoes_CO2 AS agua_emissoes_CO2, \n" +
                "    g.soma_emissao AS grau_sustentabilidade_soma, \n" +
                "    g.porcentagem AS grau_sustentabilidade_porcentagem, \n" +
                "    g.data_emissao AS grau_sustentabilidade_data_emissao\n" +
                "FROM \n" +
                "    t_techvolt_usuario u\n" +
                "LEFT JOIN \n" +
                "    t_techvolt_energia e ON u.id_usuario = e.id_usuario\n" +
                "LEFT JOIN \n" +
                "    t_techvolt_veiculo v ON u.id_usuario = v.id_usuario\n" +
                "LEFT JOIN \n" +
                "    t_techvolt_agua a ON u.id_usuario = a.id_usuario\n" +
                "LEFT JOIN \n" +
                "    t_techvolt_grau_sustentabilidade g ON u.id_usuario = g.id_usuario\n" +
                "WHERE \n" +
                "    u.id_usuario = ?\n" +
                "    AND g.data_emissao = ?";


        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            System.out.println(LocalDate.now());
            ps.setDate(2, Date.valueOf(LocalDate.now()));
            ResultSet rs = ps.executeQuery();


            if (rs.next()) {

                relatorio.setIdUsuario(rs.getInt("id_usuario"));
                relatorio.setNome(rs.getString("nome"));
                relatorio.setEmail(rs.getString("email"));
                relatorio.setDataNasc(rs.getDate("data_nascimento").toLocalDate());
                relatorio.setTelefone(rs.getString("telefone"));
                relatorio.setEnergiaKwh(rs.getInt("energia_kWh"));
                relatorio.setEnergiaEmissoes(rs.getDouble("energia_emissoes_CO2"));
                relatorio.setDistanciaKm(rs.getInt("veiculo_distancia"));
                relatorio.setCombustivel(rs.getString("veiculo_combustivel"));
                relatorio.setVeiculoEmissoes(rs.getDouble("veiculo_emissoes_CO2"));
                relatorio.setQuantidadeL(rs.getInt("agua_quantidade"));
                relatorio.setAguaEmissoes(rs.getDouble("agua_emissoes_CO2"));
                relatorio.setGrauSustentab(rs.getDouble("grau_sustentabilidade_porcentagem"));
                relatorio.setSomaEmissao(rs.getDouble("grau_sustentabilidade_soma"));
                relatorio.setData(rs.getDate("grau_sustentabilidade_data_emissao").toLocalDate());
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return relatorio;
    }








































}
