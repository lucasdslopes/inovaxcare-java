package br.com.fiap.banco.model;

public class Emergencia extends Servico{

	private String tipoEmergencia;
	private String medicoResponsavel;
	private String gravidade;
	
	//Construtor Vazio
	public Emergencia() {
			    }
			
	//Construtor Cheio
	public Emergencia(String recebeIdServico, String recebeTipoServico,String recebeTipoEmergencia,String recebeMedicoResponsavel,String recebeGravidade){
		super(recebeIdServico,recebeTipoServico);
		setTipoEmergencia(recebeTipoEmergencia);
		setMedicoResponsavel(recebeMedicoResponsavel);
		setGravidade(recebeGravidade);
				}
	
	public String getTipoEmergencia() {
		return tipoEmergencia;
	}
	public void setTipoEmergencia(String tipoEmergencia) {
		this.tipoEmergencia = tipoEmergencia;
	}
	public String getMedicoResponsavel() {
		return medicoResponsavel;
	}
	public void setMedicoResponsavel(String medicoResponsavel) {
		this.medicoResponsavel = medicoResponsavel;
	}
	public String getGravidade() {
		return gravidade;
	}
	public void setGravidade(String gravidade) {
		this.gravidade = gravidade;
	}
	
	
	
}
