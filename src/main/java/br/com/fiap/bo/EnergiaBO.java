package br.com.fiap.bo;

import br.com.fiap.dao.EnergiaDAO;
import br.com.fiap.exceptions.NaoEncontradoException;
import br.com.fiap.to.EnergiaTO;

import java.time.LocalDate;

public class EnergiaBO {

    private EnergiaDAO energiaDAO;

    public EnergiaTO inserir(String email, EnergiaTO energia) throws NaoEncontradoException {
        energiaDAO = new EnergiaDAO();

        energia.setData(LocalDate.now());

        return energiaDAO.inserir(email, energia);
    }



}
