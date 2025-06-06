package br.com.fiap.banco.bo;

import java.sql.Connection;
import java.sql.SQLException;


import br.com.fiap.banco.dao.DiagnosticoDAO;
import br.com.fiap.banco.exception.ExcecaoException;
import br.com.fiap.banco.factory.ConnectionFactory;
import br.com.fiap.banco.model.Diagnostico;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class DiagnosticoBO {

	private DiagnosticoDAO diagnosticodao;
	
	public DiagnosticoBO() throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.getConnection();
        this.diagnosticodao = new DiagnosticoDAO(conn);
    }

	public void inserirBO(Diagnostico diagnostico) throws ExcecaoException, ClassNotFoundException, SQLException {
		if ((diagnostico.getIdDiagnostico().length() != 5) || (diagnostico.getIdDispositivo().length() != 5)
                || (diagnostico.getIdCuidador().length() != 5) || diagnostico.getIdServico().length() != 5) {
            Response response = Response.status(Response.Status.BAD_REQUEST)
                    .entity("Quantidade de caracteres do IdDispositivo, IdCuidador, IdServico, IdDiagnostico não atende à quantidade necessária")
                    .build();
            throw new WebApplicationException(response);
		} else {
			diagnostico.setIdDiagnostico(diagnostico.getIdDiagnostico().toUpperCase());
			diagnostico.setIdDispositivo(diagnostico.getIdDispositivo().toUpperCase());
			diagnostico.setIdCuidador(diagnostico.getIdCuidador().toUpperCase());
			diagnostico.setIdServico(diagnostico.getIdServico().toUpperCase());
			diagnosticodao.cadastrar(diagnostico);
		}
	}

	
}