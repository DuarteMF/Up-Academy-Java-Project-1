package io.altar.jseproject.textinterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import io.altar.jseproject.model.Product;

public class TextInterface {
	private static int productNumber = 0;
	 private static ArrayList<Product> productList = new ArrayList<Product>();

	public static void firstScreen() {
		System.out.println("Por favor selecione uma das seguintes opções:\n" + "1)	Listar produtos\n"
				+ "2)	Listar prateleiras\n" + "3)	Sair");
		try (Scanner scanner = new Scanner(System.in)) {
			int input = validateOption(1, 3, scanner);

			switch (input) {
			case 1:
				listProductScreen();
				break;

			case 2:
				listShelfScreen();
				break;

			case 3:
				leave();
				break;
			}
		}
	}

	public static void listProductScreen() {
			System.out.println("Lista de produtos:");
		if(!productList.isEmpty()){
			for(Product product: productList){
				System.out.println(product.toString());
			}
		}else{
			System.out.println("Vazia!");
		}
		
		
		System.out.println("Por favor selecione uma das seguintes opções:\n" + "1)	Criar novo produto\n"
				+ "2)	Editar um produto existente\n" + "3)	Consultar o detalhe de um produto\n"
				+ "4)	Remover um produto\n" + "5)	Voltar ao ecrã anterior");
		try (Scanner scanner = new Scanner(System.in)) {
			int input = validateOption(1,5,scanner);

			switch (input) {
			case 1:
				createProduct();
				break;

			case 2:
				alterProduct();
				break;

			case 3:
				break;

			case 4:
				break;

			case 5:
				firstScreen();
				break;
			}
		}
	}


	public static void listShelfScreen() {
		// List Shelves
		System.out.println("Por favor selecione uma das seguintes opções:\n" + "1)	Criar nova prateleira\n"
				+ "2)	Editar uma prateleira existente\n" + "3)	Consultar o detalhe de uma prateleira\n"
				+ "4)	Remover uma prateleira\n" + "5)	Voltar ao ecrã anterior");
		try (Scanner scanner = new Scanner(System.in)) {
			int input = validateOption(1,5,scanner);

			switch (input) {
			case 1:
				break;

			case 2:
				break;

			case 3:
				break;

			case 4:
				break;

			case 5:
				firstScreen();
				break;
			}
		}
	}


	public static void leave() {
		System.exit(0);
	}

	public static int validateOption(int min, int max, Scanner scanner) {
		int inputValue = 0;
		while (true) {
			if (scanner.hasNextInt()) {
				inputValue = scanner.nextInt();
				if ((inputValue >= min && inputValue <= max)) {
					return inputValue;					
				}
			}
			System.out.println("Por favor escolha uma opção válida!");
			scanner.next();
		}
	}
	
	public static void createProduct() {
		productNumber++;
		Product newProduct = new Product();
		newProduct.setId(productNumber);
		try (Scanner scanner = new Scanner(System.in)) {

			System.out.println("Por favor indique a lista de prateleiras em que o produto está exposto (separado por virgulas):");
			String shelfString = scanner.nextLine();
			String[] shelfArray = shelfString.split(",\\s*"); // using regex, \\s* means it will split the string by a comma followed by nothing or whitespace
			// I need to convert the above string array into an integer one;

			System.out.println("Por favor indique o valor unitário de desconto:");
			String discount = scanner.nextLine();
			newProduct.setDiscount(Float.parseFloat(discount));

			System.out.println("Por favor indique o Imposto de Valor Acrescentado, em percentagem:");
			String valueAddedTax = scanner.nextLine();
			newProduct.setTax(Float.parseFloat(valueAddedTax));

			System.out.println("Por favor indique o Preço de Venda ao Público:");
			String salePrice = scanner.nextLine();
			newProduct.setSalePrice(Float.parseFloat(salePrice));

			// Product newProduct = new Product(productNumber,
			// Float.parseFloat(discount), Float.parseFloat(valueAddedTax),
			// Float.parseFloat(salePrice));
			
			productList.add(newProduct);
			
			listProductScreen();
		}
	}
	
	public static void alterProduct(){
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Por favor indique a ID do produto a alterar:");
			
			int productIndex = -1;
			boolean idExists = false;
			while(!idExists){
				int productID = validateOption(1,productList.size(),scanner);
				// System.out.println(productID);
				for(Product product: productList){
					if(productID == product.getId()){
						productIndex = productList.indexOf(product);
						// System.out.println("ID exists");
						idExists = true;
						break;
					}
				}
			}
			
			Product currentProduct = productList.get(productIndex);
			
			System.out.println("Este produto existe nas seguintes prateleiras: ");
			
			System.out.println("Este produto tem o seguinte desconto: " + currentProduct.getDiscount() + "\nInsira o novo valor para este parâmetro (se não inserir nada o valor corrente será mantido):\n");
			System.out.println(1);
			String newDiscount = scanner.nextLine();
			System.out.println(2);
			if(!newDiscount.equals("")){
				currentProduct.setDiscount(Float.parseFloat(newDiscount));
			}
			
			
		}
		
	}
}
