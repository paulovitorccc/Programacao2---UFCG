/* 115211312 - Paulo Vítor Souto Dantas: LAB 7 - Turma 03 */
package usuario;

import excecoes.ValorInvalidoException;
import jogo.Jogo;

public interface TipoDeUsuarioIF {

	public double compraJogo(Jogo jogo) throws ValorInvalidoException;
	
	public int recompensar(Jogo jogo);
	
	public int punir(Jogo jogo);
	
	public int bonusXp(int valor);
	
}
