/* 115211312 - Paulo Vitor Souto Dantas: LAB 06 - Turma 03 */
package loja;

import usuario.Noob;
import usuario.Usuario;
import usuario.Veterano;

public class FactoryUsuario {

	
	public Usuario criaNoob(String nomeUsuario, String usernameUsuario) throws Exception {
		return new Noob(nomeUsuario, usernameUsuario);		
	}
	
	
	public Usuario criaVeterano(String nomeUsuario, String usernameUsuario) throws Exception {
		return new Veterano(nomeUsuario, usernameUsuario);
	}
	
}
