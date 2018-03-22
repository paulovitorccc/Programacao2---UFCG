/* 115211312 - Paulo Vítor Souto Dantas: LAB 7 - Turma 03 */
package usuario;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;
import jogo.Jogo;

public class Usuario {

	public static final String FIM_DE_LINHA = System.lineSeparator();

	private String nome;
	private String login;
	private Set<Jogo> meusJogos;
	private double credito;
	private int xp2;
	private TipoDeUsuarioIF statusDoUsuario;


	public Usuario(String nome, String login, String tipo) throws StringInvalidaException {

		if (nome == null || nome.trim().isEmpty()) {
			throw new StringInvalidaException("Nome nao pode ser nulo ou vazio.");
		}
		if (login == null || login.trim().isEmpty()) {
			throw new StringInvalidaException("Login nao pode ser nulo ou vazio.");
		}

		this.nome = nome;
		this.login = login;
		meusJogos = new HashSet<Jogo>();
		this.credito = 0;
		
		if(tipo.equalsIgnoreCase("noob")) {
			this.xp2 = 0;
			//chamada polimórfica
			this.statusDoUsuario = new Noob();
		}
		else if (tipo.equalsIgnoreCase("veterano")) {
			this.xp2 = 1000;
			//chamada polimórfica
			this.statusDoUsuario = new Veterano();
		}else {
			throw new StringInvalidaException("Tipo de Usuario nao reconhecido.");
		}
	
	}


	public void compraJogo(Jogo jogo) throws ValorInvalidoException { 
		//chamada polimórfica
		double custo = statusDoUsuario.compraJogo(jogo);
		
		if (custo > this.getCredito()) {
			throw new ValorInvalidoException("Credito insuficiente para realizar a compra.");

		} else {
			int valor =(int)( jogo.getPreco() - (jogo.getPreco() % 1));
			//chamada polimórfica
			int bonusXp =  statusDoUsuario.bonusXp(valor);

			this.setXp2(getXp2() + bonusXp);
			this.setCredito(getCredito() - custo);

			this.cadastraJogo(jogo);

		}
	}


	public void recompensar(String nomeJogo, int scoreObtido, boolean zerou) throws StringInvalidaException {
		Jogo jogo = this.buscaJogo(nomeJogo);

		if (jogo == null) {
			throw new StringInvalidaException("Voce nao possui este jogo.");
		}else {
			//chamada polimórfica
			int recompensa = statusDoUsuario.recompensar(jogo);

			this.setXp2(getXp2() + recompensa);
		}
		
	}


	public void punir(String nomeJogo, int scoreObtido, boolean zerou) throws StringInvalidaException {
		Jogo jogo = this.buscaJogo(nomeJogo);
		
		if (jogo == null) {
			throw new StringInvalidaException("Voce nao possui este jogo.");
		}else {
			//chamada polimórfica
			int punicao = statusDoUsuario.punir(jogo);
			
			this.setXp2(getXp2() - punicao);
		}
		
	}


	public void cadastraJogo(Jogo jogo) {
		this.meusJogos.add(jogo);
	}


	public void registraJogada(String nomeJogo, int scoreObtido, boolean zerou) throws StringInvalidaException {
		Jogo jogo = this.buscaJogo(nomeJogo);

		this.setXp2(getXp2() + jogo.registraJogada(scoreObtido, zerou));	
		this.recompensar(nomeJogo, scoreObtido, zerou);
		this.punir(nomeJogo, scoreObtido, zerou);
	}


	public Jogo buscaJogo(String nomeJogo) {
		Jogo buscado = null;
		Iterator itr = meusJogos.iterator();

		while (itr.hasNext()) {
			Jogo achado = (Jogo) itr.next();
			if (achado.getNome().equals(nomeJogo)) {
				buscado = achado;
			}
		}

		return buscado;
	}


	public double calculaPrecoTotal() {
		double total = 0;
		Iterator itr = meusJogos.iterator();

		while (itr.hasNext()) {
			Jogo achado = (Jogo) itr.next();
			total += achado.getPreco();
		}

		return total;
	}

	/**
	 * Getters and Setters
	 */
	public int getXp2() {
		return this.xp2;
	}

	public void setXp2(int novoValor) {
		this.xp2 = novoValor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public double getCredito() {
		return this.credito;
	}

	public void setCredito(double novoValor) {
		this.credito = novoValor;
	}

	public Set<Jogo> getMeusJogos() {
		return meusJogos;
	}

	public void setMeusJogos(Set<Jogo> meusJogos) {
		this.meusJogos = meusJogos;
	}
	
	public TipoDeUsuarioIF getStatusDoUsuario() {
		return statusDoUsuario;
	}

	public void setStatusDoUsuario(TipoDeUsuarioIF statusDoUsuario) {
		this.statusDoUsuario = statusDoUsuario;
	}


	/**
	 * Métodos abaixo:
	 * - hashCode, equals: Usuarios são iguais se possuirem mesmo nome e login.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Usuario) {
			Usuario temp = (Usuario) obj;

			return this.getNome().equals(temp.getNome()) && this.getLogin().equals(temp.getLogin());

		} else {
			return false;
		}
	}
	
	
	@Override
	public String toString() {
		String tipo = statusDoUsuario.getClass().getSimpleName();
		String myString = this.getLogin() + FIM_DE_LINHA;
		myString += this.getNome() + " - Jogador " + tipo + FIM_DE_LINHA;
		myString += "Lista de Jogos:" + FIM_DE_LINHA;

		Iterator itr = getMeusJogos().iterator();
		
		while (itr.hasNext()) {
			Jogo j = (Jogo) itr.next();
			myString += j.toString();
		}
		
		myString += FIM_DE_LINHA;
		myString += "Total de preco dos jogos: R$ " + this.calculaPrecoTotal() + FIM_DE_LINHA;
		myString += "--------------------------------------------";
		
		return myString;
	}


}
