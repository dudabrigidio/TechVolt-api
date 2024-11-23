package br.com.fiap.bo;

import br.com.fiap.dao.AguaDAO;
import br.com.fiap.dao.EnergiaDAO;
import br.com.fiap.exceptions.NaoEncontradoException;
import br.com.fiap.to.AguaTO;
import br.com.fiap.to.EnergiaTO;

import java.time.LocalDate;

public class AguaBO {

    private AguaDAO aguaDAO;

    public AguaTO inserir(String email, AguaTO agua) throws NaoEncontradoException {
        aguaDAO = new AguaDAO();

        agua.setData(LocalDate.now());
        return aguaDAO.inserir(email, agua);
    }


}
