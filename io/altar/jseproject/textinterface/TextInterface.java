package io.altar.jseproject.textinterface;

import java.util.Scanner;
import io.altar.jseproject.model.Product;

public class TextInterface {
	private static int productNumber = 0;
//	private static Product[] productListArray;
	
	public static void firstScreen(){		
		boolean starting = true;
		try(Scanner scanner = new Scanner(System.in)){
			boolean valid = false;
			while(!valid){
				if(starting){
					System.out.println("Por favor selecione uma das seguintes opções:\n"
							+ "1)	Listar produtos\n"
							+ "2)	Listar prateleiras\n"
							+ "3)	Sair");
					starting = false;
				}
				String input = scanner.next();				
				switch(input){
					case "1":
						valid = true;
						listProductScreen();
						break;
						
					case "2":
						valid = true;
						listShelfScreen();
						break;
						
					case "3":
						valid = true;
						leave();
						break;
					default:
						System.out.println("Por favor escolha entre uma das 3 opções!");							
				}
			}			
		}
	}
	
	public static void listProductScreen(){
		//List Products	
		boolean starting = true;
		try(Scanner scanner = new Scanner(System.in)){
			boolean valid = false;
			while(!valid){
				if(starting){
					System.out.println("Por favor selecione uma das seguintes opções:\n"
							+ "1)	Criar novo produto\n"
							+ "2)	Editar um produto existente\n"
							+ "3)	Consultar o detalhe de um produto\n"
							+ "4)	Remover um produto\n"
							+ "5)	Voltar ao ecrã anterior");
					starting = false;
				}
				String input = scanner.next();
				switch(input){
					case "1":
						valid = true;
						createProduct();
						break;
						
					case "2":
						valid = true;
						break;
						
					case "3":
						valid = true;
						break;
						
					case "4":
						valid = true;
						break;
				
					case "5":
						valid = true;
						firstScreen();
						break;
					default:
						System.out.println("Por favor escolha entre uma das 5 opções!");
				}
			}
		}
	}
	
	public static void listShelfScreen(){
		//List Shelves
		boolean starting = true;
		try(Scanner scanner = new Scanner(System.in)){
			boolean valid = false;
			while(!valid){
				if(starting){
					System.out.println("Por favor selecione uma das seguintes opções:\n"
							+ "1)	Criar nova prateleira\n"
							+ "2)	Editar uma prateleira existente\n"
							+ "3)	Consultar o detalhe de uma prateleira\n"
							+ "4)	Remover uma prateleira\n"
							+ "5)	Voltar ao ecrã anterior");
					starting = false;
				}
				String input = scanner.next();
				switch(input){
					case "1":
						valid = true;
						break;
						
					case "2":
						valid = true;
						break;
						
					case "3":
						valid = true;
						break;
						
					case "4":
						valid = true;
						break;
				
					case "5":
						valid = true;
						firstScreen();
						break;
					default:
						System.out.println("Por favor escolha entre uma das 5 opções!");
				}
			}
		}
	}
	
	public static void leave(){
		
	}
	
	public static void createProduct(){
		productNumber++;
		try(Scanner scanner = new Scanner(System.in)){
			System.out.println("Por favor indique a lista de prateleiras em que o produto está exposto (separado por virgulas):\n");
			String shelfString = scanner.nextLine();
			String[] shelfArray = shelfString.split(",\\s*"); //using regex, ,\\s* means it will split the string by a comma followed by 0 or more whitespace
			System.out.println("Por favor indique o valor unitário de desconto:\n");
			String discount = scanner.nextLine();
			System.out.println("Por favor indique o Imposto de Valor Acrescentado, em percentagem:\n");
			String valueAddedTax = scanner.nextLine();
			System.out.println("Por favor indique o Preço de Venda ao Público:\n");
			String salePrice = scanner.nextLine();
			
			Product newProduct = new Product(productNumber, Float.parseFloat(discount), Float.parseFloat(valueAddedTax), Float.parseFloat(salePrice));
		}
//		productListArray[productNumber-1] = newProduct;
	}
	
	public static void chooseAnOption(int min,int max, String[] functions, String defaultMessage){
		
	}
}
