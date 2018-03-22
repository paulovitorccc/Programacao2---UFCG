package restaurante;

import exceptions.RemocaoInvalidaException;
import exceptions.SistemaException;

public interface InterfaceRestaurante {

	/**
	 * o metodo cadastraPratos ira cadastrar um novo prato ao cardapio
	 * 
	 * @param nome
	 * @param preco
	 * @param descricao
	 * @return
	 * @throws SistemaException
	 */
	public boolean cadastraPrato(String nome, double preco, String descricao) throws SistemaException;

	/**
	 * o metodo cadastraRefeicao ira cadastrar uma nova refeicao ao cardapio
	 * 
	 * @param nome
	 * @param descricao
	 * @param componentes
	 * @return
	 * @throws SistemaException
	 */
	public boolean cadastraRefeicao(String nome, String descricao, String componentes) throws SistemaException;

	/**
	 * o metodo removePrato ira remover o prato do cardapio
	 * 
	 * @param nome
	 * @return
	 * @throws RemocaoInvalidaException
	 */
	public boolean removePrato(String nome) throws RemocaoInvalidaException;

	/**
	 * o metodo removeRefeicao ira remover a refeicao do cardapio
	 * 
	 * @param nome
	 * @return
	 * @throws RemocaoInvalidaException
	 */
	public boolean removeRefeicao(String nome) throws RemocaoInvalidaException;

	/**
	 * o metodo consultaRestaurante ira procurar no cardapio se ha um prato ou
	 * uma refeicao com o nome e atributo recebido como parametro
	 * 
	 * @param nome
	 * @param atributo
	 * @return
	 * @throws SistemaException
	 */
	public String consultaRestaurante(String nome, String atributo) throws SistemaException;

}