/* 115211312 - Paulo Vitor Souto Dantas: LAB 06 - Turma 03 */
package loja;

import java.util.HashMap;

import jogo.Jogo;
import usuario.Noob;
import usuario.Usuario;

public class Loja {

	private static final String FIM_DE_LINHA = System.lineSeparator();

	private HashMap<String, Usuario> usuarios;
	private FactoryUsuario fabricaDeUsuarios;
	protected FactoryJogo fabricaDeJogo;

	public Loja() {

		usuarios = new HashMap<String, Usuario>();
		fabricaDeUsuarios = new FactoryUsuario();
		fabricaDeJogo = new FactoryJogo();
	}


	@Override
	public String toString() {

		String strFinal = "=== Central P2-CG ===" + FIM_DE_LINHA;

		for (String key : usuarios.keySet()) {
			strFinal += usuarios.get(key).toString();
		}

		strFinal += "--------------------------------------------";
		return strFinal;

	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((usuarios == null) ? 0 : usuarios.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object outroObj) {
		if(outroObj instanceof Loja) {
			Loja novaLoja = (Loja) outroObj;

			if(this.usuarios.equals(novaLoja.usuarios)) {
				return true;
			}else {
				return false;
			}

		}else {
			return false;
		}

	}


	public boolean criaUsuario(String nomeUsuario, String usernameUsuario, String tipo) {
		try {
			if(tipo.equalsIgnoreCase("noob")) {
				Usuario novoNoob = fabricaDeUsuarios.criaNoob(nomeUsuario, usernameUsuario);
				this.adicionaUsuario(novoNoob);
				return true;

			}else if(tipo.equalsIgnoreCase("veterano")) {
				Usuario novoVeterano = fabricaDeUsuarios.criaVeterano(nomeUsuario, usernameUsuario);
				this.adicionaUsuario(novoVeterano);
				return true;

			}else {
				throw new Exception(tipo + " nao eh um tipo de usuario reconhecido pela loja.");
			}

		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return false;

	}


	public String imprimeUsuarios() {
		return this.toString();
	}


	public boolean registraJogada(String login, String nomeDoJogo, int score, boolean zerou)  {
		Usuario novoUsuario = this.selecionaUsuario(login);

		try {

			if(novoUsuario == null) {
				throw new Exception("O usuario nao esta cadastrado.");
			} else {
				novoUsuario.registraJogada(nomeDoJogo, score, zerou);
				return true;
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	
	}


	public boolean adicionaUsuario(Usuario novoUsuario) throws Exception {
		if(usuarios.put(novoUsuario.getLogin(), novoUsuario) == null) {
			return true;
		}else {
			throw new Exception("Usuario ja esta cadastrado!");
		}

	}


	public boolean adicionaDinheiro(String login, double valor) {
		Usuario novoUsuario = usuarios.get(login);

		try {
			novoUsuario.depositaDinheiro(valor);
			return true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

		return false;

	}


	public void vendeJogo(String login, String nomeJogo, double precoJogo, String tipoJogo) {
		Usuario usuarioAtual = usuarios.get(login);
		Jogo novoJogo;
		try {
			
			if(tipoJogo.equalsIgnoreCase("RPG")) {
				novoJogo = fabricaDeJogo.criaRPG(nomeJogo, precoJogo);
			}else if(tipoJogo.equalsIgnoreCase("Luta")) {
				novoJogo = fabricaDeJogo.criaLuta(nomeJogo, precoJogo);
			}else if(tipoJogo.equalsIgnoreCase("Plataforma")) {
				novoJogo = fabricaDeJogo.criaPlataforma(nomeJogo, precoJogo);
			}else {
				throw new Exception(tipoJogo + " nao eh um tipo de Jogo reconhecido pela loja.");
			}

			if(usuarioAtual == null) {
				throw new Exception("Nao existe usuario com esse login.");

			}else {
				usuarioAtual.compraJogo(novoJogo);			
			}

		} catch(Exception e) {
			System.out.println(e.getMessage());
		}


	}


	public void upgrade(String login)   {

		Object novoObjeto = usuarios.get(login);

		try {
			if(novoObjeto == null) {
				throw new Exception("Usuario nao existe!");
			}

			if(novoObjeto instanceof Noob) {
				Usuario usuarioAntigo = (Usuario) novoObjeto;

				if(usuarioAntigo.getX2p() >= 1000) {

					String novoNome = usuarioAntigo.getNome();
					String novoLogin = usuarioAntigo.getLogin();
					usuarios.remove(usuarioAntigo.getLogin());

					this.criaUsuario(novoNome, novoLogin, "Veterano");

					Usuario novoVeterano = usuarios.get(novoLogin);
					novoVeterano.jogos = usuarioAntigo.jogos;
					novoVeterano.setDinheiro(usuarioAntigo.getDinheiro());
					novoVeterano.setX2p(usuarioAntigo.getX2p());
					
				}else {
					throw new Exception("Usuario nao possui x2p suficiente para se tornar Veterano!");
				}

			}else {
				throw new Exception("Usuario ja eh Veterano!");
			}

		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}


	public Usuario selecionaUsuario(String login) {
		return usuarios.get(login);
	}


}
