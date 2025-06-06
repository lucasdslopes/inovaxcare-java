package br.com.fiap.banco.bo;

import java.sql.Connection;
import java.sql.SQLException;


import br.com.fiap.banco.dao.EmergenciaDAO;
import br.com.fiap.banco.exception.ExcecaoException;
import br.com.fiap.banco.factory.ConnectionFactory;
import br.com.fiap.banco.model.Emergencia;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class EmergenciaBO {
	

	private EmergenciaDAO emergenciadao;
	
	public EmergenciaBO() throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.getConnection();
        this.emergenciadao = new EmergenciaDAO(conn);
    }

	public void inserirBO(Emergencia emergencia) throws ExcecaoException, ClassNotFoundException, SQLException {
		if (emergencia.getIdServico().length() != 5) {
            Response response = Response.status(Response.Status.BAD_REQUEST)
                    .entity("Quantidade de caracteres do IdServico não atende à quantidade necessária")
                    .build();
            throw new WebApplicationException(response);
		} else {
			emergencia.setIdServico(emergencia.getIdServico().toUpperCase());
			emergenciadao.cadastrar(emergencia);
		}
	}


}