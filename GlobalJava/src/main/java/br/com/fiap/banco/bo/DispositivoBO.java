package br.com.fiap.banco.bo;

import java.sql.Connection;
import java.sql.SQLException;


import br.com.fiap.banco.dao.DispositivoDAO;
import br.com.fiap.banco.exception.ExcecaoException;
import br.com.fiap.banco.factory.ConnectionFactory;
import br.com.fiap.banco.model.Dispositivo;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class DispositivoBO {


	private DispositivoDAO dispositivodao;
	
	public DispositivoBO() throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.getConnection();
        this.dispositivodao = new DispositivoDAO(conn);
    }

	public void inserirBO(Dispositivo dispositivo) throws ExcecaoException, ClassNotFoundException, SQLException {
		if (dispositivo.getIdDispositivo().length() != 5) {
            Response response = Response.status(Response.Status.BAD_REQUEST)
                    .entity("Quantidade de caracteres do IdDispositivo não atende à quantidade necessária")
                    .build();
            throw new WebApplicationException(response);
		} else {
			dispositivo.setIdDispositivo(dispositivo.getIdDispositivo().toUpperCase());
			dispositivodao.cadastrar(dispositivo);
		}
	}



}
