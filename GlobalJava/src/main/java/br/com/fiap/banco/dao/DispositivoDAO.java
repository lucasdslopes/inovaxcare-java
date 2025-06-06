package br.com.fiap.banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.banco.exception.IdNotFoundException;
import br.com.fiap.banco.model.Dispositivo;

public class DispositivoDAO {

	private Connection conn;

	public DispositivoDAO(Connection conn) {
		this.conn = conn;
	}

	public void cadastrar(Dispositivo dispositivo) throws ClassNotFoundException, SQLException {
		
		// Criar o objeto com o comando SQL configuravel
		PreparedStatement stm = conn.prepareStatement(
				"INSERT INTO dispositivo (cor,dataFabricacao,statusDispositivo,versao,idDispositivo) values (?,?,?,?,?)");

		// Setar os valores no comando SQL
		stm.setString(1, dispositivo.getCor());
		stm.setString(2, dispositivo.getDataFabricacao());
		stm.setString(3, dispositivo.getStatus());
		stm.setString(4, dispositivo.getVersao());
		stm.setString(5, dispositivo.getIdDispositivo());

		// Executar o comando SQL
		stm.executeUpdate();
	}

	public List<Dispositivo> listar() throws ClassNotFoundException, SQLException {
		
		PreparedStatement stm = conn.prepareStatement("select * from dispositivo");

		ResultSet result = stm.executeQuery();
		List<Dispositivo> lista = new ArrayList<Dispositivo>();

		while (result.next()) {
			Dispositivo dispositivo = parse(result);
			lista.add(dispositivo);
		}

		return lista;
	}

	private Dispositivo parse(ResultSet result) throws SQLException {
		String cor = result.getString("cor");
		String dataFabricacao = result.getString("dataFabricacao");
		String status = result.getString("statusDispositivo");
		String versao = result.getString("versao");
		String idDispositivo = result.getString("idDispositivo");

		Dispositivo dispositivo = new Dispositivo(cor, dataFabricacao, status, versao, idDispositivo);

		return dispositivo;
	}

	public void remover(String idDispositivo) throws ClassNotFoundException, SQLException, IdNotFoundException {
		
		// PreparedStatement
		PreparedStatement stm = conn.prepareStatement("delete from dispositivo where idDispositivo = ?");
		// Setar os parametros na Query
		stm.setString(1, idDispositivo);
		// Executar a Query
		int linha = stm.executeUpdate();
		if (linha == 0)
			throw new IdNotFoundException("ID do Dispositivo nao encontrado para remocao");
	}

	public void atualizar(Dispositivo dispositivo) throws ClassNotFoundException, SQLException, IdNotFoundException {
		
		// PreparedStatement
		PreparedStatement stm = conn.prepareStatement("update dispositivo set versao = ? where idDispositivo = ?");
		// Setar os parametros na Query
		stm.setString(1, dispositivo.getVersao());
		// stm.setDouble(1, 700);
		stm.setString(2, dispositivo.getIdDispositivo());
		// Executar a Query
		int linha = stm.executeUpdate();
		if (linha == 0)
			throw new IdNotFoundException("ID do Dispositivo nao encontrado para atualizar");
	}

}