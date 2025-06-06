package br.com.fiap.banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.banco.exception.IdNotFoundException;
import br.com.fiap.banco.factory.ConnectionFactory;
import br.com.fiap.banco.model.Cuidador;

public class CuidadorDAO {

	private Connection conn;

	public CuidadorDAO(Connection conn) {
		this.conn = conn;
	}

	public void cadastrar(Cuidador cuidador) throws ClassNotFoundException, SQLException {
		
		// Criar o objeto com o comando SQL configuravel
		PreparedStatement stm = conn.prepareStatement(
				"INSERT INTO cuidador (nomeCuidador,generoCuidador,enderecoCuidador,cpfCuidador,idCuidador,numTelefone) values (?,?,?,?,?,?)");
		// Setar os valores no comando SQL
		stm.setString(1, cuidador.getNome());
		stm.setString(2, cuidador.getGenero());
		stm.setString(3, cuidador.getEndereco());
		stm.setString(4, cuidador.getCpf());
		stm.setString(5, cuidador.getIdCuidador());
		stm.setInt(6, cuidador.getTelefone());

		// Executar o comando SQL
		stm.executeUpdate();
	}

	public List<Cuidador> listar() throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stm = conn.prepareStatement("select * from cuidador");

		ResultSet result = stm.executeQuery();
		List<Cuidador> lista = new ArrayList<Cuidador>();

		while (result.next()) {
			Cuidador cuidador = parse(result);
			lista.add(cuidador);
		}

		return lista;
	}

	private Cuidador parse(ResultSet result) throws SQLException {
		String nome = result.getString("nomeCuidador");
		String genero = result.getString("generoCuidador");
		String endereco = result.getString("enderecoCuidador");
		String cpf = result.getString("cpfCuidador");
		String idCuidador = result.getString("idCuidador");
		int telefone = result.getInt("numTelefone");

		Cuidador cuidador = new Cuidador(nome, genero, endereco, cpf, idCuidador, telefone);

		return cuidador;
	}

	public void remover(String idCuidador) throws ClassNotFoundException, SQLException, IdNotFoundException {
		Connection conn = ConnectionFactory.getConnection();
		// PreparedStatement
		PreparedStatement stm = conn.prepareStatement("delete from cuidador where idCuidador = ?");
		// Setar os parametros na Query
		stm.setString(1, idCuidador);
		// Executar a Query
		int linha = stm.executeUpdate();
		if (linha == 0)
			throw new IdNotFoundException("ID do Cuidador nao encontrado para remocao");
	}

	public void atualizar(Cuidador cuidador) throws ClassNotFoundException, SQLException, IdNotFoundException {
		Connection conn = ConnectionFactory.getConnection();
		// PreparedStatement
		PreparedStatement stm = conn.prepareStatement("update cuidador set numTelefone = ? where idCuidador = ?");
		// Setar os parametros na Query
		stm.setInt(1, cuidador.getTelefone());
		// stm.setDouble(1, 700);
		stm.setString(2, cuidador.getIdCuidador());
		// Executar a Query
		int linha = stm.executeUpdate();
		if (linha == 0)
			throw new IdNotFoundException("ID do Cuidador nao encontrado para atualizar");
	}

}