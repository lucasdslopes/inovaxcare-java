package br.com.fiap.banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.banco.exception.IdNotFoundException;

import br.com.fiap.banco.model.Manutencao;

public class ManutencaoDAO {

	private Connection conn;

	public ManutencaoDAO(Connection conn) {
		this.conn = conn;
	}

	public void cadastrar(Manutencao manutencao) throws ClassNotFoundException, SQLException {
		
		// Criar o objeto com o comando SQL configuravel
		PreparedStatement stm = conn.prepareStatement(
				"INSERT INTO servicoManutencao(idServicoManutencao,tipoServicoManutencao,custo,dataManutencao,statusManutencao) values (?,?,?,?,?)");

		// Setar os valores no comando SQL
		stm.setString(1, manutencao.getIdServico());
		stm.setString(2, manutencao.getTipoServico());
		stm.setFloat(3, manutencao.getCusto());
		stm.setString(4, manutencao.getDataManutencao());
		stm.setString(5, manutencao.getStatus());

		// Executar o comando SQL
		stm.executeUpdate();
	}

	public List<Manutencao> listar() throws ClassNotFoundException, SQLException {
		
		PreparedStatement stm = conn.prepareStatement("select * from servicoManutencao");

		ResultSet result = stm.executeQuery();
		List<Manutencao> lista = new ArrayList<Manutencao>();

		while (result.next()) {
			Manutencao manutencao = parse(result);
			lista.add(manutencao);
		}

		return lista;
	}

	private Manutencao parse(ResultSet result) throws SQLException {

		String idServico = result.getString("idServicoManutencao");
		String tipoServico = result.getString("tipoServicoManutencao");
		float custo = result.getFloat("custo");
		String dataManutencao = result.getString("dataManutencao");
		String status = result.getString("statusManutencao");

		Manutencao manutencao = new Manutencao(idServico, tipoServico, custo, dataManutencao, status);

		return manutencao;
	}

	public void remover(String idServico) throws ClassNotFoundException, SQLException, IdNotFoundException {
		
		// PreparedStatement
		PreparedStatement stm = conn.prepareStatement("delete from servicoManutencao where idServicoManutencao = (?)");
		// Setar os parametros na Query
		stm.setString(1, idServico);
		// Executar a Query
		int linha = stm.executeUpdate();
		if (linha == 0)
			throw new IdNotFoundException("Id do servico nao encontrado para remocao");
	}

	public void atualizar(Manutencao manutencao) throws ClassNotFoundException, SQLException, IdNotFoundException {
	
		// PreparedStatement
		PreparedStatement stm = conn
				.prepareStatement("update servicoManutencao set statusManutencao = ? where idServicoManutencao = ?");
		// Setar os parametros na Query
		stm.setString(1, manutencao.getStatus());
		// stm.setDouble(1, 700);
		stm.setString(2, manutencao.getIdServico());
		// Executar a Query
		int linha = stm.executeUpdate();
		if (linha == 0)
			throw new IdNotFoundException("Id do servico nao encontrado para atualizar");
	}

}
