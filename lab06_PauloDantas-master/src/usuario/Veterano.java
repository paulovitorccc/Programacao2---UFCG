/* 115211312 - Paulo Vitor Souto Dantas: LAB 06 - Turma 03 */
package usuario;

import jogo.Jogo;

public class Veterano extends Usuario { 

	public final static double DESCONTO = 20;

	public Veterano(String nomeUsuario, String usernameUsuario) throws Exception {
		super(nomeUsuario, usernameUsuario, 1000);
	}


	public boolean compraJogo(Jogo novoJogo) throws Exception {

		if(super.getDinheiro() >= novoJogo.getPreco()) {

			double precoComDesconto = novoJogo.getPreco() - ((novoJogo.getPreco() * DESCONTO) / 100);

			if(jogos.add(novoJogo)) {
				super.sacaDinheiro(precoComDesconto);

				int x2pAdicional = 15 * (int) novoJogo.getPreco();
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
