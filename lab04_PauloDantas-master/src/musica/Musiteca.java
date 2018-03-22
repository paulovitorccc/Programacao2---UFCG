/* 115211312 - Paulo Vítor Souto Dantas: LAB 04 - Turma 03 */
package musica;

import java.util.ArrayList;
import java.util.HashMap;

public class Musiteca {

	ArrayList<Album> meusAlbuns;
	ArrayList<Album> meusFavoritos;
	HashMap<String, ArrayList<Musica>> minhasPlaylists;

	
	public Musiteca() {

		meusAlbuns = new ArrayList<Album>();
		meusFavoritos = new ArrayList<Album>();
		minhasPlaylists = new HashMap<String, ArrayList<Musica>>();
	}


	public boolean removeAlbum(Album outroAlbum) {
		return meusAlbuns.remove(outroAlbum);
	}


	public boolean addAlbum(Album novoAlbum) {
		if (meusAlbuns.contains(novoAlbum) || novoAlbum == null) {
			return false;
		}else {
			meusAlbuns.add(novoAlbum);
			return true;
		}		
	}


	public boolean contemAlbum(Album novoAlbum) {
		if (meusAlbuns.contains(novoAlbum)) {
			return true;
		} else {
			return false;
		}

	}


	public ArrayList<Album> albunsDoArtista(String nomeArtista) {
		ArrayList<Album> albunsDoArtista = new ArrayList<Album>();

		for (Album album : albunsDoArtista) {
			if (album.getArtista().equals(nomeArtista)) {
				albunsDoArtista.add(album);
			}
		}
		return albunsDoArtista;
	}


	public boolean addAosFavoritos(Album outroAlbum) {
		if (outroAlbum.equals(null)) {
			return false;
		}
		if (meusAlbuns.contains(outroAlbum) && !meusFavoritos.contains(outroAlbum)) {
			meusFavoritos.add(outroAlbum);
			return true;
		}else {
			return false;
		}
	}


	public int getQtdFavoritos() {
		return meusFavoritos.size();
	}


	public boolean criaPlaylist(String nomePlaylist) {
		if (minhasPlaylists.containsKey(nomePlaylist) || nomePlaylist.trim().equals("")) {
			return false;
		}else {
			minhasPlaylists.put(nomePlaylist, new ArrayList<Musica>());
			return true;
		}

	}


	public boolean contemPlaylist(String nomePlaylist) {
		if (minhasPlaylists.containsKey(nomePlaylist)) {
			return true;
		}else {
			return false;
		}
	}


	public boolean addNaPlaylist(String nomePlaylist, String nomeAlbum, int faixa) throws Exception {
		if (!minhasPlaylists.containsKey(nomePlaylist)) {
			minhasPlaylists.put(nomePlaylist, new ArrayList<Musica>());
		}

		ArrayList<Musica> musicasPlaylist = minhasPlaylists.get(nomePlaylist);
		Album albumAtual = this.selecionaAlbum(nomeAlbum);

		if (albumAtual == null) {
			throw new Exception ("Album nao pertence ao Perfil especificado");

		}else {
			musicasPlaylist.add(albumAtual.getMusicas().get(faixa - 1));

			minhasPlaylists.put(nomePlaylist, musicasPlaylist);
			return true;
		}
	}


	public Album selecionaAlbum(String nomeAlbum) {
		for (Album album : meusAlbuns) {
			if (album.getTitulo().equalsIgnoreCase(nomeAlbum)) {
				return album;
			}
		}
		return null;
	}


	public int getTamPlaylist(String nomePLaylist){
		ArrayList<Musica> musicasPlaylist = minhasPlaylists.get(nomePLaylist);
		return musicasPlaylist.size();
	}


	public boolean contemNaPlaylist(String nomePlaylist, String nomeMusica){
		if (minhasPlaylists.containsKey(nomePlaylist)) {
			ArrayList<Musica> musicasPlaylist = minhasPlaylists.get(nomePlaylist);
			
			for (Musica musica : musicasPlaylist) {
				if (musica.getTitulo().equalsIgnoreCase(nomeMusica)){
					return true;
				}
			}

			return false;

		}else {
			return false;
		}
		
	}


}