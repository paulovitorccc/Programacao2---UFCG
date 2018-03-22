/* 115211312 - Paulo Vítor Souto Dantas: LAB 7 - Turma 03 */
package jogo;

import java.util.Set;

import excecoes.PrecoInvalidoException;
import excecoes.StringInvalidaException;

public class Luta extends Jogo{

	
	public Luta(String nome, double preco) throws StringInvalidaException, PrecoInvalidoException {
		//chamada polimórfica
		super(nome, preco);
	}
	
	
	public Luta (String nome, double preco, Set<Jogabilidade> jogabilidades) throws StringInvalidaException, PrecoInvalidoException {
		//chamada polimórfica
		super(nome, preco, jogabilidades);
	}
	
	
	@Override
	public int registraJogada(int score, boolean venceu) {
		setVezesJogadas(getVezesJogadas()+ 1);
		if(score > this.getMaiorScore()){
			setMaiorScore(score);
		}
		if(venceu){
			setVezesConcluidas(getvezesConcluidas() + 1);
			
		}
		return score/1000;
	}
	
	
	public String toString() {
		String resultado = getNome() + " - Luta:" + FIM_DE_LINHA;
		//chamada polimórfica
		resultado += super.toString();
		return resultado;
	}
	
}
