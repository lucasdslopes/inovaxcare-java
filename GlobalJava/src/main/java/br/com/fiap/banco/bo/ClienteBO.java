package br.com.fiap.banco.bo;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.fiap.banco.dao.ClienteDAO;
import br.com.fiap.banco.exception.ExcecaoException;
import br.com.fiap.banco.factory.ConnectionFactory;
import br.com.fiap.banco.model.Cliente;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class ClienteBO {


	private ClienteDAO clientedao;
	
	public ClienteBO() throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.getConnection();
        this.clientedao = new ClienteDAO(conn);
    }

	public void inserirBO(Cliente cliente) throws ExcecaoException, ClassNotFoundException, SQLException{
		if ((cliente.getCpf().length() != 14) || (cliente.getIdCliente().length() != 5)
	            || (cliente.getIdDispositivo().length() != 5)) {
	        Response response = Response.status(Response.Status.BAD_REQUEST)
	                .entity("Quantidade de caracteres do cpf e/ou IdDispositivo e/ou IdCliente não atende à quantidade necessária")
	                .build();
	        throw new WebApplicationException(response);
	    }

        if (cliente.getSenha().length() != 8) {
            Response response = Response.status(Response.Status.BAD_REQUEST)
                    .entity("A senha deve ter 8 caracteres.")
                    .build();
            throw new WebApplicationException(response);
		} else {
			cliente.setNome(cliente.getNome().toUpperCase());
			cliente.setIdCliente(cliente.getIdCliente().toUpperCase());
			cliente.setIdDispositivo(cliente.getIdDispositivo().toUpperCase());
			clientedao.cadastrar(cliente);
		}
	}
	
	
}