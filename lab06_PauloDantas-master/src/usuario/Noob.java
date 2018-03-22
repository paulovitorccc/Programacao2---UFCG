/* 115211312 - Paulo Vitor Souto Dantas: LAB 06 - Turma 03 */
package usuario;

import jogo.Jogo;

public class Noob extends Usuario {

	public final static double DESCONTO = 10;

	public Noob(String nomeUsuario, String usernameUsuario) throws Exception {
		super(nomeUsuario, usernameUsuario, 0);
	}


	public boolean compraJogo(Jogo novoJogo) throws Exception {

		if(super.getDinheiro() >= novoJogo.getPreco()) {
			
			double precoComDesconto = novoJogo.getPreco() - ((novoJogo.getPreco() * DESCONTO) / 100);

			if(jogos.add(novoJogo)) {
				super.sacaDinheiro(precoComDesconto);

				int x2pAdicional = 10 * (int) novoJogo.getPreco();
				this.x2p += x2pAdicional;

				return true;
			}else {
				throw new Exception("Voce ja possui esse jogo.");
			}

		}else {
			throw new Exception("Voce nao possui dinheiro suficiente para comprar esse jogo.");
		}

	}

}
