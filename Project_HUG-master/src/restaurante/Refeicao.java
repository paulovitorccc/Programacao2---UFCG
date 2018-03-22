package restaurante;

import java.util.List;

import exceptions.RefeicaoException;
import exceptions.SistemaException;

public class Refeicao {
	
	private final double DESCONTO = 0.9;
	private final String ESPACO = " ";
	private final int TAMANHO_MIN_REFEICAO = 3;
	private final int TAMANHO_MAX_REFEICAO = 4;
			
	private String nome;
	private String descricao;
	private List<Prato> listaPratos;
	
	public Refeicao (String nome, String descricao, List<Prato> pratos) 
			throws SistemaException{
		
		verificaTamanhoRefeicao(pratos);
		
		this.nome = nome;
		this.descricao = descricao;
		this.listaPratos = pratos;
	}
	
	public String getDescricaoCompleta(){
		
		 return this.toString();
	}
	
	/**
	 * o metodo getNome retorna o nome da refeicao
	 * @return nome - String
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * o metodo setNome tem a funcao de alterar o nome da refeicao
	 * @param novoNome
	 */
	public void setNome(String novoNome) {
		this.nome = novoNome;
	}

	/**
	 * o metodo getDescricao retorna a descricao da refeicao
	 * @return descricao - String
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * o metodo setDescricao tem a funcao de alterar a descricao da refeicao
	 * @param descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * o metodo getPrato retorna a lista de pratos que contem na refeicao
	 * @return listaPratos - String
	 */
	public List<Prato> getPratos() {
		return listaPratos;
	}

	/**
	 * o metodo setPrato tem a funcao de alterar a lista de pratos que esta contida na refeicao
	 * @param pratos
	 */
	public void setPratos(List<Prato> pratos) {
		this.listaPratos = pratos;
	}
	
	/**
	 * o metodo calculaPreco percorre a lista de pratos pegando o preco individual que cada prato,
	 * somando a um preco total e aplicando um desconto no preco final da refeicao
	 * @return precoFinal - double
	 */
	public double calculaPreco(){
		double precoTotal = 0;
		double precoFinal;
		
		for (Prato prato : listaPratos){
			precoTotal += prato.getPreco();
		}
		
		precoFinal = precoTotal * DESCONTO;
		return precoFinal;
	}
	
	/**
	 * o metodo verificaTamanhoRefeicao ira verificar a quantidade de pratos em
	 * uma refeicao
	 * 
	 * @param pratos
	 * @throws SistemaException
	 */
	public void verificaTamanhoRefeicao(List<Prato> pratos) throws SistemaException {

		if (pratos.size() < TAMANHO_MIN_REFEICAO
				&& pratos.size() > TAMANHO_MAX_REFEICAO) {

			throw new RefeicaoException("Uma refeicao deve possuir de 3 a 4 pratos.");
		}
	}
	
	/**
	 * o metodo toString retorna a representacao em String da Refeicao
	 */
	public String toString(){
		
		String stringRefeicao = "";
		String stringFinal;
		int count = 1;
		
		for (Prato prato : listaPratos){
			
			if (count < listaPratos.size()){
				stringRefeicao += " (" + count + ") " + prato.getNome() + ","; //AJUSTAR A STRING COM V�RGULA
			
			}else{
				stringRefeicao += " (" + count + ") " + prato.getNome() + ".";
			}
			
			count ++;
		}
		
		stringFinal = String.format("%s Serao servidos:%s", this.getDescricao(), stringRefeicao);
		return stringFinal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((listaPratos == null) ? 0 : listaPratos.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	/**
	 * no metodo equals comparamos a refeicao por nome, descricao e os pratos que a mesma possui
	 */
	@Override
	public boolean equals(Object outroObjeto) {
		
		if (outroObjeto instanceof Refeicao){
			Refeicao outraRefeicao = (Refeicao) outroObjeto;
			
			if (this.getNome().equalsIgnoreCase(outraRefeicao.getNome()) 
					&& this.getDescricaoCompleta().equalsIgnoreCase(outraRefeicao.getDescricaoCompleta())
					&& this.getPratos().equals(outraRefeicao.getPratos())){
				
				return true;
			}
		}
		
		return false;
	}
}
