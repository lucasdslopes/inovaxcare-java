package br.com.fiap.banco.model;

public class Servico {

	private String idServico;
	private String tipoServico;
	
	//Construtor Vazio
	public Servico() {
		    }
		
	//Construtor Cheio
	public Servico (String recebeIdServico, String recebeTipoServico){
			setIdServico(recebeIdServico);
			setTipoServico(recebeTipoServico);
			}

	public String getIdServico() {
		return idServico;
	}

	public void setIdServico(String idServico) {
		this.idServico = idServico;
	}

	public String getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}
	
}
