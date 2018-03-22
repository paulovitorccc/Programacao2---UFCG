/* 115211312 - Paulo Vítor Souto Dantas: LAB 3 - Turma 3 */
package supermercado_economiza;

public class Produto {
	
	private String nome;
	private String tipo;
	private double preco;
	private int qtd;
	
	public Produto(String nomeProduto, double precoProduto, String tipoProduto, int qtdProduto){
		nome = nomeProduto;
		preco = precoProduto;
		tipo = tipoProduto;
		qtd = qtdProduto;
	}
	
	
	public String toString(){
		return getNome() + " (" + getTipo() + "). " + "R$" + getPreco() + " Restante: " + getQtd();
	}

	
	public String getNome(){
		return nome;
	}
	
	public void setNome(String novoNome){
		this.nome =  novoNome;
	}
	
	public String getTipo(){
		return tipo;
	}
	
	public void setTipo(String novoTipo){
		this.tipo = novoTipo;		
	}
	
	public double getPreco(){
		return preco;
	}
	
	public void setPreco(double novoPreco){
		this.preco = novoPreco;
	}	
	
	public int getQtd(){
		return qtd;
	}
	
	public void setQtdAumentar(int qtdAAumentar){
		this.qtd += qtdAAumentar;
	}
	
	public void setQtdDiminuir(int qtdADiminuir){
		this.qtd -= qtdADiminuir;
	}
	
}