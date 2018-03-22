package musica;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import musica.Album;
import musica.Musica;

public class MusitecaTest {

	Musiteca musiteca;

	@Before
	public void setUp() throws Exception {
		musiteca = new Musiteca();
	}

	@Test
    public void testCriaAlbum() throws Exception {
        Album lemonade = new Album("Beyounce", "Lemonade", 2015);
        Musica sorry = new Musica("Sorry", 5, "Pop");
        Musica formation = new Musica("Formation", 4, "Pop");
        lemonade.adicionaMusica(sorry);
        lemonade.adicionaMusica(formation);        // nao pode adicionar albuns repetidos.
        assertTrue(musiteca.addAlbum(lemonade));
        assertFalse(musiteca.addAlbum(lemonade));
    }

	@Test
	public void testFavoritaAlbum() throws Exception {

		Album perfilMarilia = new Album("Marilia Mendonca", "Perfil", 2015);

		// adicionar na musiteca
		assertTrue(musiteca.addAlbum(perfilMarilia));
		assertTrue(musiteca.contemAlbum(perfilMarilia));

		// adicionar aos favoritos.
		assertTrue(musiteca.addAosFavoritos(perfilMarilia));
		assertFalse(musiteca.addAosFavoritos(perfilMarilia));

		assertEquals(1, musiteca.getQtdFavoritos());
	}

	@Test
	public void testCriaEAddPlaylist() {
		// cria uma playlist vazia, mas nao pode
		// criar mais de uma playlist com
		// o mesmo titulo.
		assertTrue(musiteca.criaPlaylist("Pop"));
		assertFalse(musiteca.criaPlaylist("Pop"));
		assertTrue(musiteca.contemPlaylist("Pop"));

	}

	@Test
	public void pesquisaMusica() throws Exception {

		Musica sentimentoLouco = new Musica("Sentimento louco", 3, "Sertanejo");
		Album mariliaLive = new Album("Marilia Mendonca", "DVD Ao vivo", 2015);
		mariliaLive.adicionaMusica(sentimentoLouco);

		assertTrue(musiteca.addAlbum(mariliaLive));

		assertTrue(musiteca.criaPlaylist("Sertanejo"));
		// adiciona na playlist de nome sertanejo:
		// a faixa 1 do album 'DVD Ao Vivo';
		// se houver nomes de albuns iguais, utilize o primeiro q encontrar
		assertTrue(musiteca.addNaPlaylist("Sertanejo", "DVD Ao Vivo", 1));

		assertEquals(1, musiteca.getTamPlaylist("Sertanejo"));
		// verifica se a playlist Sertanejo tem uma musica com esse nome.
		assertTrue(musiteca.contemNaPlaylist("Sertanejo", "Sentimento Louco"));
	}

	@Test
	public void testExeptionCases() {
		//adicao e criacao com valores null
		// sao tratados com retorno de booleanos
		// como consta no enunciado do lab.
		assertFalse(musiteca.addAlbum(null));
		assertFalse(musiteca.criaPlaylist(""));
	}
}
