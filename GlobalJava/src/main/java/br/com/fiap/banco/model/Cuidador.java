package br.com.fiap.banco.model;

public class Cuidador extends Pessoa{

	private String idCuidador;
	private int telefone;
	
	//Construtor Vazio
	public Cuidador() {
							    }
							
	//Construtor Cheio
	public Cuidador(String recebeNome,String recebeGenero,String recebeEndereco,String recebeCpf,String recebeIdCuidador,int recebeTelefone){
		super(recebeNome,recebeGenero,recebeEndereco,recebeCpf);	
		setIdCuidador(recebeIdCuidador);
		setTelefone(recebeTelefone);
	
	}
		
	public String getIdCuidador() {
		return idCuidador;
	}
	public void setIdCuidador(String idCuidador) {
		this.idCuidador = idCuidador;
	}
	public int getTelefone() {
		return telefone;
	}
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
		
}
