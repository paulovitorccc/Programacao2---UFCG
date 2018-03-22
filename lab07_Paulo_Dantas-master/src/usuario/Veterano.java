/* 115211312 - Paulo Vítor Souto Dantas: LAB 7 - Turma 03 */
package usuario;

import excecoes.ValorInvalidoException;
import jogo.Jogabilidade;
import jogo.Jogo;

public class Veterano implements TipoDeUsuarioIF {

	public static final double DESCONTO_VETERANO = 0.8;


	public Veterano() {

	}


	@Override
	public double compraJogo(Jogo jogo) throws ValorInvalidoException {
		return jogo.getPreco() * DESCONTO_VETERANO;
	}


	public int bonusXp(int valor) {
		return valor * 15;
	}


	@Override
	public int recompensar(Jogo jogo) {
		int pontosDeRecompenca = 0;

		if(jogo.getJogabilidade().contains(Jogabilidade.ONLINE)) {
			pontosDeRecompenca += 10;
		}
		if(jogo.getJogabilidade().contains(Jogabilidade.COOPERATIVO)) {
			pontosDeRecompenca += 20;
		}
		
		return pontosDeRecompenca;

	}


	@Override
	public int punir(Jogo jogo) {
		int pontosDePunicao = 0;

		if (jogo.getJogabilidade().contains(Jogabilidade.COMPETITIVO)) {
			pontosDePunicao += 20;
		}
		if (jogo.getJogabilidade().contains(Jogabilidade.OFFLINE)) {
			pontosDePunicao += 20;
		}

		return pontosDePunicao;

	}


}
