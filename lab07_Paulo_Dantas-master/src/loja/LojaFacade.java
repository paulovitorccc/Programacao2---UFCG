/* 115211312 - Paulo Vítor Souto Dantas: LAB 7 - Turma 03 */
package loja;

import excecoes.StringInvalidaException;
import excecoes.UpgradeInvalidoException;

public class LojaFacade {

	public LojaController lojaController;

	public LojaFacade() {

		this.lojaController = new LojaController();
	}


	public void adicionaUsuario(String nome, String login, String tipo) {
		this.lojaController.adicionaUsuario(nome, login, tipo);
	}


	public void criaUsuario(String nome, String login, String tipo) throws StringInvalidaException {
		this.lojaController.criaUsuario(nome, login, tipo);
	}


	public void vendeJogo(String jogoNome, double preco, String jogabilidades, String estiloJogo, String loginUser) {
		this.lojaController.vendeJogo(jogoNome, preco, jogabilidades, estiloJogo, loginUser);
	}


	public void registraJogada(String login, String nomeJogo, int score, boolean venceu) {
		this.lojaController.registraJogada(login, nomeJogo, score, venceu);

	}


	public void adicionaCredito(String login, double credito) {
		this.lojaController.adicionaCredito(login, credito);
	}


	public void buscaUsuario(String login) {
		this.lojaController.buscaUsuario(login);
	}


	public void upgrade(String login) throws UpgradeInvalidoException, StringInvalidaException {
		this.lojaController.upgrade(login);
	}


	public void confereCredito(String login) {
		this.lojaController.confereCredito(login);
	}

	
	public void informacaoUsuarios() {
		this.lojaController.informacaoUsuarios();
	}

	
	public void getX2p(String login) {
		this.lojaController.getX2p(login);
	}

	
}
