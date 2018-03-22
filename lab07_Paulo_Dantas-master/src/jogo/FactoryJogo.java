/* 115211312 - Paulo Vítor Souto Dantas: LAB 7 - Turma 03 */
package jogo;

import java.util.Set;

import excecoes.PrecoInvalidoException;
import excecoes.StringInvalidaException;

public class FactoryJogo {

	public Jogo criaJogo(String nome, double preco, Set<Jogabilidade> jogabilidades, String estilo) throws StringInvalidaException, PrecoInvalidoException {
		
		if (estilo.equalsIgnoreCase("rpg")) {
			return  this.criaRpg(nome, preco, jogabilidades);
		} else if (estilo.equalsIgnoreCase("plataforma")) {
			return this.criaPlataforma(nome, preco, jogabilidades);
		} else if (estilo.equalsIgnoreCase("luta")) {
			return this.criaLuta(nome, preco, jogabilidades);
		} else {
			throw new StringInvalidaException("Estilo de jogo nao identificado pela loja.");
		}
		
	}
	
	public Jogo criaRpg(String nome, double preco) throws StringInvalidaException, PrecoInvalidoException {
		return new Rpg(nome, preco);
	}
	
	
	public Jogo criaRpg(String nome, double preco, Set<Jogabilidade> jogabilidades) throws StringInvalidaException, PrecoInvalidoException {
		return new Rpg(nome, preco, jogabilidades);
	}


	public Jogo criaLuta(String nome, double preco) throws StringInvalidaException, PrecoInvalidoException {
		return new Luta(nome, preco);
	}
	
	
	public Jogo criaLuta(String nome, double preco, Set<Jogabilidade> jogabilidades) throws StringInvalidaException, PrecoInvalidoException {
		return new Luta(nome, preco, jogabilidades);
	}


	public Jogo criaPlataforma(String nome, double preco) throws StringInvalidaException, PrecoInvalidoException {
		return new Plataforma(nome, preco);
	}

	
	public Jogo criaPlataforma(String nome, double preco, Set<Jogabilidade> jogabilidades) throws StringInvalidaException, PrecoInvalidoException {
		return new Plataforma(nome, preco, jogabilidades);
	}

}