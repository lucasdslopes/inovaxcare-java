package br.com.fiap.banco.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.banco.bo.EmergenciaBO;
import br.com.fiap.banco.dao.EmergenciaDAO;
import br.com.fiap.banco.exception.BadInfoException;
import br.com.fiap.banco.exception.ExcecaoException;
import br.com.fiap.banco.exception.IdNotFoundException;
import br.com.fiap.banco.factory.ConnectionFactory;
import br.com.fiap.banco.model.Emergencia;

public class EmergenciaService {

	private EmergenciaDAO emergenciaDao;
	private EmergenciaBO emergenciabo;

	public EmergenciaService() throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.getConnection();
		emergenciaDao = new EmergenciaDAO(conn);
		emergenciabo = new EmergenciaBO();

	}

	public void cadastrar(Emergencia emergencia) throws ClassNotFoundException, SQLException, BadInfoException, ExcecaoException {
		emergenciabo.inserirBO(emergencia);
	}

	public List<Emergencia> listar() throws ClassNotFoundException, SQLException {
		return emergenciaDao.listar();
	}

	public void remover(String idServico) throws ClassNotFoundException, SQLException, IdNotFoundException {
		emergenciaDao.remover(idServico);
	}

	public void atualizar(Emergencia emergencia)
			throws ClassNotFoundException, SQLException, IdNotFoundException, BadInfoException {
		emergenciaDao.atualizar(emergencia);
	}

	
}