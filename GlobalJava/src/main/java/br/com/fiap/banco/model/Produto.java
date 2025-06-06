package br.com.fiap.banco.model;

public class Produto {

	private String cor;
	private String dataFabricacao;
	
	//Construtor Vazio
	public Produto() {
					    }
					
	//Construtor Cheio
	public Produto (String recebeCor, String recebeDataFabricacao){
			setCor(recebeCor);
			setDataFabricacao(recebeDataFabricacao);
	}
	
	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getDataFabricacao() {
		return dataFabricacao;
	}

	public void setDataFabricacao(String dataFabricacao) {
		this.dataFabricacao = dataFabricacao;
	}

	}