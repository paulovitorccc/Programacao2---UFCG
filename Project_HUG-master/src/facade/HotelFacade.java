package facade;

import administradores.Administrador;
import easyaccept.EasyAccept;
import exceptions.SistemaException;
import hospedes.Hospede;
import restaurante.Cardapio;

public class HotelFacade {
	
	private Cardapio cardapio;	
	private Administrador adm;
	public HotelFacade(){
		
	}
	//TestCase 1
		public int cadastraHospede(String nome, String email, String dataNascimento) 
				throws SistemaException{
			return adm.cadastroHospede(nome, email, dataNascimento);
		}
		
		public void removeHospede(String email) throws Exception{
			adm.removeHospede(email);
		}
		
		public String getInfoHospede(int id, String atributo) throws SistemaException{
			return adm.getInfoHospede(id, atributo);
		}
		
		public void atualizaCadastro(int id, String atributo, String valor) 
				throws SistemaException{
			atualizaCadastro(id, atributo, valor);
		}
		
	//TestCase 2
		public void realizaCheckin(String email, int dias, String quarto,
				String tipoQuarto) throws SistemaException{
			adm.realizaCheckin(email, dias, quarto, tipoQuarto);
		}
		
		public String getInfoHospedagem(String email, String atributo) 
				throws SistemaException{
			return adm.getInfoHospedagem(email, atributo);
		}
		
	//TestCase 3
		public double realizaCheckout(String email, String quarto) throws SistemaException{
			return adm.realizaCheckout(email, quarto);
		}
		
		public Hospede consultaTranslacoes(String atributo) throws SistemaException{
			return adm.consultaHospede(atributo);
		}
		
	//TestCase 4
		
		/**
		 * o metodo cadastraPratos ira cadastrar um novo prato ao cardapio
		 * utilizando os paramentros para sua composicao
		 * @param nome
		 * @param preco
		 * @param descricao
		 * @return boolean
		 */
		
		public void cadastraPrato(String nome, double preco, String descrisao){
			
			cardapio.cadastraPrato(nome, preco, descrisao);			
		}
		
		/**
		 * o metodo cadastraRefeicao ira cadastrar uma nova refeicao ao cardapio
		 * @param nome
		 * @param preco
		 * @param descrisao
		 */
		public void cadastraRefeicao(String nome, String descricao, String componentes) throws SistemaException{
			
			cardapio.cadastraRefeicao(nome, descricao, componentes);
		}

		/**
		 * o metodo consultaRestaurante ira procurar no cardapio se ha um prato ou
		 * uma refeicao com o nome e atributo recebido como parametro
		 * @param nome
		 * @param atributo
		 * @return
		 * @throws SistemaException
		 */
		public String consultaRestaurante(String nome, String atributo) throws SistemaException{
			
			return cardapio.consultaRestaurante(nome, atributo);
		}
		
		public static void main(String[] args) {
		    args = new String[] {"facade.HotelFacade", "easyaccept/testes_uc1.txt", "easyaccept/testes_uc2.txt",
		    		"easyaccept/testes_uc3.txt", "easyaccept/testes_uc4.txt"}; 
		    EasyAccept.main(args);
		}
}
