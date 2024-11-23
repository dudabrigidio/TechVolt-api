package br.com.fiap.bo;

import br.com.fiap.dao.LoginDAO;
import br.com.fiap.exceptions.AcessoException;
import br.com.fiap.to.LoginTO;

public class LoginBO {

    private LoginDAO loginDAO;

    public boolean verifica(LoginTO login) throws AcessoException {
        loginDAO = new LoginDAO();

        if (!loginDAO.verifica(login)){
            throw new AcessoException("Email ou senha incorretos");
        }
        return loginDAO.verifica(login);
    }

}
