package br.com.fiap.resource;

import br.com.fiap.bo.LoginBO;

import br.com.fiap.exceptions.AcessoException;
import br.com.fiap.to.LoginTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.util.ArrayList;

@Path("/usuario")
public class LoginResource {
    private LoginBO loginBO = new LoginBO();

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response listarLogin(LoginTO login) throws AcessoException {

        Response.ResponseBuilder response = null;
        if(loginBO.verifica(login)){
            response = Response.ok(); // 200 (Ok)
        } else {
            response = Response.status(404);
            throw new AcessoException("Email ou senha incorretos");
        }
        return response.build();
    }

}