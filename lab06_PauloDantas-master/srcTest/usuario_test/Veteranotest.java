/* 115211312 - Paulo Vitor Souto Dantas: LAB 06 - Turma 03 */
package usuario_test;

import org.junit.Before;
import org.junit.Test;

import jogo.Plataforma;
import jogo.RPG;
import junit.framework.Assert;
import usuario.Veterano;

public class Veteranotest {
	//Demais metodos de Usuario ja foram testados no NoobTest

	Veterano paulo;
	Plataforma mario;
	RPG witcher;
	
	@Before
	public void setup() throws Exception {
		witcher = new RPG("The Witcher", 101.19);
		
		paulo = new Veterano("Paulo Vitor", "paulo.vitor");
		paulo.depositaDinheiro(200);
		
		mario = new Plataforma("Mario", 500);
	}
	
	
	@Test
	public void testX2p() throws Exception {
		//veterano ja comeca com 1000 de x2p
		Assert.assertEquals(1000, paulo.getX2p());
		
		//veterano ao comprar jogo ganha preco do jogo vezes 15 de x2p
		paulo.compraJogo(witcher);
		Assert.assertEquals(2515, paulo.getX2p());
		
	}
	
	
	@Test
	public void testCompraJogo() throws Exception {
		try {
			paulo.compraJogo(mario);
		}catch(Exception e) {
			Assert.assertEquals("Voce nao possui dinheiro suficiente para comprar esse jogo.", e.getMessage());
		}
		
		//adicionando dinheiro para comprar o jogo mario
		paulo.depositaDinheiro(500);
		paulo.compraJogo(mario);
		//paulo tinha 700 reais e comprou mario pelo preco de 400 (com 20% de desconto)
		Assert.assertEquals(300.0, paulo.getDinheiro());
		
		try {
			paulo.compraJogo(witcher);
		}catch(Exception e) {
			Assert.assertEquals("Voce ja possui esse jogo.", e.getMessage());
		}

	}
	
}
