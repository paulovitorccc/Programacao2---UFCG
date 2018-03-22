/* 115211312 - Paulo Vitor Souto Dantas: LAB 06 - Turma 03 */
package jogo;

public class RPG extends Jogo{

	public RPG(String nomeJogo, double precoJogo) throws Exception {
		super(nomeJogo, precoJogo);
	}

	
	public int registraJogada(int store, boolean zerou) throws Exception {
		if(store < 0) {
			throw new Exception("O store nao pode ser negativo.");
		}
		
		super.qtdVezesJogadas += 1;
		
		if(store > super.maiorStore) {
			super.setMaiorStore(store);
		}
		if(zerou == true) {
			super.qtdVezesZeradas += 1;
		}
		
		return 10;
	
	}
	
}
