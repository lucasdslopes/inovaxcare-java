package br.com.fiap.banco.model;

public class Pessoa {

	private String nome;
	private String genero;
	private String endereco;
	private String cpf;
	
	//Construtor Vazio
	public Pessoa() {
						    }
						
	//Construtor Cheio
	public Pessoa(String recebeNome,String recebeGenero,String recebeEndereco,String recebeCpf){
		setGenero(recebeGenero);
		setEndereco(recebeEndereco);
		setCpf(recebeCpf);
		setNome(recebeNome);
		}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}
