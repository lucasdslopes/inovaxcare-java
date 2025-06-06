package br.com.fiap.banco.model;

public class Documento{

	private String dataSolicitacao;
	private String dataEmissao;
	
	//Construtor Vazio
	public Documento() {
				    }
				
	//Construtor Cheio
	public Documento (String recebeDataSolicitacao,String recebeDataEmissao){	
		setDataSolicitacao(recebeDataSolicitacao);
		setDataEmissao(recebeDataEmissao);
	}
	
	
	public String getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(String dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public String getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	
	
	
	
}
