/* 115211312 - Paulo Vitor Souto Dantas: LAB 06 - Turma 03 */
package loja_test;

import org.junit.Before;
import org.junit.Test;

import jogo.Jogo;
import jogo.Luta;
import jogo.Plataforma;
import jogo.RPG;
import junit.framework.Assert;
import loja.Loja;
import usuario.Noob;
import usuario.Usuario;
import usuario.Veterano;

public class LojaTest {

	Loja loja;
	Noob jose;
	Plataforma mario;

	@Before
	public void seup() throws Exception {
		mario = new Plataforma("Mario", 100.0);
		
		loja = new Loja();
		jose = new Noob("Jose", "jose.ze");
		loja.adicionaUsuario(jose);
		
		jose.setDinheiro(1000);
		jose.compraJogo(mario);
	}

	@Test
	public void testCriaUsuario() {
		try {
			loja.criaUsuario("Maria", "maria.fernanda", "Especial");
		}catch(Exception e) {
			Assert.assertEquals("Especial nao eh um tipo de usuario reconhecido pela loja.", e.getMessage());
		}
		//criando usuarios e adicionando na loja
		Assert.assertTrue(loja.criaUsuario("Paulo Vitor", "paulo.vitor", "veterano"));
		Assert.assertTrue(loja.criaUsuario("Mateus", "mateus.dantas", "Noob"));
		
		Usuario mateus = loja.selecionaUsuario("mateus.dantas");
		Usuario paulo = loja.selecionaUsuario("paulo.vitor");
		
		Assert.assertTrue(mateus instanceof Noob);
		Assert.assertTrue(paulo instanceof Veterano);
	
	}
	
	
	@Test
	public void testRegistraJogada() {
		try {
			loja.registraJogada("maria", "mario", 100, true);
		}catch(Exception e) {
			Assert.assertEquals("O usuario nao esta cadastrado.", e.getMessage());
		}
		
		Assert.assertTrue(loja.registraJogada("jose.ze", "Mario", 100, true));
		Assert.assertEquals(1, mario.getQtdVezesJogadas());
	}
	
	
	@Test
	public void testAdicionaUsuario() throws Exception {
		try {
			Veterano jose2 = new Veterano("Jose", "jose.ze");
			loja.adicionaUsuario(jose2);
		}catch(Exception e) {
			Assert.assertEquals("Usuario ja esta cadastrado!", e.getMessage());
		}
		
		Noob eduardo = new Noob("Eduardo", "eduardo.sousa");
		Assert.assertTrue(loja.adicionaUsuario(eduardo));
		
	}
	
	
	@Test
	public void testAdicionaDinheiro() {
		Assert.assertTrue(loja.adicionaDinheiro("jose.ze", 400));
	}
	
	
	@Test
	public void testVendeJogo() {
		loja.vendeJogo("jose.ze", "Skyrim", 100, "RPG");
		loja.vendeJogo("jose.ze", "Mario", 50, "Plataforma");
		loja.vendeJogo("jose.ze", "Def Jam", 70, "Luta");
		
		Jogo skyrimRPG = jose.procuraJogoPorNome("Skyrim");
		Jogo marioPlataforma = jose.procuraJogoPorNome("Mario");
		Jogo defJamLuta = jose.procuraJogoPorNome("Def Jam");
		
		//testando a criacao de jogos
		Assert.assertTrue(skyrimRPG instanceof RPG);
		Assert.assertTrue(marioPlataforma instanceof Plataforma);
		Assert.assertTrue(defJamLuta instanceof Luta);
		
		try {
			loja.vendeJogo("jose.ze", "Def Jam", 70, "Moba");
		}catch(Exception e) {
			Assert.assertEquals("Moba nao eh um tipo de Jogo reconhecido pela loja.", e.getMessage());
		}
		
		try {
			loja.vendeJogo("joao", "Def Jam", 70, "Luta");
		}catch(Exception e) {
			Assert.assertEquals("Nao existe usuario com esse login.", e.getMessage());
		}
		
	}

	
	@Test
	public void testUpgrade() throws Exception {
				
		try {
			loja.upgrade("ze");
		}catch(Exception e) {
			Assert.assertEquals("Usuario nao existe!", e.getMessage());
		}
		
		try {
			loja.upgrade("jose.ze");
		}catch(Exception e) {
			Assert.assertEquals("Usuario nao possui x2p suficiente para se tornar Veterano!", e.getMessage());
		}
		
		Veterano vinicius = new Veterano("Vinicius", "vinicius.vi");
		
		try {
			loja.upgrade("vinicius.vi");
		}catch(Exception e) {
			Assert.assertEquals("Usuario ja eh Veterano!", e.getMessage());
		}
		
		jose.setX2p(4000);
		loja.upgrade("jose.ze");
		
		Usuario novoUsuario = loja.selecionaUsuario("jose.ze");
		Assert.assertTrue(novoUsuario instanceof Veterano);
		
	}

}
