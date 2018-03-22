package hospedes;

import exceptions.HospedeException;
import exceptions.SistemaException;

public interface HospedeIF {

	public String getInfoHospede(String atributo)throws SistemaException;
	public String getNome();
	public String getEmail();
	public String getDataNascimento();
	
	public void setNome(String nome);
	public void setEmail(String email) throws HospedeException;
	public void setDataNascimento(String dataNascimento);
}
