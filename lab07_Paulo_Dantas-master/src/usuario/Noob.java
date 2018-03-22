/* 115211312 - Paulo Vítor Souto Dantas: LAB 7 - Turma 03 */
package usuario;

import excecoes.ValorInvalidoException;
import jogo.Jogabilidade;
import jogo.Jogo;

public class Noob implements TipoDeUsuarioIF {

	public static final double DESCONTO_NOOB = 0.9;


	public Noob() {

	}


	@Override
	public double compraJogo(Jogo jogo) throws ValorInvalidoException {
		return jogo.getPreco() * DESCONTO_NOOB;
	}


	public int bonusXp(int valor) {
		return valor * 10;
	}


	public int recompensar(Jogo jogo) {
		int pontosDeRecompenca = 0;

		if(jogo.getJogabilidade().contains(Jogabilidade.OFFLINE)) {
			pontosDeRecompenca += 30;
		}
		if(jogo.getJogabilidade().contains(Jogabilidade.MULTIPLAYER)) {
			pontosDeRecompenca += 10;
		}

		return pontosDeRecompenca;

	}


	public int punir(Jogo jogo) {
		int pontosDePunicao = 0;

		if (jogo.getJogabilidade().contains(Jogabilidade.ONLINE)) {
			pontosDePunicao += 10;
		}
		if (jogo.getJogabilidade().contains(Jogabilidade.COMPETITIVO)) {
			pontosDePunicao += 20;
		}
		if (jogo.getJogabilidade().contains(Jogabilidade.COOPERATIVO)) {
			pontosDePunicao += 50;
		}

		return pontosDePunicao;

	}



}