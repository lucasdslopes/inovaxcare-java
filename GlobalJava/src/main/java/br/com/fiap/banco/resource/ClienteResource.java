package br.com.fiap.banco.resource;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.banco.exception.BadInfoException;
import br.com.fiap.banco.exception.ExcecaoException;
import br.com.fiap.banco.exception.IdNotFoundException;
import br.com.fiap.banco.model.Cliente;
import br.com.fiap.banco.service.ClienteService;
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

@Path("/cliente") // http://localhost:8080/GlobalJava/api/cliente
public class ClienteResource {

	private ClienteService service;

	public ClienteResource() throws ClassNotFoundException, SQLException {
        this.service = new ClienteService();
    }

	// POST http://localhost:8080/GlobalJava/api/cliente
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(Cliente cliente, @Context UriInfo uri)
			throws ClassNotFoundException, SQLException, BadInfoException, ExcecaoException {
		service.cadastrar(cliente);
		UriBuilder uriBuilder = uri.getAbsolutePathBuilder();
		uriBuilder.path((cliente.getIdCliente()));
		return Response.created(uriBuilder.build()).build();
	}

	// GET http://localhost:8080/GlobalJava/api/cliente
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cliente> lista() throws ClassNotFoundException, SQLException {
		return service.listar();
	}

	// DELETE http://localhost:8080/GlobalJava/api/cliente/LC13214
	@DELETE
	@Path("/{idCliente}")
	public Response remover(@PathParam("idCliente") String idCliente) throws ClassNotFoundException, SQLException {
		try {
			service.remover(idCliente);
			return Response.noContent().build();
		} catch (IdNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	// PUT http://localhost:8080/GlobalJava/api/cliente/LC48913
	@PUT
	@Path("/{idCliente}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizar(Cliente cliente, @PathParam("idCliente") String idCliente)
			throws ClassNotFoundException, SQLException {
		try {
			cliente.setIdCliente(idCliente);
			service.atualizar(cliente);
			return Response.ok().build();
		} catch (IdNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		} catch (BadInfoException e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

}