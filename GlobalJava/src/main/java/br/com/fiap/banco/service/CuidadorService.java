package br.com.fiap.banco.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.banco.bo.CuidadorBO;
import br.com.fiap.banco.dao.CuidadorDAO;
import br.com.fiap.banco.exception.BadInfoException;
import br.com.fiap.banco.exception.ExcecaoException;
import br.com.fiap.banco.exception.IdNotFoundException;
import br.com.fiap.banco.factory.ConnectionFactory;
import br.com.fiap.banco.model.Cuidador;

public class CuidadorService {


	private CuidadorDAO cuidadordao;
	private CuidadorBO cuidadorbo;
	
	public CuidadorService() throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.getConnection();
		cuidadordao = new CuidadorDAO(conn);
		cuidadorbo = new CuidadorBO();

	}

	public void cadastrar(Cuidador cuidador) throws ClassNotFoundException, SQLException, BadInfoException, ExcecaoException {
		cuidadorbo.inserirBO(cuidador);
	}

	public List<Cuidador> listar() throws ClassNotFoundException, SQLException {
		return cuidadordao.listar();
	}

	public void remover(String idCuidador) throws ClassNotFoundException, SQLException, IdNotFoundException {
		cuidadordao.remover(idCuidador);
	}

	public void atualizar(Cuidador cuidador)
			throws ClassNotFoundException, SQLException, IdNotFoundException, BadInfoException {
		cuidadordao.atualizar(cuidador);
	}

}