package br.com.fiap.bo;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.exceptions.MenorIdadeException;
import br.com.fiap.exceptions.NaoEncontradoException;
import br.com.fiap.to.UsuarioTO;

import java.time.LocalDate;
import java.time.Period;


public class UsuarioBO {

    private UsuarioDAO usuarioDAO;

    public UsuarioTO inserir(UsuarioTO usuario) throws MenorIdadeException{
        usuarioDAO = new UsuarioDAO();

        Period period = Period.between(usuario.getDataNasc(), LocalDate.now());
        int anos = period.getYears();
        if(anos < 18) {
            throw new MenorIdadeException("O usuário deve ser maior de 18 anos");
        }

        return usuarioDAO.inserir(usuario);
    }

    public String alterar(String email, UsuarioTO usuario) throws NaoEncontradoException {
        usuarioDAO = new UsuarioDAO();
        //
        return usuarioDAO.alterar(email, usuario);
    }

    public boolean excluir(String email) throws NaoEncontradoException {
        usuarioDAO = new UsuarioDAO();
        //
        return usuarioDAO.excluir(email);
    }

    public UsuarioTO listarUsuario(String email) {
        usuarioDAO = new UsuarioDAO();
        //
        return usuarioDAO.listarUsuario(email);
    }




}
