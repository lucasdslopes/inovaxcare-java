package br.com.fiap.banco.model;

public class Dispositivo extends Produto{

	private String status;
	private String versao;
	private String idDispositivo;
	
	//Construtor Vazio
	public Dispositivo() {
	
	}
						
	//Construtor Cheio
	public Dispositivo(String recebeCor, String recebeDataFabricacao,String recebeStatus,String recebeVersao,String recebeIdDispositivo){
		super(recebeCor,recebeDataFabricacao);		
		setStatus(recebeStatus);
		setVersao(recebeVersao);
		setIdDispositivo(recebeIdDispositivo);
	}
		
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVersao() {
		return versao;
	}
	public void setVersao(String versao) {
		this.versao = versao;
	}
	public String getIdDispositivo() {
		return idDispositivo;
	}
	public void setIdDispositivo(String idDispositivo) {
		this.idDispositivo = idDispositivo;
	}
	
	
}
