package br.com.fiap.banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.banco.exception.IdNotFoundException;
import br.com.fiap.banco.factory.ConnectionFactory;
import br.com.fiap.banco.model.Cliente;


public class ClienteDAO {

	private Connection conn;

	public ClienteDAO(Connection conn) {
		this.conn = conn;
	}

	public void cadastrar(Cliente cliente) throws ClassNotFoundException, SQLException {
		

		// Criar o objeto com o comando SQL configuravel
		PreparedStatement stm = conn.prepareStatement(
				"INSERT INTO cliente(nomeCliente,generoCliente,enderecoCliente,cpfCliente,idCliente,senha,email,idDispositivo) values (?,?,?,?,?,?,?,?)");
		
		// Setar os valores no comando SQL
		stm.setString(1, cliente.getNome());
		stm.setString(2, cliente.getGenero());
		stm.setString(3, cliente.getEndereco());
		stm.setString(4, cliente.getCpf());
		stm.setString(5, cliente.getIdCliente());
		stm.setString(6, cliente.getSenha());
		stm.setString(7, cliente.getEmail());
		stm.setString(8, cliente.getIdDispositivo());
		
		// Executar o comando SQL
		stm.executeUpdate();
	}

	public List<Cliente> listar() throws ClassNotFoundException, SQLException {
	

		PreparedStatement stm = conn.prepareStatement("select * from cliente");

		ResultSet result = stm.executeQuery();
		List<Cliente> lista = new ArrayList<Cliente>();
             
		while (result.next()) {
			Cliente cliente = parse(result);
			lista.add(cliente);
		}

		return lista;
	}

	private Cliente parse(ResultSet result) throws SQLException {
		String nome = result.getString("nomeCliente");
		String genero = result.getString("generoCliente");
		String endereco = result.getString("enderecoCliente");
		String cpf = result.getString("cpfCliente");
		String idCliente = result.getString("idCliente");
		String senha = result.getString("senha");
		String email = result.getString("email");
		String idDispositivo = result.getString("idDispositivo");
		
		Cliente cliente = new Cliente(nome,genero,endereco,cpf,idCliente,senha,email,idDispositivo);

		return cliente;
	}

	public void remover(String idCliente) throws ClassNotFoundException, SQLException, IdNotFoundException {
		Connection conn = ConnectionFactory.getConnection();
		// PreparedStatement
		PreparedStatement stm = conn.prepareStatement("delete from cliente where idCliente = ?");
		// Setar os parametros na Query
		stm.setString(1, idCliente);
		// Executar a Query
		int linha = stm.executeUpdate();
		if (linha == 0)
			throw new IdNotFoundException("ID do Cliente nao encontrado para remocao");
	}

	public void atualizar(Cliente cliente) throws ClassNotFoundException, SQLException, IdNotFoundException {
		Connection conn = ConnectionFactory.getConnection();
		// PreparedStatement
		PreparedStatement stm = conn.prepareStatement("update cliente set enderecoCliente = ? where idCliente = ?");
		// Setar os parametros na Query
		stm.setString(1, cliente.getEndereco());
		// stm.setDouble(1, 700);
		stm.setString(2, cliente.getIdCliente());
		// Executar a Query
		int linha = stm.executeUpdate();
		if (linha == 0)
			throw new IdNotFoundException("ID do Cliente nao encontrado para atualizar");
	}

}