package br.com.fiap.resource;

import br.com.fiap.bo.RelatorioBO;
import br.com.fiap.to.RelatorioTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
@Path("/usuario")
public class RelatorioResource {

    private RelatorioBO relatorioBO = new RelatorioBO();

    @GET
    @Path("/relatorio/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos(@PathParam("email") String email){
        ArrayList<RelatorioTO> resultado = relatorioBO.listAll(email);
        Response.ResponseBuilder response = null;
        if (resultado != null){
            response = Response.ok(); // 200 (Ok)
        } else {
            response = Response.status(404);
        }
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/calculo/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listHoje(@PathParam("email") String email){
        RelatorioTO resultado = relatorioBO.listHoje(email);
        Response.ResponseBuilder response = null;
        if (resultado != null){
            response = Response.ok(); // 200 (Ok)
        } else {

            response = Response.status(404);
        }
        response.entity(resultado);
        return response.build();
    }
}
