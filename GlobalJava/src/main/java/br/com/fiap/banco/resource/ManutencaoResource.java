package br.com.fiap.banco.resource;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.banco.exception.BadInfoException;
import br.com.fiap.banco.exception.ExcecaoException;
import br.com.fiap.banco.exception.IdNotFoundException;
import br.com.fiap.banco.model.Manutencao;
import br.com.fiap.banco.service.ManutencaoService;
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

@Path("/manutencao") // http://localhost:8080/GlobalJava/api/manutencao
public class ManutencaoResource {

	private ManutencaoService service;
	
	public ManutencaoResource() throws ClassNotFoundException, SQLException {
		this.service = new ManutencaoService();
	}

	// POST http://localhost:8080/GlobalJava/api/manutencao
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(Manutencao manutencao, @Context UriInfo uri)
			throws ClassNotFoundException, SQLException, ExcecaoException, BadInfoException {
		service.cadastrar(manutencao);
		UriBuilder uriBuilder = uri.getAbsolutePathBuilder();
		uriBuilder.path((manutencao.getIdServico()));
		return Response.created(uriBuilder.build()).build();
	}

	// GET http://localhost:8080/GlobalJava/api/manutencao
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Manutencao> lista() throws ClassNotFoundException, SQLException {
		return service.listar();
	}

	// DELETE http://localhost:8080/GlobalJava/api/manutencao/JK552080
	@DELETE
	@Path("/{idServicoManutencao}")
	public Response remover(@PathParam("idServicoManutencao") String idServico)
			throws ClassNotFoundException, SQLException {
		try {
			service.remover(idServico);
			return Response.noContent().build();
		} catch (IdNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	// PUT http://localhost:8080/GlobalJava/api/manutencao/JC058226
	@PUT
	@Path("/{idServicoManutencao}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizar(Manutencao manutencao, @PathParam("idServicoManutencao") String idServico)
			throws ClassNotFoundException, SQLException {
		try {
			manutencao.setIdServico(idServico);
			service.atualizar(manutencao);
			return Response.ok().build();
		} catch (IdNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		} catch (BadInfoException e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

}