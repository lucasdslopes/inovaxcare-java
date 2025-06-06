package br.com.fiap.banco.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.banco.bo.ClienteBO;
import br.com.fiap.banco.dao.ClienteDAO;
import br.com.fiap.banco.exception.BadInfoException;
import br.com.fiap.banco.exception.ExcecaoException;
import br.com.fiap.banco.exception.IdNotFoundException;
import br.com.fiap.banco.factory.ConnectionFactory;
import br.com.fiap.banco.model.Cliente;



public class ClienteService {
	private ClienteDAO clientedao;
	private ClienteBO clientebo;
	
	public ClienteService() throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.getConnection();
		clientedao = new ClienteDAO(conn);
		clientebo = new ClienteBO();

	}
	
	
	public void cadastrar(Cliente cliente) throws ExcecaoException, ClassNotFoundException, SQLException, BadInfoException {
		
		clientebo.inserirBO(cliente);
	}
	
	
	public List<Cliente> listar() throws ClassNotFoundException, SQLException {
		return clientedao.listar();
	}
	public void remover(String idCliente) throws ClassNotFoundException, SQLException, IdNotFoundException{
		clientedao.remover(idCliente);;
	}
	public void atualizar(Cliente cliente) 
			throws ClassNotFoundException, SQLException, IdNotFoundException, BadInfoException{
		clientedao.atualizar(cliente);
	}
}

	
	

	