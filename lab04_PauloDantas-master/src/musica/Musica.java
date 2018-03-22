/* 115211312 - Paulo Vítor Souto Dantas: LAB 04 - Turma 03 */
package musica;

public class Musica {

	private String titulo;
	private int duracao;
	private String genero;

	
	public Musica(String tituloMusica, int duracaoMusica, String generoMusica) throws Exception {
		if (tituloMusica == null || tituloMusica.trim().equals("")) {
			throw new Exception("Titulo da musica nao pode ser nulo ou vazio.");
		}
		
		if (generoMusica == null || generoMusica.trim().equals("")) {
			throw new Exception("Genero da musica nao pode ser nulo ou vazio.");
		}
		
		if (duracaoMusica <= 0) {
			throw new Exception("Duracao da musica nao pode ser negativa.");
		}
		
		this.titulo = tituloMusica;
		this.duracao = duracaoMusica;
		this.genero = generoMusica;

	}
	
	
	public String toString(){
		return this.titulo + " (" + this.genero + " - " + this.duracao + " minutos)";
	}


	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + duracao;
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}


	public boolean equals(Object outroObjeto) {
		if (outroObjeto instanceof Musica) {
			Musica outraMusica = (Musica) outroObjeto;
			if (outraMusica.getTitulo().equals(getTitulo()) && outraMusica.getDuracao() == getDuracao()){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	
	//GETTERS AND SETTERS 
	public String getTitulo(){
		return this.titulo;		
	}

	public void setTitulo(String novoTitulo){
		this.titulo = novoTitulo;
	}

	public int getDuracao(){
		return this.duracao;		
	}

	public void setDuracao(int novaDuracao){
		this.duracao = novaDuracao;
	}

	public String getGenero(){
		return this.genero;		
	}

	public void setGenero(String novoGenero){
		this.genero = novoGenero;
	}

	
}