package br.com.fiap.bo;


import br.com.fiap.dao.RelatorioDAO;
import br.com.fiap.to.RelatorioTO;


import java.util.ArrayList;

public class RelatorioBO {

    private RelatorioDAO relatorioDAO;

    public ArrayList<RelatorioTO> listAll(String email) {
        relatorioDAO = new RelatorioDAO();
        //
        return relatorioDAO.listAll(email);
    }
    public RelatorioTO listHoje(String email) {
        relatorioDAO = new RelatorioDAO();
        return relatorioDAO.listHoje(email);
    }

}

