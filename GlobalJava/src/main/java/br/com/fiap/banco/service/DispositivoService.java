package br.com.fiap.banco.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.banco.bo.DispositivoBO;
import br.com.fiap.banco.dao.DispositivoDAO;
import br.com.fiap.banco.exception.BadInfoException;
import br.com.fiap.banco.exception.ExcecaoException;
import br.com.fiap.banco.exception.IdNotFoundException;
import br.com.fiap.banco.factory.ConnectionFactory;
import br.com.fiap.banco.model.Dispositivo;

public class DispositivoService {

	private DispositivoBO dispositivobo;
	private DispositivoDAO dispositivoDao;

	public DispositivoService() throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.getConnection();
		dispositivoDao = new DispositivoDAO(conn);
		dispositivobo = new DispositivoBO();

	}

	public void cadastrar(Dispositivo dispositivo) throws ClassNotFoundException, SQLException, BadInfoException, ExcecaoException {
		dispositivobo.inserirBO(dispositivo);
	}

	public List<Dispositivo> listar() throws ClassNotFoundException, SQLException {
		return dispositivoDao.listar();
	}

	public void remover(String idDispositivo) throws ClassNotFoundException, SQLException, IdNotFoundException {
		dispositivoDao.remover(idDispositivo);
	}

	public void atualizar(Dispositivo dispositivo)
			throws ClassNotFoundException, SQLException, IdNotFoundException, BadInfoException {
		dispositivoDao.atualizar(dispositivo);
	}

}