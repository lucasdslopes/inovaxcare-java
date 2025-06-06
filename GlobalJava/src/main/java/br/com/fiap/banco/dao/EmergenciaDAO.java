package br.com.fiap.banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.banco.exception.IdNotFoundException;

import br.com.fiap.banco.model.Emergencia;

public class EmergenciaDAO {

	private Connection conn;

	public EmergenciaDAO(Connection conn) {
		this.conn = conn;
	}

	public void cadastrar(Emergencia emergencia) throws ClassNotFoundException, SQLException {
		
		// Criar o objeto com o comando SQL configuravel
		PreparedStatement stm = conn.prepareStatement(
				"INSERT INTO servicoEmergencia(idServicoEmergencia,tipoServicoEmergencia,tipoEmergencia,medicoResponsavel, gravidade) values (?,?,?,?,?)");

		// Setar os valores no comando SQL
		stm.setString(1, emergencia.getIdServico());
		stm.setString(2, emergencia.getTipoServico());
		stm.setString(3, emergencia.getTipoEmergencia());
		stm.setString(4, emergencia.getMedicoResponsavel());
		stm.setString(5, emergencia.getGravidade());

		// Executar o comando SQL
		stm.executeUpdate();
	}

	public List<Emergencia> listar() throws ClassNotFoundException, SQLException {
		
		PreparedStatement stm = conn.prepareStatement("select * from servicoEmergencia");

		ResultSet result = stm.executeQuery();
		List<Emergencia> lista = new ArrayList<Emergencia>();

		while (result.next()) {
			Emergencia emergencia = parse(result);
			lista.add(emergencia);
		}

		return lista;
	}

	private Emergencia parse(ResultSet result) throws SQLException {

		String idServico = result.getString("idServicoEmergencia");
		String tipoServico = result.getString("tipoServicoEmergencia");
		String tipoEmergencia = result.getString("tipoEmergencia");
		String medicoResponsavel = result.getString("medicoResponsavel");
		String gravidade = result.getString("gravidade");

		Emergencia emergencia = new Emergencia(idServico, tipoServico, tipoEmergencia, medicoResponsavel, gravidade);

		return emergencia;
	}

	public void remover(String idServico) throws ClassNotFoundException, SQLException, IdNotFoundException {
		
		// PreparedStatement
		PreparedStatement stm = conn.prepareStatement("delete from servicoEmergencia where idServicoEmergencia = (?)");
		// Setar os parametros na Query
		stm.setString(1, idServico);
		// Executar a Query
		int linha = stm.executeUpdate();
		if (linha == 0)
			throw new IdNotFoundException("Id do servico nao encontrado para remocao");
	}

	public void atualizar(Emergencia emergencia) throws ClassNotFoundException, SQLException, IdNotFoundException {
		
		// PreparedStatement
		PreparedStatement stm = conn
				.prepareStatement("update servicoEmergencia set medicoResponsavel = ? where idServicoEmergencia = ?");
		// Setar os parametros na Query
		stm.setString(1, emergencia.getMedicoResponsavel());
		// stm.setDouble(1, 700);
		stm.setString(2, emergencia.getIdServico());
		// Executar a Query
		int linha = stm.executeUpdate();
		if (linha == 0)
			throw new IdNotFoundException("Id do servico nao encontrado para atualizar");
	}

}