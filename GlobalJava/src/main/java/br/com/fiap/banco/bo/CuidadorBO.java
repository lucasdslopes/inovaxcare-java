package br.com.fiap.banco.bo;

import java.sql.Connection;
import java.sql.SQLException;


import br.com.fiap.banco.dao.CuidadorDAO;
import br.com.fiap.banco.exception.ExcecaoException;
import br.com.fiap.banco.factory.ConnectionFactory;
import br.com.fiap.banco.model.Cuidador;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class CuidadorBO {


	private CuidadorDAO cuidadordao;
	
	public CuidadorBO() throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.getConnection();
        this.cuidadordao = new CuidadorDAO(conn);
    }

	public void inserirBO(Cuidador cuidador) throws ExcecaoException, ClassNotFoundException, SQLException {
		if ((cuidador.getCpf().length() != 14) || (cuidador.getIdCuidador().length() != 5)) {
            Response response = Response.status(Response.Status.BAD_REQUEST)
                    .entity("Quantidade de caracteres do Cpf e/ou IdCuidador não atende à quantidade necessária")
                    .build();
            throw new WebApplicationException(response);
		} 
		
		if (String.valueOf(cuidador.getTelefone()).length() != 9) {
		    Response response = Response.status(Response.Status.BAD_REQUEST)
		            .entity("O telefone do cuidador deve ter  9 dígitos. Ex. 912345678")
		            .build();
		    throw new WebApplicationException(response);
		}
		
		
		else {
			cuidador.setIdCuidador(cuidador.getIdCuidador().toUpperCase());
			cuidador.setCpf(cuidador.getCpf().toUpperCase());
			cuidadordao.cadastrar(cuidador);
		}
	}

}
