/* 115211312 - Paulo Vitor Souto Dantas: LAB 06 - Turma 03 */
package jogo;

public abstract class Jogo {

	private static final String FIM_DE_LINHA = System.lineSeparator();
	
	protected String nome;
	protected double preco;
	protected int maiorStore;
	protected int qtdVezesJogadas;
	protected int qtdVezesZeradas;
	
	
	public Jogo(String nomeJogo, double precoJogo) throws Exception {
		if(nomeJogo == null || nomeJogo.trim().equals("")) {
			throw new Exception("O nome do jogo nao pode ser vazio ou nulo.");
		}
		if(precoJogo < 0) {
			throw new Exception("O preco do jogo nao pode ser negativo.");
		}
		
		this.nome = nomeJogo;
		this.preco = precoJogo;
		this.qtdVezesJogadas = 0;
		this.qtdVezesZeradas = 0;
	}
		
	
	@Override
	public String toString() {
		String tipoJogo = this.getClass().getSimpleName();
		
		String strFinal = "";
		strFinal += "+ " + this.getNome() + " - " + tipoJogo + ":" + FIM_DE_LINHA;
		strFinal += "==> Jogou " + this.getQtdVezesJogadas() + " vez(es)" + FIM_DE_LINHA;
		strFinal += "==> Zerou " + this.getQtdVezesZeradas() + " vez(es)" + FIM_DE_LINHA;
		strFinal += "==> Maior score: " + this.getMaiorStore() + FIM_DE_LINHA + FIM_DE_LINHA;
		
		return strFinal;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		long temp;
		temp = Double.doubleToLongBits(preco);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object outroObj) {
		if(outroObj instanceof Jogo) {
			Jogo novoJogo = (Jogo) outroObj;

			if(this.getNome().equals(novoJogo.getNome()) && this.getPreco() == novoJogo.getPreco()) {
				return true;
			}else {
				return false;
			}

		}else {
			return false;
		}
		
	}


	public abstract int registraJogada(int store, boolean zerou) throws Exception;


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public double getPreco() {
		return preco;
	}


	public void setPreco(double preco) {
		this.preco = preco;
	}


	public int getMaiorStore() {
		return maiorStore;
	}


	public void setMaiorStore(int maiorStore) {
		this.maiorStore = maiorStore;
	}


	public int getQtdVezesJogadas() {
		return qtdVezesJogadas;
	}


	public void setQtdVezesJogadas(int jogada) {
		this.qtdVezesJogadas = 1;
	}


	public int getQtdVezesZeradas() {
		return qtdVezesZeradas;
	}


	public void setQtdVezesZeradas(int zerada) {
		this.qtdVezesZeradas = 1;
	}

}
