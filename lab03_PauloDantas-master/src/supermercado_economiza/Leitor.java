/* 115211312 - Paulo Vítor Souto Dantas: LAB 3 - Turma 3 */
package supermercado_economiza;

import java.util.Scanner;

public class Leitor {
	Scanner sc = new Scanner(System.in);

	public int leInteiro(){
		return sc.nextInt();	
	}

	public String leString(){
		return sc.next();	
	}

	public double leDouble(){
		return sc.nextDouble();	
	}

	public String leLinha(){
		return sc.nextLine();	
	}

}