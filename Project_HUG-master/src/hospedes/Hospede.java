package hospedes;

import exceptions.HospedeException;
import exceptions.SistemaException;

public class Hospede implements HospedeIF{

	public String nome;
	public String email;
	public String dataNascimento;
	
	/***
	 * Classe que modela os hospedes no sistema
	 * 
	 * @param nome - String
	 * @param email - String
	 * @param dataNascimento - String
	 */
	public Hospede(String nome, String email, String dataNascimento)throws SistemaException{
		
		if(verificaEmailValido(email)){
			this.nome = nome;
			this.email = email;
			this.dataNascimento = dataNascimento;
		}else{
			throw new HospedeException("Email invalido");
		}
	}

	/***
	 * Retorna a informacao de um parametro especifico de hospede
	 * 
	 * @param atributo - String
	 * @return nome/email/dataNascimento - String
	 * @throws HospedeException
	 */
	public String getInfoHospede(String atributo)throws SistemaException {
		
		atributo = atributo.toUpperCase();
		
		switch(atributo){
			case "NOME":
				return this.getNome();
			case "EMAIL":
				return this.getEmail();
			case "DATANASCIMENTO":
				return this.getDataNascimento();
			default:
				throw new HospedeException("Parametro invalido para hospedes");
		}
		
	}
	
	/***
	 * Verifica se um email e valido para uso
	 * 
	 * @param email - String
	 * @return boolean
	 */
	private boolean verificaEmailValido(String email){
		
		if(email.contains("@")){
			String[] emailSeparator = email.split("@");
			
			if(emailSeparator[0].length() == 0 || emailSeparator[1].length() == 0){
				return false;
			}else{
				return true;
			}
		}
		
		return false;
	}

	/***
	 * Retorna o nome do hospede
	 * @return nome - String
	 */
	public String getNome() {
		return nome;
	}

	/***
	 * Redefine o nome do hospede
	 * @param nome - String
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/***
	 * Retorna o email do hospede
	 * @return email - String
	 */
	public String getEmail() {
		return email;
	}

	/***
	 * Redefine o email do hospede
	 * @param email - String
	 * @throws HospedeException 
	 */
	public void setEmail(String email) throws HospedeException {
		
		if(verificaEmailValido(email)){
			this.email = email;	
		}else{
			throw new HospedeException("Email invalido");
		}
	}

	/***
	 * Retorna a data de nascimento do hospede
	 * @return data de nascimento - String
	 */
	public String getDataNascimento() {
		return dataNascimento;
	}

	/***
	 * Redefine a data de nascimento do hospede
	 * @param data de nascimento - String
	 */
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
}