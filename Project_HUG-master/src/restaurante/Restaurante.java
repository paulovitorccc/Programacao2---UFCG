package restaurante;

import exceptions.SistemaException;

public class Restaurante implements InterfaceRestaurante{
	
	private Cardapio cardapio;
	
	public Restaurante(){
		
		this.cardapio = new Cardapio();
	}
	
	/**
	 * o metodo cadastraPratos ira cadastrar um novo prato ao cardapio
	 */
	@Override
	public boolean cadastraPrato(String nome, double preco, String descricao) {
		
		return cardapio.cadastraPrato(nome, preco, descricao);
	}

	/**
	 *  o metodo cadastraRefeicao ira cadastrar uma nova refeicao ao cardapio
	 */
	@Override
	public boolean cadastraRefeicao(String nome, String descricao,
			String componentes)throws SistemaException {
		
		return cardapio.cadastraRefeicao(nome, descricao, componentes);
	}
	
	/**
	 * o metodo removePrato ira remover o prato do cardapio
	 */
	@Override
	public boolean removePrato(String nome) {
		
		return cardapio.removePrato(nome);
	}
	
	/**
	 * o metodo removeRefeicao ira remover a refeicao do cardapio
	 */
	@Override
	public boolean removeRefeicao(String nome) {
		
		return cardapio.removeRefeicao(nome);
	}
	
	/**
	 * o metodo consultaRestaurante ira procurar no cardapio se ha um prato ou uma refeicao
	 * com o nome e atributo recebido como parametro
	 */
	@Override
	public String consultaRestaurante(String nome, String atributo)throws SistemaException {
		
		return cardapio.consultaRestaurante(nome, atributo);
	}
	
}