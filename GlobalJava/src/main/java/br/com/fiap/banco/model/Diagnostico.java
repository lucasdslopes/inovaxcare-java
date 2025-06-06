package br.com.fiap.banco.model;

public class Diagnostico extends Documento{

	private String estadoGeral;
	private String idDiagnostico;
	private String sinaisVitais;
	private String idDispositivo;
	private String idCuidador;
	private String idServico;
	private String sinaisQueda;
	
	
	//Construtor Vazio
	public Diagnostico() {
			    }
			
	//Construtor Cheio
	public Diagnostico (String recebeEstadoGeral,String recebeIdDiagnostico,String recebeSinaisVitais,String recebeIdDispositivo,
			String recebeIdCuidador,String recebeIdServico,String recebeDataSolicitacao,String recebeDataEmissao,String recebeSinaisQueda){
		super(recebeDataSolicitacao,recebeDataEmissao);
		setEstadoGeral(recebeEstadoGeral);
		setIdDiagnostico(recebeIdDiagnostico);
		setSinaisVitais(recebeSinaisVitais);
		setIdServico(recebeIdServico);
		setIdCuidador(recebeIdCuidador);
		setIdDispositivo(recebeIdDispositivo);
		setSinaisQueda(recebeSinaisQueda);
	}
	
	
	
	public String getSinaisQueda() {
		return sinaisQueda;
	}

	public void setSinaisQueda(String sinaisQueda) {
		this.sinaisQueda = sinaisQueda;
	}

	public String getIdDispositivo() {
		return idDispositivo;
	}

	public void setIdDispositivo(String idDispositivo) {
		this.idDispositivo = idDispositivo;
	}

	public String getIdCuidador() {
		return idCuidador;
	}

	public void setIdCuidador(String idCuidador) {
		this.idCuidador = idCuidador;
	}

	public String getIdServico() {
		return idServico;
	}

	public void setIdServico(String idServico) {
		this.idServico = idServico;
	}

	public String getEstadoGeral() {
		return estadoGeral;
	} 
	public void setEstadoGeral(String estadoGeral) {
		this.estadoGeral = estadoGeral;
	}
	public String getIdDiagnostico() {
		return idDiagnostico;
	}
	public void setIdDiagnostico(String idDiagnostico) {
		this.idDiagnostico = idDiagnostico;
	}
	public String getSinaisVitais() {
		return sinaisVitais;
	}
	public void setSinaisVitais(String sinaisVitais) {
		this.sinaisVitais = sinaisVitais;
	}
	
}
