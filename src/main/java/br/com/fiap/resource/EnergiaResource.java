package br.com.fiap.resource;

import br.com.fiap.bo.EnergiaBO;
import br.com.fiap.exceptions.NaoEncontradoException;
import br.com.fiap.to.EnergiaTO;
import br.com.fiap.to.UsuarioTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;

@Path("/usuario")
public class EnergiaResource {

    private EnergiaBO energiaBO = new EnergiaBO();

    @POST
    @Path("/energia/inserir/{email}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserir(@PathParam ("email") String email, @Valid EnergiaTO energia) throws NaoEncontradoException {
        System.out.println(energia.getKwh());
        energia.setEmissoes(energia.calcularEmissoes());
        EnergiaTO resultado = null;
        try {
            resultado = energiaBO.inserir(email, energia);
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
