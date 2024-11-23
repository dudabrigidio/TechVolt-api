package br.com.fiap.bo;

import br.com.fiap.dao.VeiculoDAO;
import br.com.fiap.exceptions.NaoEncontradoException;
import br.com.fiap.to.VeiculoTO;

import java.time.LocalDate;

public class VeiculoBO {

    private VeiculoDAO veiculoDAO;

    public VeiculoTO inserir(String email, VeiculoTO veiculo) throws NaoEncontradoException {
        veiculoDAO = new VeiculoDAO();

        veiculo.setData(LocalDate.now());

        return veiculoDAO.inserir(email, veiculo);
    }


}
