package br.com.fiap.banco.model;

public class Manutencao extends Servico{

	private float custo;
	private String dataManutencao;
	private String status;
	
	//Construtor Vazio
	public Manutencao() {}
			
	//Construtor Cheio
	public Manutencao(String recebeIdServico, String recebeTipoServico,float recebeCusto,String recebeDataManutencao,String recebeStatus){
		super(recebeIdServico,recebeTipoServico);		
		setCusto(recebeCusto);
		setDataManutencao(recebeDataManutencao);
		setStatus(recebeStatus);
				}
	
	
	public float getCusto() {
		return custo;
	}
	public void setCusto(float custo) {
		this.custo = custo;
	}
	public String getDataManutencao() {
		return dataManutencao;
	}
	public void setDataManutencao(String dataManutencao) {
		this.dataManutencao = dataManutencao;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}