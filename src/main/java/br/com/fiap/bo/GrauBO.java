package br.com.fiap.bo;

import br.com.fiap.dao.GrauDAO;
import br.com.fiap.exceptions.NaoEncontradoException;
import br.com.fiap.to.GrauTO;
import br.com.fiap.to.RelatorioTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class GrauBO {

    private GrauDAO grauDAO;

    public GrauTO inserir(String email, GrauTO grau) throws NaoEncontradoException {
        grauDAO = new GrauDAO();
        grau.setData(LocalDate.now());
        return grauDAO.inserir(email, grau);
    }

    public boolean verifica(String email) throws NaoEncontradoException {
        grauDAO = new GrauDAO();

        return grauDAO.verifica(email);
    }
}
