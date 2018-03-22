package restaurante;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import exceptions.SistemaException;


public class TesteCardapio{

	private Cardapio cardapio;
	private Prato joe;
	private Prato burrito;
	private Prato parisiense;
	private Prato pudim;
	private Refeicao refeicao;
	private List<Prato> pratos;
	


	@Before
	public void setUpCardapio(){

		cardapio = new Cardapio();
		pratos = new ArrayList<>();

	}

	@Before
	public void setUpPratos() throws SistemaException{

		joe = new Prato("Joe", 3.00 , "Joe eh um prato celebre da culinaria japonesa, feito de salmao,"
        		+ " cream cheese e cebolinha.");

        burrito = new Prato ("Burrito", 25.99, "O burrito eh um celebre prato tradicional da culinaria do "
        		+ "Mexico consistindo de uma tortilla de farinha geralmente recheada com carne.");

        parisiense = new Prato ("Parisiense", 45.99, "O macarrao a parisiense eh um prato tradicional italiano,"
        		+ "feito de presunto, molho branco, frango, ervilha e champignon.");

        pudim = new Prato ("Pudim", 9.99, "Pudim eh uma sobremesa tradicional que eh feita com leite,"
        		+ "leite condensado, ovos e uma calda de caramelo.");
        
  
	}

	@Test
	public void testCardapioComPrato() throws Exception {

		cardapio.cadastraPrato("Joe", 3.00 , "Joe eh um prato celebre da culinaria japonesa, feito de salmao,"
        		+ " cream cheese e cebolinha.");
        
        cardapio.cadastraPrato("Burrito", 25.99, "O burrito eh um celebre prato tradicional da culinaria do "
        		+ "Mexico consistindo de uma tortilla de farinha geralmente recheada com carne.");
        
        cardapio.cadastraPrato("Parisiense", 45.99, "O macarrao a parisiense eh um prato tradicional italiano,"
        		+ "feito de presunto, molho branco, frango, ervilha e champignon.");
        
        cardapio.cadastraPrato("Pudim", 9.99, "Pudim eh uma sobremesa tradicional que eh feita com leite,"
        		+ "leite condensado, ovos e uma calda de caramelo.");
        
       
    	refeicao = new Refeicao("Combo Internacional", "Esta refeicao lhe proporciona "
        		+ "os melhores pratos tradicionais de cada parte do mundo.", cardapio.getPratos());
        

		assertEquals(4, cardapio.getPratos().size());
				
		
		assertTrue(cardapio.getPratos().equals(cardapio.criaRefeicao("Joe;Burrito;Parisiense;Pudim")));
		
		assertTrue(cardapio.cadastraRefeicao("Combo Internacional", "Esta refeicao lhe proporciona "
        		+ "os melhores pratos tradicionais de cada parte do mundo.", "Joe;Burrito;Parisiense;Pudim"));
		
		assertEquals(joe, cardapio.buscaPrato("Joe"));
		
		assertTrue(refeicao.equals(cardapio.buscaRefeicao("Combo Internacional")));
		
		assertEquals(1, cardapio.getRefeicoes().size());
		
		assertEquals("76.473", cardapio.consultaRestaurante("Combo Internacional", "preco"));
		
		assertEquals("Esta refeicao lhe proporciona os melhores pratos tradicionais de cada parte do mundo."
				+ " Serao servidos: (1) Joe, (2) Burrito, (3) Parisiense, (4) Pudim.", 
				cardapio.consultaRestaurante("Combo Internacional", "descricao"));
		
		assertEquals("Joe", cardapio.consultaRestaurante("Joe", "nome"));
		
		assertFalse(cardapio.removePrato("tapioca"));
		
	
		cardapio.removePrato("Pudim");
		
		assertEquals(3, cardapio.getPratos().size());
		
		assertTrue(cardapio.removeRefeicao("Combo Internacional"));
		
		assertEquals(0, cardapio.getRefeicoes().size());

	}

	@Test
	public void TestCardapioWithException() {

		try {
			
			pratos.remove(burrito);
			pratos.remove(joe);
			Refeicao refeicaoInvalida = new Refeicao("Refeicao", "invalida", pratos);
		} 
		catch (Exception e) {
			
			assertEquals("Uma refeicao deve possuir de 3 a 4 pratos.", e.getMessage());
		}

		try {
			
			cardapio.consultaRestaurante("Parisiense", "ingredientes");
		} 
		catch (Exception e) {
			
			assertEquals("Atributo invalido.", e.getMessage());
		}
	}
}