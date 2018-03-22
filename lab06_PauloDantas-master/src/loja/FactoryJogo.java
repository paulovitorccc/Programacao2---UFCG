/* 115211312 - Paulo Vitor Souto Dantas: LAB 06 - Turma 03 */
package loja;

import jogo.Jogo;
import jogo.Luta;
import jogo.Plataforma;
import jogo.RPG;

public class FactoryJogo {

	
	public Jogo criaRPG(String nomeJogo, double precoJogo) throws Exception {
		return new RPG(nomeJogo, precoJogo);		
	}
	
	
	public Jogo criaLuta(String nomeJogo, double precoJogo) throws Exception {
		return new Luta(nomeJogo, precoJogo);		
	}
	
	
	public Jogo criaPlataforma(String nomeJogo, double precoJogo) throws Exception {
		return new Plataforma(nomeJogo, precoJogo);		
	}
	
}
