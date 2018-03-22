/* 115211312 - Paulo Vítor Souto Dantas: LAB 04 - Turma 03 */
package musica;

import java.util.List;
import java.util.ArrayList;

public class Album {

	private static final String FIM_DE_LINHA = System.lineSeparator();

	ArrayList<Musica> musicas;
	private String artista;
	private String titulo;
	private int anoDeLancamento;


	public Album(String artistaAlbum, String tituloAlbum, int anoDeLancamentoAlbum) throws Exception{
		if (artistaAlbum == null || artistaAlbum.trim().equals("")) {
			throw new Exception ("Artista do album nao pode ser nulo ou vazio.");
		}

		if (anoDeLancamentoAlbum <= 1900) {
			throw new Exception ("Ano de lancamento do album nao pode ser inferior a 1900.");
		}

		if (tituloAlbum == null || tituloAlbum.trim().equals("")) {
			throw new Exception ("Titulo do album nao pode ser nulo ou vazio.");
		}

		musicas = new ArrayList<Musica>();
		this.artista = artistaAlbum;
		this.titulo = tituloAlbum;
		this.anoDeLancamento = anoDeLancamentoAlbum;

	}


	public String toString(){
		String concatenacaoFinal = this.titulo + ", " + this.artista + " (" + this.anoDeLancamento + ")" + FIM_DE_LINHA + FIM_DE_LINHA;

		for (int i = 0; i < musicas.size(); i++) {
			concatenacaoFinal += "	" + i+1 + ". " + musicas.get(i).toString() + FIM_DE_LINHA;
		}

		return concatenacaoFinal;
	}


	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artista == null) ? 0 : artista.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}


	public boolean equals(Object outroObjeto) {
		if (outroObjeto instanceof Album){
			Album outroAlbum = (Album) outroObjeto;
			if (outroAlbum.getArtista().equals(getArtista()) && outroAlbum.getTitulo().equals(getTitulo())){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}


	public boolean adicionaMusica(Musica novaMusica){
		if (novaMusica == null){
			return false;
		}else {
			musicas.add(novaMusica);
			return true;
		}
	}


	public void removeMusica(int faixa){
		musicas.remove(faixa - 1);
	}


	public boolean contemMusica(String tituloMusica){
		for (Musica musica : musicas) {
			if (musica.getTitulo().equalsIgnoreCase(tituloMusica)){
				return true;
			}
		}
		return false;
	}


	public int quantidadeFaixas(){
		return musicas.size();
	}

	
	public Musica getMusica(String tituloMusica){
		for (Musica musica : musicas) {
			if (musica.getTitulo().equals(tituloMusica)) {
				return musica;
			}
		}
		return null;
	}


	public int getDuracaoTotal(){
		int duracao = 0;

		for (Musica musica : musicas) {
			duracao += musica.getDuracao();
		}
		return duracao;	

	}

	
	//GETTERS AND SETTERS
	public String getArtista(){
		return this.artista;		
	}

	public void setArtista(String novoArtista){
		this.artista = novoArtista;
	}

	public String getTitulo(){
		return this.titulo;		
	}

	public void setTitulo(String novoTitulo){
		this.titulo = novoTitulo;
	}

	public int getAnoDeLancamento(){
		return this.anoDeLancamento;		
	}

	public void setAnoDeLancamento(int novoAnoDeLancamento){
		this.anoDeLancamento = novoAnoDeLancamento;
	}

	public int getDuracao(){
		int duracao = 0;

		for (Musica musica : musicas) {
			duracao += musica.getDuracao();
		}
		return duracao;		
	}

	public List<Musica> getMusicas() {
		return musicas;
	}

	public void setMusicas(ArrayList<Musica> musicas) {
		this.musicas = musicas;
	}
	

}