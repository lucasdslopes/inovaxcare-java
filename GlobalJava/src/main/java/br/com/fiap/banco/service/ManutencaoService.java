package br.com.fiap.banco.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.banco.bo.ManutencaoBO;
import br.com.fiap.banco.dao.ManutencaoDAO;
import br.com.fiap.banco.exception.BadInfoException;
import br.com.fiap.banco.exception.ExcecaoException;
import br.com.fiap.banco.exception.IdNotFoundException;
import br.com.fiap.banco.factory.ConnectionFactory;
import br.com.fiap.banco.model.Manutencao;

public class ManutencaoService {

	private ManutencaoDAO manutencaoDao;
	private ManutencaoBO manutencaobo;

	public ManutencaoService() throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.getConnection();
		manutencaoDao = new ManutencaoDAO(conn);
		manutencaobo = new ManutencaoBO();

	}

	public void cadastrar(Manutencao manutencao) throws ClassNotFoundException, SQLException, BadInfoException, ExcecaoException {
		manutencaobo.inserirBO(manutencao);
	}

	public List<Manutencao> listar() throws ClassNotFoundException, SQLException {
		return manutencaoDao.listar();
	}

	public void remover(String idServico) throws ClassNotFoundException, SQLException, IdNotFoundException {
		manutencaoDao.remover(idServico);
	}

	public void atualizar(Manutencao manutencao)
			throws ClassNotFoundException, SQLException, IdNotFoundException, BadInfoException {
		manutencaoDao.atualizar(manutencao);
	}

	
}