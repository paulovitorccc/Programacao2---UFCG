/* 115211312 - Paulo Vitor Souto Dantas: LAB 06 - Turma 03 */
package usuario_test;

import org.junit.Before;
import org.junit.Test;

import jogo.Plataforma;
import jogo.RPG;
import junit.framework.Assert;
import usuario.Noob;

public class NoobTest {

	private static final String FIM_DE_LINHA = System.lineSeparator();
	//joao e joao2 possuem nomes iguais mas login diferente
	//arthur possue o mesmo login de joao
	Noob joao;
	Noob joao2;
	Noob arthur;

	String nomeEsperado = "Joao Vitor";
	String loginEsperado = "joao.vitor";

	RPG witcher;
	Plataforma mario;

	@Before
	public void setup() throws Exception {
		witcher = new RPG("The Witcher", 101.19);
		joao = new Noob("Joao Vitor", "joao.vitor");
		joao2 = new Noob("Joao Vitor", "joao.marcos");
		arthur = new Noob("Arthur Rodrigues", "joao.vitor");
		joao.depositaDinheiro(200);
		mario = new Plataforma("Mario", 500);
	}

	//Teste no construtor de usuarios
	@Test
	public void testGetNome() {
		Assert.assertEquals(nomeEsperado, joao.getNome());
	}


	@Test
	public void testGetLogin() {
		Assert.assertEquals(loginEsperado, joao.getLogin());
	}

	//noob ao comprar jogo ganha 10 vezes o preco do jogo de x2p
	@Test
	public void testGetX2p() throws Exception {
		joao.compraJogo(witcher);
		Assert.assertEquals(1010, joao.getX2p());
	}


	@Test
	public void testToString() throws Exception {
		joao.compraJogo(witcher);
		String stringJoao = "joao.vitor" + FIM_DE_LINHA +
				"Joao Vitor - Jogador Noob" + FIM_DE_LINHA + 
				"Lista de Jogos:" + FIM_DE_LINHA +
				"+ The Witcher - RPG:" + FIM_DE_LINHA +
				"==> Jogou 0 vez(es)" + FIM_DE_LINHA +
				"==> Zerou 0 vez(es)" + FIM_DE_LINHA +
				"==> Maior score: 0" + FIM_DE_LINHA + FIM_DE_LINHA +
				"Total de preço dos jogos: R$ 101.19"+ FIM_DE_LINHA + FIM_DE_LINHA;
		Assert.assertEquals(stringJoao, joao.toString());
	}

	//Ah igualdade de dois usuarios eh de acordo com o login
	@Test
	public void testEquals() {
		Assert.assertFalse(joao.equals(joao2));
		Assert.assertTrue(joao.equals(arthur));
	}

	//Lancamento de excecao na criacao de usuarios
	@Test
	public void testContrutorWithException() {
		try {
			Noob paulo = new Noob("   ", "paulo.vitor");
		}catch(Exception e) {
			Assert.assertEquals("O nome do usuario nao pode ser vazio ou nulo.", e.getMessage());
		}

		try {
			Noob paulo = new Noob(null, "paulo.vitor");
		}catch(Exception e) {
			Assert.assertEquals("O nome do usuario nao pode ser vazio ou nulo.", e.getMessage());
		}

		try {
			Noob paulo = new Noob("Paula Vitor", " ");
		}catch(Exception e) {
			Assert.assertEquals("O login do usuario nao pode ser vazio ou nulo.", e.getMessage());
		}

		try {
			Noob paulo = new Noob("Paula Vitor", null);
		}catch(Exception e) {
			Assert.assertEquals("O login do usuario nao pode ser vazio ou nulo.", e.getMessage());
		}

	}


	@Test
	public void testRegistraJogada() throws Exception {
		try {
			joao.registraJogada("Mario", 100, true);
		}catch(Exception e) {
			Assert.assertEquals("Voce nao possui esse jogo.", e.getMessage());
		}

		joao.compraJogo(witcher);
		//joao joga The Witcher e ganha 40000 de x2p
		joao.registraJogada("The Witcher", 40000, true);

		Assert.assertEquals(40000, witcher.getMaiorStore());
		Assert.assertEquals(1, witcher.getQtdVezesZeradas());
		Assert.assertEquals(1, witcher.getQtdVezesJogadas());

	}


	@Test
	public void testProcuraJogoPorNome() throws Exception {
		joao.compraJogo(witcher);
		Assert.assertEquals(witcher, joao.procuraJogoPorNome("The Witcher"));		
		Assert.assertEquals(null, joao.procuraJogoPorNome("Mario"));
	}


	@Test
	public void testDepositaDinheiro() throws Exception {
		try {
			joao.depositaDinheiro(-5);	
		}catch(Exception e) {
			Assert.assertEquals("Voce nao pode adicionar valor negativo ao seu dinheiro!", e.getMessage());
		}

		joao.depositaDinheiro(100);
		//adiciona 200 reais no before e mais 100 na linha acima
		Assert.assertEquals(300.0, joao.getDinheiro());
	}


	@Test
	public void testSacaDinheiro() throws Exception {
		try {
			joao.sacaDinheiro(20000);
		}catch(Exception e) {
			Assert.assertEquals("Voce nao possui dinheiro suficiente!", e.getMessage());
		}

		joao.sacaDinheiro(200.0);
		Assert.assertEquals(0.0, joao.getDinheiro());
	}

	@Test
	public void testCompraJogo() throws Exception {
		try {
			joao.compraJogo(mario);
		}catch(Exception e) {
			Assert.assertEquals("Voce nao possui dinheiro suficiente para comprar esse jogo.", e.getMessage());
		}
		
		//adicionando dinheiro para comprar o jogo mario
		joao.depositaDinheiro(500);
		joao.compraJogo(mario);
		//joao tinha 700 reais e comprou mario pelo preco de 450 (com 10% de desconto)
		Assert.assertEquals(250.0, joao.getDinheiro());
		
		try {
			joao.compraJogo(witcher);
		}catch(Exception e) {
			Assert.assertEquals("Voce ja possui esse jogo.", e.getMessage());
		}

	}

}
