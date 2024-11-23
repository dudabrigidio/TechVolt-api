package br.com.fiap.resource;

import br.com.fiap.bo.GrauBO;
import br.com.fiap.exceptions.NaoEncontradoException;
import br.com.fiap.to.GrauTO;
import br.com.fiap.to.RelatorioTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/usuario")
public class GrauResource {

    private GrauBO grauBO = new GrauBO();

    @POST
    @Path("/grau/inserir/{email}")
    public Response inserir(@PathParam("email") String email, GrauTO grau) throws NaoEncontradoException {

        GrauTO resultado = null;
        try {
            resultado = grauBO.inserir(email, grau);
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

    @GET
    @Path("verifica/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarDatas(@PathParam("email") String email) throws NaoEncontradoException{
        Response.ResponseBuilder response = null;
        if (grauBO.verifica(email)){
            response = Response.ok(); // 200 (Ok)
        } else {
            response = Response.status(404);
        }
        return response.build();
    }
}
