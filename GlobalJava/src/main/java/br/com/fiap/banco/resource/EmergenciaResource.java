package br.com.fiap.banco.resource;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.banco.exception.BadInfoException;
import br.com.fiap.banco.exception.ExcecaoException;
import br.com.fiap.banco.exception.IdNotFoundException;
import br.com.fiap.banco.model.Emergencia;
import br.com.fiap.banco.service.EmergenciaService;
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

@Path("/emergencia") // http://localhost:8080/GlobalJava/api/emergencia
public class EmergenciaResource {

	private EmergenciaService service;

	public EmergenciaResource() throws ClassNotFoundException, SQLException {
		this.service = new EmergenciaService();
	}

	// POST http://localhost:8080/GlobalJava/api/emergencia
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(Emergencia emergencia, @Context UriInfo uri)
			throws ClassNotFoundException, SQLException, ExcecaoException, BadInfoException {
		service.cadastrar(emergencia);
		UriBuilder uriBuilder = uri.getAbsolutePathBuilder();
		uriBuilder.path((emergencia.getIdServico()));
		return Response.created(uriBuilder.build()).build();
	}

	// GET http://localhost:8080/GlobalJava/api/emergencia
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Emergencia> lista() throws ClassNotFoundException, SQLException {
		return service.listar();
	}

	// DELETE http://localhost:8080/GlobalJava/api/emergencia/JK552080
	@DELETE
	@Path("/{idServicoEmergencia}")
	public Response remover(@PathParam("idServicoEmergencia") String idServico)
			throws ClassNotFoundException, SQLException {
		try {
			service.remover(idServico);
			return Response.noContent().build();
		} catch (IdNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	// PUT http://localhost:8080/GlobalJava/api/emergencia/JC058226
	@PUT
	@Path("/{idServicoEmergencia}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizar(Emergencia emergencia, @PathParam("idServicoEmergencia") String idServico)
			throws ClassNotFoundException, SQLException {
		try {
			emergencia.setIdServico(idServico);
			service.atualizar(emergencia);
			return Response.ok().build();
		} catch (IdNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		} catch (BadInfoException e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

}