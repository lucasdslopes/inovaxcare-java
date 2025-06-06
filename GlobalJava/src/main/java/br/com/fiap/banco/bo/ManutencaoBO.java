package br.com.fiap.banco.bo;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.fiap.banco.dao.ManutencaoDAO;
import br.com.fiap.banco.exception.ExcecaoException;
import br.com.fiap.banco.factory.ConnectionFactory;
import br.com.fiap.banco.model.Manutencao;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class ManutencaoBO {


	private ManutencaoDAO manutencaodao;
	
	public ManutencaoBO() throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.getConnection();
        this.manutencaodao = new ManutencaoDAO(conn);
    }

	public void inserirBO(Manutencao manutencao) throws ExcecaoException, ClassNotFoundException, SQLException {
		if (manutencao.getIdServico().length() != 5) {
            Response response = Response.status(Response.Status.BAD_REQUEST)
                    .entity("Quantidade de caracteres do IdServico não atende à quantidade necessária")
                    .build();
            throw new WebApplicationException(response);
		} else {
			manutencao.setIdServico(manutencao.getIdServico().toUpperCase());
			manutencaodao.cadastrar(manutencao);
		}
	}

	
}