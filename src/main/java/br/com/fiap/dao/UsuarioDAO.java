package br.com.fiap.dao;

import br.com.fiap.exceptions.NaoEncontradoException;
import br.com.fiap.to.UsuarioTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UsuarioDAO extends Repository{

    public UsuarioTO inserir(UsuarioTO usuario) {
        String sql ="INSERT INTO t_techvolt_usuario (nome, email, senha, data_nascimento, telefone) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.setDate(4, java.sql.Date.valueOf(usuario.getDataNasc()));
            ps.setString(5, usuario.getTelefone());

            if (ps.executeUpdate() > 0) {
                return usuario;
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


    public String alterar(String email, UsuarioTO usuario) throws NaoEncontradoException {
        int idUsuario = Integer.parseInt(buscarUsuario(email));
        String sql ="UPDATE t_techvolt_usuario SET nome=?, email=?, senha=?, data_nascimento=?, telefone=? WHERE id_usuario=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.setDate(4, java.sql.Date.valueOf(usuario.getDataNasc()));
            ps.setString(5, usuario.getTelefone());
            ps.setInt(6, idUsuario);

            if (ps.executeUpdate() > 0) {
                return "Alterado com sucesso";
            } else {
                return "Erro ao alterar";
            }

        } catch (SQLException e) {
            System.out.println( "Erro de SQL: " + e.getMessage());
        }catch (Exception e){
            System.out.println("erro: " +e.getMessage());
        }finally {
            closeConnection();
        }
        return "Erro ao alterar";
    }



    public UsuarioTO listarUsuario(String email) {
        String sql ="SELECT * FROM t_techvolt_usuario WHERE email=?";
        UsuarioTO usuario = new UsuarioTO();
        try (PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setDataNasc(rs.getDate("data_nascimento").toLocalDate());
                usuario.setTelefone(rs.getString("telefone"));
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
        return usuario;
    }

    public boolean excluir(String email) throws NaoEncontradoException {
        excluirEnergia(email);
        excluirAgua(email);
        excluirVeiculo(email);
        excluirGrau(email);
        String sql ="DELETE FROM t_techvolt_usuario WHERE email=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setString(1, email);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println( "Erro de SQL: " + e.getMessage());
        }catch (Exception e){
            System.out.println("erro: " +e.getMessage());
        }finally {
            closeConnection();
        }
        return false;
    }

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

    public void excluirEnergia(String email) throws NaoEncontradoException {
        int idUsuario = Integer.parseInt(buscarUsuario(email));
        String sql ="DELETE FROM t_techvolt_energia WHERE id_usuario=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setInt(1, idUsuario);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println( "Erro de SQL: " + e.getMessage());
        }catch (Exception e){
            System.out.println("erro: " +e.getMessage());
        }finally {
            closeConnection();
        }
    }

    public void excluirAgua(String email) throws NaoEncontradoException {
        int idUsuario = Integer.parseInt(buscarUsuario(email));
        String sql ="DELETE FROM t_techvolt_agua WHERE id_usuario=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setInt(1, idUsuario);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println( "Erro de SQL: " + e.getMessage());
        }catch (Exception e){
            System.out.println("erro: " +e.getMessage());
        }finally {
            closeConnection();
        }
    }

    public void excluirVeiculo(String email) throws NaoEncontradoException {
        int idUsuario = Integer.parseInt(buscarUsuario(email));
        String sql ="DELETE FROM t_techvolt_veiculo WHERE id_usuario=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setInt(1, idUsuario);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println( "Erro de SQL: " + e.getMessage());
        }catch (Exception e){
            System.out.println("erro: " +e.getMessage());
        }finally {
            closeConnection();
        }
    }

    public void excluirGrau(String email) throws NaoEncontradoException {
        int idUsuario = Integer.parseInt(buscarUsuario(email));
        String sql ="DELETE FROM t_techvolt_grau_sustentabilidade WHERE id_usuario=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setInt(1, idUsuario);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println( "Erro de SQL: " + e.getMessage());
        }catch (Exception e){
            System.out.println("erro: " +e.getMessage());
        }finally {
            closeConnection();
        }
    }


}
