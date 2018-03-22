/* 115211312 - Paulo Vítor Souto Dantas: LAB 7 - Turma 03 */
package loja;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import easyaccept.EasyAccept;
import excecoes.PrecoInvalidoException;
import excecoes.StringInvalidaException;
import excecoes.UpgradeInvalidoException;
import excecoes.ValorInvalidoException;
import jogo.FactoryJogo;
import jogo.Jogabilidade;
import jogo.Jogo;
import usuario.FactoryUsuario;
import usuario.Noob;
import usuario.Usuario;
import usuario.Veterano;

public class LojaController {

	public static final String FIM_DE_LINHA = System.lineSeparator();

	private List<Usuario> meusUsuarios;
	private HashMap<String, Jogabilidade> mapJogabilidades;
	private FactoryUsuario factoryUsuario;
	private FactoryJogo factoryJogo;

	public LojaController() {

		this.meusUsuarios = new ArrayList<Usuario>();
		this.initializeMap();
		this.factoryUsuario = new FactoryUsuario();
		this.factoryJogo = new FactoryJogo();
	}


	public void adicionaUsuario(String nome, String login, String tipo) {
		try {
			Usuario novoUser = this.criaUsuario(nome, login, tipo);
			meusUsuarios.add(novoUser);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}


	public Usuario criaUsuario(String nome, String login, String tipo) throws StringInvalidaException {
		//chamada polimórfica
		Usuario novoUser = factoryUsuario.criaUsuario(nome, login, tipo);
		meusUsuarios.add(novoUser);
		return novoUser;
	}


	public void vendeJogo(String jogoNome, double preco, String jogabilidades, String estiloJogo, String loginUser) {

		try {
			Usuario buscado = this.buscaUsuario(loginUser);

			Set<Jogabilidade> tiposJogabilidades = this.createJogabilidades(jogabilidades);
			Jogo jogoVendido = this.criaJogo(jogoNome, preco, tiposJogabilidades, estiloJogo);

			buscado.compraJogo(jogoVendido);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


	public void registraJogada(String login, String nomeJogo, int score, boolean venceu) {
		try {
			Usuario usr = this.buscaUsuario(login);

			usr.registraJogada(nomeJogo, score, venceu);

		} catch (Exception e) {
			e.getMessage();
		}

	}


	public void adicionaCredito(String login, double credito) {
		try {
			
			if (credito < 0) {
				throw new ValorInvalidoException("Credito nao pode ser negativo");
			}
			
			Usuario user = this.buscaUsuario(login);

			user.setCredito(user.getCredito() + credito);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


	public Usuario buscaUsuario(String login) {
		Usuario buscado = null;

		try {
			for (int i = 0; i < meusUsuarios.size(); i++) {

				if (meusUsuarios.get(i).getLogin().equals(login)) {
					buscado = meusUsuarios.get(i);

				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return buscado;
	}

	
	public void punir(String login, String nomeJogo, int scoreObtido, boolean zerou) throws StringInvalidaException {
		Usuario user = this.buscaUsuario(login);
		user.punir(nomeJogo, scoreObtido, zerou);
	}
	
	
	public void recompensar(String login, String nomeJogo, int scoreObtido, boolean zerou) throws StringInvalidaException {
		Usuario user = this.buscaUsuario(login);
		user.recompensar(nomeJogo, scoreObtido, zerou);
	}

	
	public void upgrade(String login) throws UpgradeInvalidoException, StringInvalidaException {
		Usuario antigo = this.buscaUsuario(login);

		if (antigo.getStatusDoUsuario() instanceof Veterano) {
			throw new UpgradeInvalidoException("Upgrade impossivel de ser realizado, usuario ja eh veterano");

		} else if (antigo.getXp2() < 1000) {
			throw new UpgradeInvalidoException("Impossivel realizar upgrade, quantidade de x2p insuficiente!");
		}

		antigo.setStatusDoUsuario(new Veterano());;

	}


	public void downgrade(String login) throws UpgradeInvalidoException, StringInvalidaException {
		Usuario antigo = this.buscaUsuario(login);

		if (antigo.getStatusDoUsuario() instanceof Noob) {
			throw new UpgradeInvalidoException("Downgrade impossivel de ser realizado, usuario ja eh Noob");

		} else if (antigo.getXp2() > 1000) {
			throw new UpgradeInvalidoException("Impossivel realizar downgrade, quantidade de x2p insuficiente!");
		}

		antigo.setStatusDoUsuario(new Noob());;

	}


	public double confereCredito(String login) {

		try {
			Usuario procurado = this.buscaUsuario(login);
			return procurado.getCredito();

		} catch (Exception e) {
			e.getMessage();
		}

		
		return 0;
	}


	public String informacaoUsuarios() {
		String myString = "=== Central P2-CG ===" + FIM_DE_LINHA + FIM_DE_LINHA;

		for (int i = 0; i < meusUsuarios.size(); i++) {
			myString += meusUsuarios.get(i).toString() + FIM_DE_LINHA;
		}

		return myString;
	}


	public int getX2p(String login) {
		Usuario buscado = this.buscaUsuario(login);
		return buscado.getXp2();
	}


	private Jogo criaJogo(String jogoNome, double preco, Set<Jogabilidade> tiposJogabilidades, String estiloJogo) throws StringInvalidaException, PrecoInvalidoException {
		String estilo = estiloJogo.toLowerCase();
		//chamada polimórfica
		return this.factoryJogo.criaJogo(jogoNome, preco, tiposJogabilidades, estilo);
	}


	private Set<Jogabilidade> createJogabilidades(String names1) {
		Set<Jogabilidade> jogabilidades = new HashSet<Jogabilidade>();

		String[] listofNames = names1.split(",");

		for (int i = 0; i < listofNames.length; i++) {
			String element = listofNames[i].toUpperCase();

			if (element != null) {
				Jogabilidade tipojogabilidade = mapJogabilidades.get(element);
				jogabilidades.add(tipojogabilidade);
			}

		}

		return jogabilidades;

	}


	private void initializeMap() {
		this.mapJogabilidades = new HashMap<String, Jogabilidade>();

		mapJogabilidades.put("ONLINE", Jogabilidade.ONLINE);
		mapJogabilidades.put("OFFLINE", Jogabilidade.OFFLINE);
		mapJogabilidades.put("COMPETITIVO", Jogabilidade.COMPETITIVO);
		mapJogabilidades.put("COOPERATIVO", Jogabilidade.COOPERATIVO);
		mapJogabilidades.put("MULTIPLAYER", Jogabilidade.MULTIPLAYER);

	}


	public static void main(String[] args) {
		args = new String[] { "loja.LojaController", "acceptance_test/us1.txt", "acceptance_test/us2.txt",  "acceptance_test/us3.txt" };
		EasyAccept.main(args);

	}


}
