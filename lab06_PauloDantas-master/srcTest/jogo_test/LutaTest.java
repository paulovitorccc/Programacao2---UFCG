/* 115211312 - Paulo Vitor Souto Dantas: LAB 06 - Turma 03 */
package jogo_test;

import org.junit.Before;
import org.junit.Test;

import jogo.Luta;
import junit.framework.Assert;

public class LutaTest {

	private static final String FIM_DE_LINHA = System.lineSeparator();

	Luta tekken;
	Luta tekken2;
	Luta defJam;
	String nomeEsperado = "Tekken";
	double precoEsperado = 150.40;

	@Before
	public void setup() throws Exception {
		tekken = new Luta("Tekken", 150.40);
		tekken2 = new Luta("Tekken", 150.40);
		defJam = new Luta("Def Jam", 121.70);	
	}


	@Test
	public void testGetNome() {
		Assert.assertEquals(nomeEsperado, tekken.getNome());
	}


	@Test
	public void testGetPreco() {
		Assert.assertEquals(precoEsperado, tekken.getPreco());
	}


	@Test
	public void testToString() {
		String stringTekken = "+ Tekken - Luta:" + FIM_DE_LINHA + 
				"==> Jogou 0 vez(es)" + FIM_DE_LINHA +
				"==> Zerou 0 vez(es)" + FIM_DE_LINHA +
				"==> Maior score: 0" + FIM_DE_LINHA + FIM_DE_LINHA;
		Assert.assertEquals(stringTekken, tekken.toString());
	}

	//Ah igualdade de dois jogos eh de acordo com o nome e preco
	@Test
	public void testEquals() {
		Assert.assertTrue(tekken.equals(tekken2));
		Assert.assertFalse(tekken.equals(defJam));
	}

	//Lancamento de excecao na criacao de jogos
	@Test
	public void contrutorWithException() {
		try {
			Luta kingOfFighters = new Luta(" ", 300);
		}catch(Exception e) {
			Assert.assertEquals("O nome do jogo nao pode ser vazio ou nulo.", e.getMessage());
		}

		try {
			Luta kingOfFighters = new Luta(null, 300);
		}catch(Exception e) {
			Assert.assertEquals("O nome do jogo nao pode ser vazio ou nulo.", e.getMessage());
		}

		try {
			Luta kingOfFighters = new Luta("King Of Fighters", -30);
		}catch(Exception e) {
			Assert.assertEquals("O preco do jogo nao pode ser negativo.", e.getMessage());
		}

	}

	//Registra jogada da Luta retorna x2p para o usuario de acordo com o novo store
	@Test
	public void testRegistraJogada() throws Exception {
		try {
			tekken.registraJogada(-50, true);
		}catch(Exception e) {
			Assert.assertEquals("O store nao pode ser negativo.", e.getMessage());
		}

		Assert.assertEquals(5, tekken.registraJogada(5000, true));

		Assert.assertEquals(1, tekken.getQtdVezesJogadas());
		Assert.assertEquals(1, tekken.getQtdVezesZeradas());
		Assert.assertEquals(5000, tekken.getMaiorStore());
	}
}
