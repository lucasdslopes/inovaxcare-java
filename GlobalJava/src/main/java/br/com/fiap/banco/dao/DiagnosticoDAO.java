package br.com.fiap.banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.banco.exception.IdNotFoundException;
import br.com.fiap.banco.factory.ConnectionFactory;
import br.com.fiap.banco.model.Diagnostico;

public class DiagnosticoDAO {

	private Connection conn;

	public DiagnosticoDAO(Connection conn) {
		this.conn = conn;
	}

	public void cadastrar(Diagnostico diagnostico) throws ClassNotFoundException, SQLException {
		
		// Criar o objeto com o comando SQL configuravel
		PreparedStatement stm = conn.prepareStatement(
				"INSERT INTO diagnostico (estadoGeral,idDiagnostico, sinaisVitais,idDispositivo,idCuidador,idServicoEmergencia,dataSolicitacao,dataEmissao,sinaisQueda) values (?,?,?,?,?,?,?,?,?)");

		// Setar os valores no comando SQL
		stm.setString(1, diagnostico.getEstadoGeral());
		stm.setString(2, diagnostico.getIdDiagnostico());
		stm.setString(3, diagnostico.getSinaisVitais());
		stm.setString(4, diagnostico.getIdDispositivo());
		stm.setString(5, diagnostico.getIdCuidador());
		stm.setString(6, diagnostico.getIdServico());
		stm.setString(7, diagnostico.getDataSolicitacao());
		stm.setString(8, diagnostico.getDataEmissao());
		stm.setString(9, diagnostico.getSinaisQueda());

		// Executar o comando SQL
		stm.executeUpdate();
	}

	public List<Diagnostico> listar() throws ClassNotFoundException, SQLException {
		
		PreparedStatement stm = conn.prepareStatement("select * from diagnostico");

		ResultSet result = stm.executeQuery();
		List<Diagnostico> lista = new ArrayList<Diagnostico>();

		while (result.next()) {
			Diagnostico diagnostico = parse(result);
			lista.add(diagnostico);
		}

		return lista;
	}

	private Diagnostico parse(ResultSet result) throws SQLException {
		String estadoGeral = result.getString("estadoGeral");
		String idDiagnostico = result.getString("idDiagnostico");
		String sinaisVitais = result.getString("sinaisVitais");
		String idDispositivo = result.getString("idDispositivo");
		String idCuidador = result.getString("idCuidador");
		String idServico = result.getString("idServicoEmergencia");
		String dataSolicitacao = result.getString("dataSolicitacao");
		String dataEmissao = result.getString("dataEmissao");
		String sinaisQueda = result.getString("sinaisQueda");

		Diagnostico diagnostico = new Diagnostico(estadoGeral, idDiagnostico, sinaisVitais, idDispositivo, idCuidador,
				idServico, dataSolicitacao, dataEmissao, sinaisQueda);

		return diagnostico;
	}

	public void remover(String idDiagnostico) throws ClassNotFoundException, SQLException, IdNotFoundException {
		
		// PreparedStatement
		PreparedStatement stm = conn.prepareStatement("delete from diagnostico where idDiagnostico = ?");
		// Setar os parametros na Query
		stm.setString(1, idDiagnostico);
		// Executar a Query
		int linha = stm.executeUpdate();
		if (linha == 0)
			throw new IdNotFoundException("ID do Diagnostico nao encontrado para remocao");
	}

	public void atualizar(Diagnostico diagnostico) throws ClassNotFoundException, SQLException, IdNotFoundException {
		Connection conn = ConnectionFactory.getConnection();
		// PreparedStatement
		PreparedStatement stm = conn.prepareStatement("update diagnostico set estadoGeral = ? where idDiagnostico = ?");
		// Setar os parametros na Query
		stm.setString(1, diagnostico.getEstadoGeral());
		// stm.setDouble(1, 700);
		stm.setString(2, diagnostico.getIdDiagnostico());
		// Executar a Query
		int linha = stm.executeUpdate();
		if (linha == 0)
			throw new IdNotFoundException("ID do Diagnostico nao encontrado para atualizar");
	}

}