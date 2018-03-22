package administradores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import estadia.Estadia;
import exceptions.SistemaException;
import exceptions.StringInvalidaException;
import hospedes.Hospede;
import quarto.Quarto;

//OBS GERAIS: TRATAR FORMATACAO DA DATA DE NASCIMENTO; TRATAR FORMATACAO DO  EMAIL

public class Administrador implements AdministradorIF {

	private ArrayList<HistoricoCheckout> historicoHotel;
	private List<Hospede> hospedes;
	private HashMap<Hospede, List<Estadia>> mapaDeHospedagens;
	private double totalLucroHotel;
	private int numeroDeHospedes;

	public Administrador() {
		this.hospedes = new ArrayList<>();
		this.mapaDeHospedagens = new HashMap<>();
		this.numeroDeHospedes = 0;
		this.historicoHotel = new ArrayList<>();
		this.totalLucroHotel = 0;
	}

	@Override
	public int cadastroHospede(String nome, String email, String dataNascimento) throws SistemaException {


		testString(nome, "Nome nao pode ser vazio ou nulo");
		testString(email, "Email nao pode ser vazio ou nulo");
		testString(dataNascimento, "Data nao pode ser nula ou vazia");

		Hospede novoHospede = new Hospede(nome, email, dataNascimento);
		this.hospedes.add(novoHospede);
		this.numeroDeHospedes += 1;
		
		return numeroDeHospedes;

	}

	@Override
	public void removeHospede(String email)throws Exception {

		Hospede hospede = consultaHospede(email);
		this.hospedes.remove(hospede);
	}

	/***
	 * O Id vai retornar informar o indice em que o hospede vai estar armazenado 
	 * na lista, basta pegar o hospede da lista e chamar o metodo getInfoHospede dele[
	 * e passar o mesmo atributo
	 * @throws SistemaException 
	 */
	@Override
	public String getInfoHospede(int id, String atributo) throws SistemaException {
		Hospede hospedeAtual = hospedes.get(id);
		return hospedeAtual.getInfoHospede(atributo);
	}

	/***
	 * Aqui é necessario verificar se o atributo é valido(Email, nome, ou dataNascimento)
	 * pode ser um switch, e depois, settar o valor desse atributo chamando o set de 
	 * hospede do atributo pedido
	 * @throws SistemaException 
	 */
		
	@Override
	public void atualizaCadastro(int id, String atributo, String valor) 
				throws SistemaException {
		
		String novoAtributo = atributo.trim().toUpperCase();
		Hospede novoHospede = hospedes.get(id);

		switch(novoAtributo) {
		case "NOME":
			novoHospede.setNome(valor);
		case "EMAIL":
			novoHospede.setEmail(valor);
		case "DATADENASCIMENTO":
			novoHospede.setDataNascimento(valor);
		default:
			throw new SistemaException("Atributo de hospede invalido!");
		}

	}

	/***
	 * Daqui pra baixo é da estadia, provavelmente criar um Mapa associando hospedes a quartos
	 * mas nao sei ao certo, se quiser, a gente comenta isso quando eu chegar em casa, depois das 
	 * 16h, mas se tu ver que consegue implemetar pode ir desenrolando
	 * @throws SistemaException 
	 */
	@Override
	public void realizaCheckin(String email, int dias, String quarto,
			String tipoQuarto) throws SistemaException {
		
		Estadia novaEstadia = new Estadia(dias, quarto, tipoQuarto);
		Hospede novoHospede = this.buscaHospedeEmail(email);

		if(!this.hospedes.contains(novoHospede)){
			throw new SistemaException("Hospede nao cadastrado");
		
		}else{
			
			if(this.verificaHospedeNoHotel(novoHospede)) {
				List<Estadia> estadiasDoHospede = this.mapaDeHospedagens.get(novoHospede);
				estadiasDoHospede.add(novaEstadia);
			
			}else {
				
				ArrayList<Estadia> estadias = new ArrayList<>();
				estadias.add(novaEstadia);
				this.mapaDeHospedagens.put(novoHospede, estadias);
			}	
		}
		
	}

