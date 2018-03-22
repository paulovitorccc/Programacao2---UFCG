package administradores;

import exceptions.SistemaException;
import hospedes.Hospede;

public interface AdministradorIF {

	//TestCase 1
	public int cadastroHospede(String nome, String email, String dataNascimento) throws SistemaException;
	public void removeHospede(String email) throws Exception;
	public String getInfoHospede(int id, String atributo) throws SistemaException;
	public void atualizaCadastro(int id, String atributo, String valor) throws SistemaException;
	
	//TestCase 2
	public void realizaCheckin(String email, int dias, String quarto,
			String tipoQuarto) throws SistemaException;
	public String getInfoHospedagem(String email, String atributo) throws SistemaException;
	
	//TestCase 3
	public double realizaCheckout(String email, String quarto) throws SistemaException;
	public String consultaTranslacoes(String atributo) throws SistemaException;
	public String consultaTranslacoes(String atributo, int indice) throws SistemaException;	
	
	//Utilitarios
	public void testString(String stringTest, String erroMsg)throws SistemaException;
	public Hospede buscaHospedeEmail(String email)throws SistemaException;
	public Hospede buscaHospedeNome(String nomel)throws SistemaException;
	public Hospede buscaHospedeDataNascimento(String dataNascimento)throws SistemaException;
	public Hospede consultaHospede(String atributo)throws SistemaException;
}