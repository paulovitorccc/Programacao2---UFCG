/* 115211312 - Paulo Vítor Souto Dantas: LAB 7 - Turma 03 */
package usuario;

import excecoes.StringInvalidaException;

public class FactoryUsuario {

	public Usuario criaUsuario(String nomeUsuario, String usernameUsuario, String tipo) throws StringInvalidaException {
		return new Usuario(nomeUsuario, usernameUsuario, tipo);
	}


}