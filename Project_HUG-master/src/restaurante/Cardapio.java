package restaurante;

import java.util.ArrayList;
import java.util.List;

import exceptions.SistemaException;

public class Cardapio {

	private List<Prato> pratos;
	private List<Refeicao> refeicoes;

	public Cardapio( ) {

		this.pratos = new ArrayList<>();
		this.refeicoes = new ArrayList<>();
	}

	/**
	 * o metodo getPratos retorna a lista de pratos
	 * 
	 * @return pratos - List<Pratos>
	 */
	public List<Prato> getPratos() {
		return pratos;
	}

	/**
	 * o metodo getPrato retorna a lista de pratos que contem no cardapio
	 * 
	 * @param pratos
	 */
	public void setPratos(List<Prato> pratos) {
		this.pratos = pratos;
	}

	/**
	 * o metodo getRefeicao retorna a lista de refeicoes
	 * 
	 * @return refeicao - List<Refeicao>
	 */
	public List<Refeicao> getRefeicoes() {
		return refeicoes;
	}

	/**
	 * o metodo getRefeicao retorna a lista de refeicoes que contem no cardapio
	 * 
	 * @param refeicoes
	 */
	public void setRefeicoes(List<Refeicao> refeicoes) {
		this.refeicoes = refeicoes;
	}

	/**
	 * o metodo buscaPrato percorre a lista de pratos para conferir se ha um
	 * prato com o nome e o preco recebido como parametro
	 * 
	 * @param nome
	 * @return prato - Prato
	 */
	public Prato buscaPrato(String nome) {

		for (Prato prato : pratos) {

			if (nome.equalsIgnoreCase(prato.getNome())) {

				return prato;
			}
		}
		return null;
	}

	/**
	 * o metodo buscaRefeicao percorre a lista de refeicoes para confererir se
	 * ha uma refeicao com o nome recebido como parametro
	 * 
	 * @param nome
	 * @return refeicao - Refeicao
	 */
	public Refeicao buscaRefeicao(String nome) {

		for (Refeicao refeicao : refeicoes) {

			if (nome.equalsIgnoreCase(refeicao.getNome())) {

				return refeicao;
			}
		}
		return null;
	}

	/**
	 * o metodo criaRefeicao ira criar uma lista de pratos que ira compor a
	 * refeicao
	 * 
	 * @param componentes
	 * @return prato - ArrayList<Prato>
	 */
	public ArrayList<Prato> criaRefeicao(String componentes) {

		ArrayList<Prato> pratos = new ArrayList<>();
		String[] nomes = componentes.split(";");

		for (String nome : nomes) {

			Prato prato = buscaPrato(nome);

			if (prato != null) {

				pratos.add(prato);
			}
		}
		return pratos;
	}

	/**
	 * o metodo cadastraPratos ira cadastrar um novo prato ao cardapio
	 * utilizando os paramentros para sua composicao
	 * @param nome
	 * @param preco
	 * @param descricao
	 * @return boolean
	 */
	public boolean cadastraPrato(String nome, double preco, String descricao) {

		Prato prato = new Prato(nome, preco, descricao);

		pratos.add(prato);

		return true;
	}

	/**
	 * o metodo cadastraRefeicao ira cadastrar uma nova refeicao ao cardapio
	 * 
	 * @param nome
	 * @param descricao
	 * @param componentes
	 * @return boolean
	 * @throws SistemaException
	 */
	public boolean cadastraRefeicao(String nome, String descricao,
						String componentes) throws SistemaException {

		ArrayList<Prato> pratos = criaRefeicao(componentes);

		Refeicao refeicao = new Refeicao(nome, descricao, pratos);

		refeicoes.add(refeicao);

		return true;
	}

	/**
	 * o metodo removePrato ira remover o prato do cardapio
	 * procurando-a pelo nome recebido como parametro 
	 * @param nome 
	 * @return boolean
	 */
	public boolean removePrato(String nome) {

		Prato prato = buscaPrato(nome);

		if (prato != null) {

			pratos.remove(prato);

			return true;
		}

		return false;
	}

	/**
	 * o metodo removeRefeicao ira remover a refeicao do cardapio procurando a refeicao com o nome
	 * que eh recebido como parametro 
	 * @param nome
	 * @return boolean
	 */
	public boolean removeRefeicao(String nome) {

		Refeicao refeicao = buscaRefeicao(nome);

		if (refeicao != null) {

			refeicoes.remove(refeicao);

			return true;
		}

		return false;
	}

	/**
	 * o metodo consultaPrato ira percorrer a lista de prato para verificar se
	 * ha um prato com o nome e um atributo recebido como parametro
	 * 
	 * @param nome
	 * @param atributo
	 * @return String
	 */
	public String consultaPrato(String nome, String atributo) {

		Prato prato = buscaPrato(nome);

		if (atributo.equalsIgnoreCase("preco")) {

			String preco = String.valueOf(prato.getPreco());

			return preco;
		} else if (atributo.equalsIgnoreCase("descricao")) {

			return prato.getDescricao();
		} else {

			return prato.getNome();
		}
	}

	/**
	 * o metodo consultaPrato ira percorrer a lista de refeicoes para verificar
	 * se ha uma refeicao com o nome e um atributo recebido como parametro
	 * 
	 * @param nome
	 * @param atributo
	 * @return String
	 */
	public String consultaRefeicao(String nome, String atributo) {

		Refeicao refeicao = buscaRefeicao(nome);

		if (atributo.equalsIgnoreCase("preco")) {

			String preco = String.valueOf(refeicao.calculaPreco());

			return preco;
		} else if (atributo.equalsIgnoreCase("descricao")) {

			return refeicao.getDescricaoCompleta();
		} else {

			return refeicao.getNome();
		}

	}

	/**
	 * o metodo consultaRestaurante ira procurar no cardapio se ha um prato ou
	 * uma refeicao com o nome e atributo recebido como parametro
	 * 
	 * @param nome
	 * @param atributo
	 * @return String
	 * @throws SistemaException
	 */
	public String consultaRestaurante(String nome, String atributo)throws SistemaException {

		verificaAtributoInvalido(atributo);

		if (buscaPrato(nome) != null) {

			return consultaPrato(nome, atributo);
		} else {

			if (buscaRefeicao(nome) != null) {

				return consultaRefeicao(nome, atributo);
			} else {

				throw new SistemaException("Prato e refeicao inexistente.");
			}
		}
	}

	/**
	 * o metodo verificaAtributoInvalido ira verificar se o atributo recebido
	 * como paramentro eh um preco, uma descricao ou um nome
	 * 
	 * @param atributo
	 * @throws SistemaException
	 */
	public void verificaAtributoInvalido(String atributo) throws SistemaException {

		if (!atributo.equalsIgnoreCase("preco") && !atributo.equalsIgnoreCase("descricao")
				&& !atributo.equalsIgnoreCase("nome")) {

			throw new SistemaException("Atributo invalido.");
		}
	}

	/**
	 * no metodo equals comparamos o cardapio pela lista de pratos e refeicao o
	 * mesmo possui
	 */
	@Override
	public boolean equals(Object outroObjeto) {

		if (outroObjeto instanceof Cardapio) {
			Cardapio outroCardapio = (Cardapio) outroObjeto;

			if (this.getPratos() == outroCardapio.getPratos() 
					&& this.getRefeicoes() == outroCardapio.getRefeicoes()) {

				return true;
			}
		}

		return false;
	}

	/**
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pratos == null) ? 0 : pratos.hashCode());
		result = prime * result + ((refeicoes == null) ? 0 : refeicoes.hashCode());
		return result;
	}
}