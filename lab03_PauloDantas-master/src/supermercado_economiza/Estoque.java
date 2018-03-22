/* 115211312 - Paulo Vítor Souto Dantas: LAB 3 - Turma 3 */
package supermercado_economiza;

public class Estoque {

	private final String FIM_DE_LINHA = System.lineSeparator();
	private Produto[] produtos;
	private int qtdDeProdutos;

	Leitor leitor = new Leitor();


	public Estoque(){
		qtdDeProdutos = 0;
		produtos = new Produto[5];

	}


	public String toString(){
		String strFinal = "Produtos cadastrados: \n";

		for(int i = 0; i < qtdDeProdutos; i++){
			strFinal += "    " + (i + 1) + ") " + produtos[i].toString() + FIM_DE_LINHA;
		}

		return strFinal;

	}


	public int getQtdDeProdutos(){
		return qtdDeProdutos;
	}


	public void setQtdDeProdutos(int qtdDeProdutosAdd){
		qtdDeProdutos += qtdDeProdutosAdd;
	}


	public void adicionarProduto(Produto cadastrado){

		if(qtdDeProdutos == produtos.length){
			aumentarArray();
		}

		produtos[qtdDeProdutos] = cadastrado;
		this.qtdDeProdutos += 1;

	}


	public Produto buscarProdutoPorNome(String produtoAtual){

		for(int i = 0; i < qtdDeProdutos; i++){

			if(produtos[i].getNome().equalsIgnoreCase(produtoAtual)){
				return produtos[i];				
			}
		}

		return null;

	}


	public void aumentarArray(){
		Produto[] novoArrayProdutos = new Produto[qtdDeProdutos * 2];

		for(int i = 0; i < produtos.length; i++){
			novoArrayProdutos[i] = produtos[i];
		}

		produtos = novoArrayProdutos;

	}


	public double precoTotalDoMercado(){
		double precoTotal = 0;

		for (int k = 0; k < qtdDeProdutos; k++){
			precoTotal += produtos[k].getPreco() * produtos[k].getQtd();
		}

		return precoTotal;

	}

}