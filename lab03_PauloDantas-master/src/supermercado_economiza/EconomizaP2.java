/* 115211312 - Paulo Vítor Souto Dantas: LAB 3 - Turma 3 */
package supermercado_economiza;

public class EconomizaP2 {

	private double totalVendido;

	Leitor leitor = new Leitor();
	Estoque estoque = new Estoque();

	public void cadastrar(){

		String opcao;

		System.out.println("= = = = Cadastro de Produtos = = = =");

		do{

			System.out.print("Digite o nome do produto: ");
			String nomeProdutoCadastrado = leitor.leLinha();

			System.out.print("Digite o preço unitário do produto: ");
			double precoProdutoCadastrado= leitor.leDouble();
			leitor.leLinha();

			System.out.print("Digite o tipo do produto: ");
			String tipoProdutoCadastrado = leitor.leString();
			leitor.leLinha();

			System.out.print("Digite a quantidade a adicionar no estoque: ");
			int qtdProdutoCadastrado = leitor.leInteiro(); 
			leitor.leLinha();

			if(estoque.buscarProdutoPorNome(nomeProdutoCadastrado) == null){

				Produto cadastrado = new Produto(nomeProdutoCadastrado, precoProdutoCadastrado, tipoProdutoCadastrado, qtdProdutoCadastrado);

				estoque.adicionarProduto(cadastrado);

				System.out.println(qtdProdutoCadastrado + " \"" + cadastrado.getNome() + "\"" + " cadastrado(s) com sucesso.");
				System.out.println("");
			
			}else{
				
				Produto novoProdutoObjeto = estoque.buscarProdutoPorNome(nomeProdutoCadastrado);
				novoProdutoObjeto.setPreco(precoProdutoCadastrado);
				novoProdutoObjeto.setQtdAumentar(qtdProdutoCadastrado);
				
				System.out.println(qtdProdutoCadastrado + " \"" + novoProdutoObjeto.getNome() + "\"" + " cadastrado(s) com sucesso.");
				System.out.println("");				
				
			}

			System.out.print("Deseja cadastrar outro produto? ");
			opcao = leitor.leString();
			System.out.println("");
			leitor.leLinha();


		}while(opcao.equalsIgnoreCase("sim"));
	}


	public void vender(){

		String opcaoVender;

		System.out.println("= = = = Venda de Produtos = = = =");

		do{

			System.out.print("Digite o nome do produto: ");
			String novoProduto = leitor.leLinha();
			Produto novoProdutoObjeto = estoque.buscarProdutoPorNome(novoProduto);

			if(novoProdutoObjeto == null){
				System.out.println("==> " + novoProduto + " nao cadastrado(a) no sistema.");

			}else{

				System.out.println("==> " + novoProdutoObjeto.getNome() + " ("+ novoProdutoObjeto.getTipo() +"). R$" + novoProdutoObjeto.getPreco());

				System.out.print("Digite a quantidade que deseja vender: ");
				int qtdAVender = leitor.leInteiro();
				leitor.leLinha();

				if (novoProdutoObjeto.getQtd() < qtdAVender){
					System.out.println("Nao e possivel vender pois não ha " + novoProdutoObjeto.getNome() + " suficiente.");

				}else{
					novoProdutoObjeto.setQtdDiminuir(qtdAVender);

					double vendaAtual = novoProdutoObjeto.getPreco() * qtdAVender;
					totalVendido += vendaAtual;

					System.out.printf("==> Total arrecadado: R$ %.2f" , (vendaAtual));
					System.out.println();
					System.out.println();

				}
			}

			System.out.println("Deseja vender outro produto? ");
			opcaoVender = leitor.leString();
			leitor.leLinha();

		}while(opcaoVender.equalsIgnoreCase("sim"));

	}


	public void imprimirBalanco(){

		System.out.println("= = = = Impressao de Balanco = = = =");

		System.out.println(estoque.toString());		

		System.out.println("");
		System.out.printf("Total arrecadado em vendas: R$ %.2f\n" , totalVendido);
		System.out.println("Total que pode ser arrecadado: R$ " + estoque.precoTotalDoMercado());
	}

}