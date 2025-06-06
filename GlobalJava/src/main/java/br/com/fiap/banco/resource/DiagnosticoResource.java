package br.com.fiap.banco.resource;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.banco.exception.BadInfoException;
import br.com.fiap.banco.exception.ExcecaoException;
import br.com.fiap.banco.exception.IdNotFoundException;
import br.com.fiap.banco.model.Diagnostico;
import br.com.fiap.banco.service.DiagnosticoService;
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
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.core.UriBuilder;

@Path("/diagnostico")// http://localhost:8080/GlobalJava/api/diagnostico
public class DiagnosticoResource {

	private DiagnosticoService service;

	public DiagnosticoResource() throws ClassNotFoundException, SQLException {
		this.service = new DiagnosticoService();
	}

	//POST http://localhost:8080/GlobalJava/api/diagnostico
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(Diagnostico diagnostico, @Context UriInfo uri) throws ClassNotFoundException, SQLException, ExcecaoException, BadInfoException {
		service.cadastrar(diagnostico);
		UriBuilder uriBuilder = uri.getAbsolutePathBuilder();
		uriBuilder.path((diagnostico.getIdDiagnostico()));
		return Response.created(uriBuilder.build()).build();
	}

	//GET http://localhost:8080/GlobalJava/api/diagnostico
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Diagnostico> lista() throws ClassNotFoundException, SQLException {
		return service.listar();
	}

	//DELETE http://localhost:8080/GlobalJava/api/diagnostico/LC13214
	@DELETE
	@Path("/{idDiagnostico}")
	public Response remover(@PathParam("idDiagnostico") String idDiagnostico) throws ClassNotFoundException, SQLException {
		try {
			service.remover(idDiagnostico);
			return Response.noContent().build();
		} catch (IdNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	// PUT http://localhost:8080/GlobalJava/api/diagnostico/LC48913
	@PUT
	@Path("/{idDiagnostico}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizar(Diagnostico diagnostico, @PathParam("idDiagnostico") String idDiagnostico)
			throws ClassNotFoundException, SQLException {
		try {
			diagnostico.setIdDiagnostico(idDiagnostico);
			service.atualizar(diagnostico);
			return Response.ok().build();
		} catch (IdNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		} catch (BadInfoException e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}
	
}