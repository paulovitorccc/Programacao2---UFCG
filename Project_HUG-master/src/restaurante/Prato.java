package restaurante;

public class Prato {
	
	private String nome;
	private double preco;
	private String descricao;
	
	public Prato (String nome, double preco, String descricao){
		
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
	}
	
	/** 
	 * o metodo getNome retorna o nome do prato
	 * @return nome - String
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * o metodo setNome tem a funcao de alterar o nome do prato
	 * @param novoNome 
	 */
	public void setNome(String novoNome) {
		this.nome = novoNome;
	}

	/**
	 * o metodo getPreco retorna o preco do prato
	 * @return preco - double
	 */
	public double getPreco() {
		return preco;
	}

	/**
	 * o metodo setPreco tem a funcao de alterar o preco do prato
	 * @param novoPreco
	 */
	public void setPreco(double novoPreco) {
		this.preco = novoPreco;
	}
	
	/**
	 * o metodo getDescricao retorna o nome do prato
	 * @return descricao - String
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * o metodo setDescricao tem a funcao de alterar a descricao do prato
	 * @param novaDescricao
	 */
	public void setDescricao(String novaDescricao) {
		this.descricao = novaDescricao;
	}
	
	/**
	 * o metodo toString retorna a representacao em String da Refeicao
	 */
	public String toString(){
		String stringPrato = String.format("%s, %.2f, %s.",
				this.getNome(), this.getPreco(), this.getDescricao());
		
		return stringPrato;
	}

	/**
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		long temp;
		temp = Double.doubleToLongBits(preco);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/**
	 * no metodo equals comparamos o prato por nome, descricao e preco
	 */
	@Override
	public boolean equals(Object outroObjeto) {	
		
		if (outroObjeto instanceof Prato){
			Prato outroPrato = (Prato) outroObjeto;
			
			if (this.getNome().equalsIgnoreCase(outroPrato.getNome()) 
					&& this.getDescricao().equalsIgnoreCase(outroPrato.getDescricao())
					&& this.getPreco() == outroPrato.getPreco()){
				
				return true;
			}
		}
		
		return false;
	}
	
}
