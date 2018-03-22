/* 115211312 - Paulo Vitor Souto Dantas: LAB 06 - Turma 03 */
package jogo_test;

import org.junit.Before;
import org.junit.Test;

import jogo.RPG;
import junit.framework.Assert;

public class RPGTest {

	private static final String FIM_DE_LINHA = System.lineSeparator();
	
	RPG witcher;
	RPG witcher2;
	RPG skyrim;
	String nomeEsperado = "The Witcher";
	double precoEsperado = 101.19;
	
	@Before
	public void setup() throws Exception {
		witcher = new RPG("The Witcher", 101.19);
		witcher2 = new RPG("The Witcher", 101.19);
		skyrim = new RPG("Skyrim", 121.70);	
	}
	
	
	@Test
	public void testGetNome() {
		Assert.assertEquals(nomeEsperado, witcher.getNome());
	}
	
	
	@Test
	public void testGetPreco() {
		Assert.assertEquals(precoEsperado, witcher.getPreco());
	}
	
	
	@Test
	public void testToString() {
		String stringWitcher = "+ The Witcher - RPG:" + FIM_DE_LINHA + 
								"==> Jogou 0 vez(es)" + FIM_DE_LINHA +
								"==> Zerou 0 vez(es)" + FIM_DE_LINHA +
								"==> Maior score: 0" + FIM_DE_LINHA + FIM_DE_LINHA;
		Assert.assertEquals(stringWitcher, witcher.toString());
	}
	
	//Ah igualdade de dois jogos eh de acordo com o nome e preco
	@Test
	public void testEquals() {
		Assert.assertTrue(witcher.equals(witcher2));
		Assert.assertFalse(witcher.equals(skyrim));
	}
	
	//Lancamento de excecao na criacao de jogos
	@Test
	public void contrutorWithException() {
		try {
			RPG finalFantay = new RPG(" ", 200);
		}catch(Exception e) {
			Assert.assertEquals("O nome do jogo nao pode ser vazio ou nulo.", e.getMessage());
		}
		
		try {
			RPG fallout = new RPG(null, 300);
		}catch(Exception e) {
			Assert.assertEquals("O nome do jogo nao pode ser vazio ou nulo.", e.getMessage());
		}
		
		try {
			RPG fallout = new RPG("Fallout", -30);
		}catch(Exception e) {
			Assert.assertEquals("O preco do jogo nao pode ser negativo.", e.getMessage());
		}
				
	}
	
	//Registra jogada do RPG retorna 10 para o x2p do usuario
	@Test
	public void testRegistraJogada() throws Exception {
		try {
			witcher.registraJogada(-50, true);
		}catch(Exception e) {
			Assert.assertEquals("O store nao pode ser negativo.", e.getMessage());
		}
		
		Assert.assertEquals(10, witcher.registraJogada(1000, true));
		
		Assert.assertEquals(1, witcher.getQtdVezesJogadas());
		Assert.assertEquals(1, witcher.getQtdVezesZeradas());
		Assert.assertEquals(1000, witcher.getMaiorStore());
	}
	
}
