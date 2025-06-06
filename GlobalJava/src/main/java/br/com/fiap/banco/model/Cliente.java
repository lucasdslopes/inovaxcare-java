package br.com.fiap.banco.model;

public class Cliente extends Pessoa{

	private String idCliente;
	private String senha;
	private String email;
	private String idDispositivo;
	
	//Construtor Vazio
	public Cliente() {

	}
								
	//Construtor Cheio
	public Cliente(String recebeNome,String recebeGenero,String recebeEndereco,String recebeCpf,String recebeIdCliente,String recebeSenha,String recebeEmail,String recebeIdDispositivo){
		super(recebeNome,recebeGenero,recebeEndereco,recebeCpf);	
		setIdCliente(recebeIdCliente);
		setSenha(recebeSenha);
		setEmail(recebeEmail);
		setIdDispositivo(recebeIdDispositivo);
	}
	
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIdDispositivo() {
		return idDispositivo;
	}
	public void setIdDispositivo(String idDispositivo) {
		this.idDispositivo = idDispositivo;
	}
	
}
