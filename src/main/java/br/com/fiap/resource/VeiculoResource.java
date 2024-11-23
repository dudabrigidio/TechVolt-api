package br.com.fiap.resource;

import br.com.fiap.bo.VeiculoBO;
import br.com.fiap.exceptions.NaoEncontradoException;
import br.com.fiap.to.VeiculoTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/usuario")
public class VeiculoResource {

    private VeiculoBO veiculoBO = new VeiculoBO();

    @POST
    @Path("/veiculo/inserir/{email}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserir(@PathParam("email") String email, @Valid VeiculoTO veiculo) throws NaoEncontradoException {
        veiculo.setEmissoes(veiculo.calcularEmissoes());
        VeiculoTO resultado = null;
        try {
            resultado = veiculoBO.inserir(email, veiculo);
        } catch (NaoEncontradoException e) {
            throw new RuntimeException(e);
        }
        Response.ResponseBuilder response = null;
        if (resultado != null ){
            response = Response.created(null); //201 - CREATED
        } else {
            response = Response.status(404); // 404 - BAD REQUEST
        }
        response.entity(resultado);
        return response.build();
    }


}
