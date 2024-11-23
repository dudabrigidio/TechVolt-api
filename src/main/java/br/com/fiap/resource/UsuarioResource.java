package br.com.fiap.resource;

import br.com.fiap.bo.UsuarioBO;
import br.com.fiap.exceptions.MenorIdadeException;
import br.com.fiap.exceptions.NaoEncontradoException;
import br.com.fiap.to.UsuarioTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/usuario")
public class UsuarioResource {

    private UsuarioBO usuarioBO = new UsuarioBO();

    @POST
    @Path("/inserir")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserir(@Valid UsuarioTO usuario) throws MenorIdadeException {
        UsuarioTO resultado = usuarioBO.inserir(usuario);
        Response.ResponseBuilder response = null;
        if (resultado != null ){
            response = Response.created(null); //201 - CREATED
        } else {
            response = Response.status(404);// 404 - BAD REQUEST
        }
        response.entity(resultado);
        return response.build();
    }

    @PUT
    @Path("/alterar/{email}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterar(@PathParam("email") String email, @Valid UsuarioTO usuario) throws NaoEncontradoException {
        String resultado = usuarioBO.alterar(email, usuario);
        Response.ResponseBuilder response = null;
        if (resultado != null ){
            response = Response.ok(); //200 - ok
        } else {
            response = Response.status(404); // 404 - BAD REQUEST
        }
        response.entity(resultado);
        return response.build();
    }

    @DELETE
    @Path("/excluir/{email}")
    public Response alterar(@PathParam("email") String email) throws NaoEncontradoException {
        Response.ResponseBuilder response = null;
        if (usuarioBO.excluir(email)){
            response = Response.status(204); //204 - NO CONTENT
        } else {
            response = Response.status(404); // 404 - NOT FOUND
        }
        return response.build();
    }

    @GET
    @Path("/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarUsuario(@PathParam("email") String email) {
        UsuarioTO resultado = usuarioBO.listarUsuario(email);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.ok(); // 200-OK
        } else {
            response= Response.status(404); // 404 - BAD REQUEST
        }
        response.entity(resultado);
        return response.build();
    }
}
