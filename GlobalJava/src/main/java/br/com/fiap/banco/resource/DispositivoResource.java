package br.com.fiap.banco.resource;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.banco.exception.BadInfoException;
import br.com.fiap.banco.exception.ExcecaoException;
import br.com.fiap.banco.exception.IdNotFoundException;
import br.com.fiap.banco.model.Dispositivo;
import br.com.fiap.banco.service.DispositivoService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

@Path("/dispositivo")// http://localhost:8080/GlobalJava/api/dispositivo
public class DispositivoResource {

	private DispositivoService service;

	public DispositivoResource() throws ClassNotFoundException, SQLException {
		this.service = new DispositivoService();
	}

	//POST http://localhost:8080/GlobalJava/api/dispositivo
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(Dispositivo dispositivo, @Context UriInfo uri) throws ClassNotFoundException, SQLException, ExcecaoException, BadInfoException {
		service.cadastrar(dispositivo);
		UriBuilder uriBuilder = uri.getAbsolutePathBuilder();
		uriBuilder.path((dispositivo.getIdDispositivo()));
		return Response.created(uriBuilder.build()).build();
	}

	//GET http://localhost:8080/GlobalJava/api/dispositivo
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Dispositivo> lista() throws ClassNotFoundException, SQLException {
		return service.listar();
	}

	//DELETE http://localhost:8080/GlobalJava/api/dispositivo/JK552080
	@DELETE
	@Path("/{idDispositivo}")
	public Response remover(@PathParam("idDispositivo") String idDispositivo) throws ClassNotFoundException, SQLException {
		try {
			service.remover(idDispositivo);
			return Response.noContent().build();
		} catch (IdNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	// PUT http://localhost:8080/GlobalJava/api/dispositivo/JC058226
	@PUT
	@Path("/{idDispositivo}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizar(Dispositivo dispositivo, @PathParam("idDispositivo") String idDispositivo)
			throws ClassNotFoundException, SQLException {
		try {
			dispositivo.setIdDispositivo(idDispositivo);
			service.atualizar(dispositivo);
			return Response.ok().build();
		} catch (IdNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		} catch (BadInfoException e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}
	
}