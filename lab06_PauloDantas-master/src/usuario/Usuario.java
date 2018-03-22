/* 115211312 - Paulo Vitor Souto Dantas: LAB 06 - Turma 03 */
package usuario;

import java.util.HashSet;

import jogo.Jogo;

public abstract class Usuario {

	private static final String FIM_DE_LINHA = System.lineSeparator();
	
	protected String nome;
	protected String login;
	public HashSet<Jogo> jogos;
	protected double dinheiro;
	protected int x2p;


	public Usuario(String nomeUsuario, String loginUsuario, int x2pInicial) throws Exception {
		if(nomeUsuario == null || nomeUsuario.trim().equals("")) {
			throw new Exception("O nome do usuario nao pode ser vazio ou nulo.");
		}
		if(loginUsuario == null || loginUsuario.trim().equals("")) {
			throw new Exception("O login do usuario nao pode ser vazio ou nulo.");
		}
		if(x2pInicial < 0) {
			throw new Exception("O x2p nao pode ser menor que 0.");
		}
		
		this.nome = nomeUsuario;
		this.login = loginUsuario;
		jogos = new HashSet<Jogo>();
		this.dinheiro = 0.0;
		this.x2p = x2pInicial;
	}

	
	@Override
	public String toString() {
		
		String tipo = this.getClass().getSimpleName();
		double precoTotal = 0;
		
		String strFinal = this.login + FIM_DE_LINHA + this.nome + " - " + "Jogador " + tipo + FIM_DE_LINHA + "Lista de Jogos:" + FIM_DE_LINHA;
		
		for (Jogo jogo : jogos) {
			precoTotal += jogo.getPreco();		
			strFinal += jogo.toString();
		}
		
		strFinal += "Total de preço dos jogos: R$ " + precoTotal + FIM_DE_LINHA + FIM_DE_LINHA;
		return strFinal;
				
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object outroObj) {
		if(outroObj instanceof Usuario) {
			Usuario novoUsuario = (Usuario) outroObj;

			if(novoUsuario.getLogin().equals(getLogin())) {
				return true;
			}else {
				return false;
			}

		}else {
			return false;
		}
		
	}


	public abstract boolean compraJogo(Jogo novoJogo) throws Exception;


	public void registraJogada(String nomeDoJogo, int score, boolean zerou) throws Exception {

		Jogo novoJogo = this.procuraJogoPorNome(nomeDoJogo);
		
		if(novoJogo == null) {
			throw new Exception("Voce nao possui esse jogo."); 
		}else {
			this.x2p += novoJogo.registraJogada(score, zerou);
		}

	}


	public Jogo procuraJogoPorNome(String nomeDoJogo) {

		for (Jogo jogo : jogos) {
			if (jogo.getNome().equals(nomeDoJogo)) {
				return jogo;					
			}
		}

		return null;

	}


	public void depositaDinheiro(double valor) throws Exception {
		if(valor < 0) {
			throw new Exception("Voce nao pode adicionar valor negativo ao seu dinheiro!");
		}else {
			this.dinheiro += valor;
		}
	}


	public void sacaDinheiro(double valor) throws Exception {
		if(valor > this.dinheiro) {
			throw new Exception("Voce nao possui dinheiro suficiente!");
		}else {
			this.dinheiro -= valor;
		}
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


	public double getDinheiro() {
		return dinheiro;
	}


	public void setDinheiro(double dinheiro) {
		this.dinheiro = dinheiro;
	}


	public int getX2p() {
		return x2p;
	}


	public void setX2p(int x2p) {
		this.x2p = x2p;
	}

}