	@Override
	public String getInfoHospedagem(String email, String atributo) throws SistemaException {
		Hospede novoHospede = this.buscaHospedeEmail(email);
		List<Estadia> hospedagensDoHospede = this.mapaDeHospedagens.get(novoHospede);

		String novoAtributo = atributo.trim().toUpperCase();
		switch(novoAtributo) {
		case "HOSPEDAGENSATIVAS":
			return String.valueOf(hospedagensDoHospede.size());

		case "QUARTO":
			String idDosquartos = "";
			idDosquartos += hospedagensDoHospede.get(0).getQuarto().getID();

			for (int i = 1; i < hospedagensDoHospede.size(); i++) {
				Quarto quartoAtual = hospedagensDoHospede.get(i).getQuarto();
				idDosquartos += "," + quartoAtual.getID();
			}
			
			return idDosquartos;

		case "TOTAL":
			double valorTotal = 0;
			
			for (Estadia estadia : hospedagensDoHospede) {
				valorTotal += estadia.getQuarto().getPrecoDaDiaria();
			}
			
			int valorInteiro = (int) valorTotal;
			String valorFinal = String.valueOf(valorInteiro);
			return "R$" + valorFinal + ",00";
			
		}
		
		throw new SistemaException("Atributo Invalido!");

	}

	@Override
	public double realizaCheckout(String email, String quarto) throws SistemaException {
		Hospede hospede = buscaHospedeEmail(email);
		
		if(this.mapaDeHospedagens.containsKey(hospede)){
			
			for(Estadia estadia : mapaDeHospedagens.get(hospede)){
			
				if(estadia.getQuarto().getID().equalsIgnoreCase(quarto)){
					
					HistoricoCheckout historico = new HistoricoCheckout(LocalDate.now(),
							hospede.getNome(),estadia.getQuarto().getID(),
							estadia.precoEstadia());
					
					this.historicoHotel.add(historico);
					this.mapaDeHospedagens.get(hospede).remove(estadia);
					totalLucroHotel += estadia.precoEstadia();
					
					return estadia.precoEstadia();
				}
			}
		}else{
			throw new SistemaException("Hospede nao esta cadastrado no sistem");
		}
		
		throw new SistemaException("Hospede nao possui esta estadia");
	}

	@Override
	public String consultaTranslacoes(String atributo) throws SistemaException {
		atributo = atributo.toUpperCase();
		
		switch(atributo){
			case "QUANTIDADE":
				String qtd = String.valueOf(this.historicoHotel.size());
				return qtd;
			case "NOME":
				String nomes = this.hospedes.get(0).getNome();
				for(int i = 1 ; i < this.historicoHotel.size() ; i++){
					nomes += ";" + this.historicoHotel.get(i).getNomeHospede();
				}
				return nomes;
			case "TOTAL":
				return String.valueOf(this.totalLucroHotel);
			default:
				throw new SistemaException("Atributo invalido");
		}
	}
	
	public String consultaTranslacoes(String atributo, int indice) throws SistemaException{
		HistoricoCheckout translacao = this.historicoHotel.get(indice);
		atributo = atributo.toUpperCase();
		
		switch(atributo){
			case "NOME":
				return translacao.getNomeHospede();
			case "TOTAL":
				return String.valueOf(translacao.getTotalPago());
			default:
				throw new SistemaException("Atributo invalido");
				
		}
	}

	public void testString(String stringTest, String erroMsg)throws SistemaException{

		if(stringTest == null || stringTest.trim().isEmpty()){
			throw new StringInvalidaException(erroMsg);
		}
	}

	public Hospede buscaHospedeEmail(String email)throws SistemaException{

		for(Hospede hospede : this.hospedes){
			if(email.equalsIgnoreCase(hospede.getEmail())){
				return hospede;
			}
		}
		throw new SistemaException("Hospede de email " + email + " nao foi cadastrado(a)");
	}

	public Hospede buscaHospedeNome(String nome)throws SistemaException{

		for(Hospede hospede : this.hospedes){
			if(nome.equalsIgnoreCase(hospede.nome)){
				return hospede;
			}
		}

		throw new SistemaException("Hospede de nome " + nome +" nao foi cadastrado(a)");
	}

	public Hospede buscaHospedeDataNascimento(String dataNascimento)throws SistemaException{

		for(Hospede hospede : this.hospedes){
			if(dataNascimento.equalsIgnoreCase(hospede.getDataNascimento())){
				return hospede;
			}
		}

		throw new SistemaException("Hospede de data de nascimento" + dataNascimento + " nao foi cadastrado(a)");
	}

	public Hospede consultaHospede(String atributo)throws SistemaException{
		atributo = atributo.toUpperCase();

		switch(atributo){
		case "NOME":
			return buscaHospedeNome(atributo);
		case "EMAIL":
			return buscaHospedeEmail(atributo);
		case "DATANASCIMENTO":
			return buscaHospedeDataNascimento(atributo);
		default:
			throw new StringInvalidaException("Hospede nao possui tal atributo");
		}
	}

	public boolean verificaHospedeNoHotel(Hospede hospedeAtual) {
		if(mapaDeHospedagens.containsKey(hospedeAtual)) {
			return true;
		}else {
			return false;
		}

	}


}



