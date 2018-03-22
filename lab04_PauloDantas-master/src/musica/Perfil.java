/* 115211312 - Paulo Vítor Souto Dantas: LAB 04 - Turma 03 */
package musica;

import java.util.ArrayList;

public class Perfil {

	private String nome;
	private Musiteca musitecaDoUsuario;


	public Perfil(String nomeUsuario) {

		this.nome = nomeUsuario;
		musitecaDoUsuario = new Musiteca();
	}


	//Delegadores 
	public void adicionaAlbum(Album album) throws Exception {
		try{
			if(!musitecaDoUsuario.addAlbum(album)) {
				throw new Exception("Nao eh possivel adicionar album!");
			}
						
		}catch(Exception excecao){
			System.out.println("Erro na adicao de album na musiteca");
			System.out.println(excecao.getMessage());
		}
	}


	public void removeAlbum(Album outroAlbum) throws Exception {
		try{
			if(!musitecaDoUsuario.removeAlbum(outroAlbum)) {
				throw new Exception("Nao eh possivel remover album!");
			}
						
		}catch(Exception excecao){
			System.out.println("Erro na remoçao de album na musiteca");
			System.out.println(excecao.getMessage());
		}
	}


	public void contemAlbum(Album novoAlbum) throws Exception {
		try{
			if(!musitecaDoUsuario.contemAlbum(novoAlbum)) {
				throw new Exception("Musiteca nao contem album!");
			}
						
		}catch(Exception excecao){
			System.out.println("Erro ao procurar album na musiteca");
			System.out.println(excecao.getMessage());
		}
	}


	public ArrayList<Album> albunsDoArtista(String nomeArtista) {
		return musitecaDoUsuario.albunsDoArtista(nomeArtista);
	}


	public void addAosFavoritos(Album outroAlbum) throws Exception {
		try{
			if(!musitecaDoUsuario.addAosFavoritos(outroAlbum)) {
				throw new Exception("Nao eh possivel adicionar album aos favoritos!");
			}
						
		}catch(Exception excecao){
			System.out.println("Erro ao adicionar album aos favoritos da musiteca");
			System.out.println(excecao.getMessage());
		}
	}


	public int getQtdFavoritos() {
		return musitecaDoUsuario.getQtdFavoritos();
	}


	public void criaPlaylist(String nomePlaylist) throws Exception {
		try{
			if(!musitecaDoUsuario.criaPlaylist(nomePlaylist)) {
				throw new Exception("Nao eh possivel criar playist, nome invalido!");
			}
						
		}catch(Exception excecao){
			System.out.println("Erro ao criar playlist na musiteca");
			System.out.println(excecao.getMessage());
		}
	}


	public void contemPlaylist(String nomePlaylist) throws Exception {
		try{
			if(!musitecaDoUsuario.contemPlaylist(nomePlaylist)) {
				throw new Exception("Musiteca nao contem playlist!");
			}
						
		}catch(Exception excecao){
			System.out.println("Erro ao buscar playlist na musiteca");
			System.out.println(excecao.getMessage());
		}
	}


	public void addNaPlaylist(String nomePlaylist, String nomeAlbum, int faixa) throws Exception {
		try{
			if(!musitecaDoUsuario.addNaPlaylist(nomePlaylist, nomeAlbum, faixa)) {
				throw new Exception("Album nao pode ser adicionado a playlist!");
			}
						
		}catch(Exception excecao){
			System.out.println("Erro ao adicionar album na playlist da musiteca");
			System.out.println(excecao.getMessage());
		}
	}


	public Album selecionaAlbum(String nomeAlbum) {
		return musitecaDoUsuario.selecionaAlbum(nomeAlbum);
	}


	public int getTamPlaylist(String nomePLaylist){
		return musitecaDoUsuario.getTamPlaylist(nomePLaylist);
	}


	public void contemNaPlaylist(String nomePlaylist, String nomeMusica){
		try{
			if(!musitecaDoUsuario.contemNaPlaylist(nomePlaylist, nomeMusica)) {
				throw new Exception("Playlist nao contem ah musica!");
			}
						
		}catch(Exception excecao){
			System.out.println("Erro ao buscar musica na playlist da musiteca");
			System.out.println(excecao.getMessage());
		}
	}


}
