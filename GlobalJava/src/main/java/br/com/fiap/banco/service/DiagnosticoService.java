package br.com.fiap.banco.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.banco.bo.DiagnosticoBO;
import br.com.fiap.banco.dao.DiagnosticoDAO;
import br.com.fiap.banco.exception.BadInfoException;
import br.com.fiap.banco.exception.ExcecaoException;
import br.com.fiap.banco.exception.IdNotFoundException;
import br.com.fiap.banco.factory.ConnectionFactory;
import br.com.fiap.banco.model.Diagnostico;

public class DiagnosticoService {


	private DiagnosticoBO diagnosticobo;
	private DiagnosticoDAO diagnosticoDao;

	public DiagnosticoService() throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.getConnection();
		diagnosticoDao = new DiagnosticoDAO(conn);
		diagnosticobo = new DiagnosticoBO();

	}

	public void cadastrar(Diagnostico diagnostico) throws ClassNotFoundException, SQLException, BadInfoException, ExcecaoException {
		diagnosticobo.inserirBO(diagnostico);
	}

	public List<Diagnostico> listar() throws ClassNotFoundException, SQLException {
		return diagnosticoDao.listar();
	}

	public void remover(String idDiagnostico) throws ClassNotFoundException, SQLException, IdNotFoundException {
		diagnosticoDao.remover(idDiagnostico);
	}

	public void atualizar(Diagnostico diagnostico)
			throws ClassNotFoundException, SQLException, IdNotFoundException, BadInfoException {
		diagnosticoDao.atualizar(diagnostico);
	}

}