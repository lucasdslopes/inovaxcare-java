package br.com.fiap.banco.resource;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.banco.exception.BadInfoException;
import br.com.fiap.banco.exception.ExcecaoException;
import br.com.fiap.banco.exception.IdNotFoundException;
import br.com.fiap.banco.model.Cuidador;
import br.com.fiap.banco.service.CuidadorService;
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

@Path("/cuidador") // http://localhost:8080/GlobalJava/api/cuidador
public class CuidadorResource {

	private CuidadorService service;
	
	public CuidadorResource() throws ClassNotFoundException, SQLException {
		this.service = new CuidadorService();
	
	}

	// POST http://localhost:8080/GlobalJava/api/cuidador
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(Cuidador cuidador, @Context UriInfo uri)
			throws ClassNotFoundException, SQLException, ExcecaoException, BadInfoException {
		service.cadastrar(cuidador);
		UriBuilder uriBuilder = uri.getAbsolutePathBuilder();
		uriBuilder.path((cuidador.getIdCuidador()));
		return Response.created(uriBuilder.build()).build();
	}

	// GET http://localhost:8080/GlobalJava/api/cuidador
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cuidador> lista() throws ClassNotFoundException, SQLException {
		return service.listar();
	}

	// DELETE http://localhost:8080/GlobalJava/api/cuidador/LC13214
	@DELETE
	@Path("/{idCuidador}")
	public Response remover(@PathParam("idCuidador") String idCuidador) throws ClassNotFoundException, SQLException {
		try {
			service.remover(idCuidador);
			return Response.noContent().build();
		} catch (IdNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	// PUT http://localhost:8080/GlobalJava/api/cuidador/LC48913
	@PUT
	@Path("/{idCuidador}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizar(Cuidador cuidador, @PathParam("idCuidador") String idCuidador)
			throws ClassNotFoundException, SQLException {
		try {
			cuidador.setIdCuidador(idCuidador);
			service.atualizar(cuidador);
			return Response.ok().build();
		} catch (IdNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		} catch (BadInfoException e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

}