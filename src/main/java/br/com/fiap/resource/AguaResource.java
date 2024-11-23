package br.com.fiap.resource;

import br.com.fiap.bo.AguaBO;
import br.com.fiap.exceptions.NaoEncontradoException;
import br.com.fiap.to.AguaTO;
import br.com.fiap.to.EnergiaTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/usuario")
public class AguaResource {

    private AguaBO aguaBO = new AguaBO();

    @POST
    @Path("/agua/inserir/{email}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserir(@PathParam("email") String email, @Valid AguaTO agua) throws NaoEncontradoException {
        agua.setEmissoes(agua.calcularEmissoes());
        AguaTO resultado = null;
        try {
            resultado = aguaBO.inserir(email, agua);
        } catch (NaoEncontradoException e) {
            throw new RuntimeException(e);
        }

        Response.ResponseBuilder response = null;
        if (resultado != null ){
            response = Response.created(null); //201 - CREATED
        } else {
            response = Response.status(404);// 404 - BAD REQUEST
        }
        response.entity(resultado);
        return response.build();
    }


}
