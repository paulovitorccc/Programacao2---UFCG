/* 115211312 - Paulo Vítor Souto Dantas: LAB 3 - Turma 3 */
package supermercado_economiza;

public class MenuDeEntrada{

	private static final int CADASTRAR = 1;
	private static final int VENDER = 2;
	private static final int IMPRIMIR = 3;

	public static void main(String[] args){
		
		EconomizaP2 economiza = new EconomizaP2();
		Leitor leitor = new Leitor();

		int opcao;

		do{
			System.out.println("= = = = Bem-vindo(a) ao EconomizaP2 = = = =");
			System.out.println("Digite a opção desejada:");
			System.out.println("1 - Cadastrar um Produto");
			System.out.println("2 - Vender um Produto");
			System.out.println("3 - Imprimir Balanço");
			System.out.println("4 - Sair");
			System.out.println();
			System.out.print("Opção: ");

			opcao = leitor.leInteiro();
			leitor.leLinha();

			switch(opcao){
			case CADASTRAR:
				economiza.cadastrar();
				break;

			case VENDER:
				economiza.vender();
				break;

			case IMPRIMIR:
				economiza.imprimirBalanco();
				break;

			}
		}while(opcao != 4);

	}
}