/* 115211312 - Paulo Vitor Souto Dantas: LAB 06 - Turma 03 */
package jogo_test;


import org.junit.Before;
import org.junit.Test;

import jogo.Plataforma;
import junit.framework.Assert;

public class PlataformaTest {

	private static final String FIM_DE_LINHA = System.lineSeparator();

	Plataforma mario;
	Plataforma mario2;
	Plataforma sonic;
	String nomeEsperado = "Mario";
	double precoEsperado = 50.0;

	@Before
	public void setup() throws Exception {
		mario = new Plataforma("Mario", 50.0);
		mario2 = new Plataforma("Mario", 50.0);
		sonic = new Plataforma("Sonic", 40.50);	
	}


	@Test
	public void testGetNome() {
		Assert.assertEquals(nomeEsperado, mario.getNome());
	}


	@Test
	public void testGetPreco() {
		Assert.assertEquals(precoEsperado, mario.getPreco());
	}


	@Test
	public void testToString() {
		String stringMario = "+ Mario - Plataforma:" + FIM_DE_LINHA + 
							"==> Jogou 0 vez(es)" + FIM_DE_LINHA +
							"==> Zerou 0 vez(es)" + FIM_DE_LINHA +
							"==> Maior score: 0" + FIM_DE_LINHA + FIM_DE_LINHA;
		Assert.assertEquals(stringMario, mario.toString());
	}

	//Ah igualdade de dois jogos eh de acordo com o nome e preco
	@Test
	public void testEquals() {
		Assert.assertTrue(mario.equals(mario2));
		Assert.assertFalse(mario.equals(sonic));
	}

	//Lancamento de excecao na criacao de jogos
	@Test
	public void contrutorWithException() {
		try {
			Plataforma bomberman = new Plataforma(" ", 300);
		}catch(Exception e) {
			Assert.assertEquals("O nome do jogo nao pode ser vazio ou nulo.", e.getMessage());
		}

		try {
			Plataforma bomberman = new Plataforma(null, 300);
		}catch(Exception e) {
			Assert.assertEquals("O nome do jogo nao pode ser vazio ou nulo.", e.getMessage());
		}

		try {
			Plataforma bomberman = new Plataforma("Bomberman", -300);
		}catch(Exception e) {
			Assert.assertEquals("O preco do jogo nao pode ser negativo.", e.getMessage());
		}

	}

	//Registra jogada de Plataforma retorna 20 de x2p para o usuario sempre que ele zera o jogo
	@Test
	public void testRegistraJogada() throws Exception {
		try {
			mario.registraJogada(-50, true);
		}catch(Exception e) {
			Assert.assertEquals("O store nao pode ser negativo.", e.getMessage());
		}

		Assert.assertEquals(20, mario.registraJogada(5000, true));

		Assert.assertEquals(1, mario.getQtdVezesJogadas());
		Assert.assertEquals(1, mario.getQtdVezesZeradas());
		Assert.assertEquals(5000, mario.getMaiorStore());
	}
	
}
