package io.altar.jseproject.textinterface;

import java.util.LinkedHashMap;
import java.util.Scanner;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.repository.ProductRepository;

public class TextInterface {
	private static int productNumber = 0;
	private static LinkedHashMap<Integer,Product> productList = ProductRepository.getInstance();
	

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
			for(int ID: productList.keySet()){
				System.out.println(productList.get(ID).toString());
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
				seeProductDetails();
				break;

			case 4:
				removeProduct();
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
		return;
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
			scanner.nextLine();
		}
	}
	
	public static int validateInt(Scanner scanner, boolean noValue) {
		int inputValue = 0;
		String stored = "placeholder";
		while (true) {
			if (scanner.hasNextInt()) {
				inputValue = scanner.nextInt();
				return inputValue;
			}else if(noValue && stored.isEmpty()){
				inputValue = -999;
				return inputValue;
			}
			System.out.println("Por favor escolha uma opção válida!");
			stored = scanner.nextLine();
		}
	}
	
	public static double validateDouble(Scanner scanner, boolean noValue) {
		double inputValue = 0;
		String stored = "placeholder";
		while (true) {
			if (scanner.hasNextDouble()) {
				inputValue = scanner.nextDouble();
				return inputValue;
			}else if(noValue && stored.isEmpty()){
				inputValue = -999;
				return inputValue;
			}
			System.out.println("Por favor escolha uma opção válida!");
			stored = scanner.nextLine();
		}
	}
	
	public static void createProduct() {
		productNumber++;
		
		try (Scanner scanner = new Scanner(System.in)) {

			System.out.println("Por favor indique a lista de prateleiras em que o produto está exposto (separado por virgulas):");
			String shelfString = scanner.nextLine();
			String[] shelfArray = shelfString.split(",\\s*"); // using regex, \\s* means it will split the string by a comma followed by nothing or whitespace
			// I need to convert the above string array into an integer one;

			System.out.println("Por favor indique o valor unitário de desconto:");
			double discount = validateDouble(scanner,false);

			System.out.println("Por favor indique o Imposto de Valor Acrescentado, em percentagem:");
			int valueAddedTax = validateInt(scanner,false);

			System.out.println("Por favor indique o Preço de Venda ao Público:");
			double salePrice = validateDouble(scanner,false);
			
			new Product(productNumber,discount,valueAddedTax,salePrice);
			
			listProductScreen();
		}
	}
	
	public static void alterProduct(){
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Por favor indique a ID do produto a alterar:");
			
			int productID = -1;
			boolean idExists = false;
			while(!idExists){
				productID = validateOption(1,productList.size(),scanner);
				if(productList.containsKey(productID)){
					idExists = true;
				}
			}
			
			System.out.println("Este produto existe nas seguintes prateleiras: ");
			
			System.out.println("Este produto tem o seguinte desconto: " + productList.get(productID).getDiscount() + "\nInsira o novo valor para este parâmetro (se não inserir nada o valor corrente será mantido):\n");
			double newDiscount = validateDouble(scanner,true);
			if(newDiscount==-999){
				newDiscount = productList.get(productID).getDiscount();
			}
						
			System.out.println("Este produto tem o seguinte imposto: " + productList.get(productID).getTax() + "\nInsira o novo valor para este parâmetro (se não inserir nada o valor corrente será mantido):\n");
			int newTax = validateInt(scanner,true);
			if(newTax==-999){
				newTax = productList.get(productID).getTax();
			}
			
			System.out.println("Este produto tem o seguinte preço: " + productList.get(productID).getSalePrice() + "\nInsira o novo valor para este parâmetro (se não inserir nada o valor corrente será mantido):\n");
			double newSalePrice = validateDouble(scanner,true);
			if(newSalePrice==-999){
				newSalePrice = productList.get(productID).getSalePrice();
			}
			
			ProductRepository.alterElement(productID, newDiscount, newTax, newSalePrice);
			
			listProductScreen();			
		}		
	}
	
	public static void seeProductDetails(){
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Por favor indique a ID do produto a consultar:");
			
			int productID = -1;
			boolean idExists = false;
			while(!idExists){
				productID = validateOption(1,productList.size(),scanner);
				if(productList.containsKey(productID)){
					idExists = true;
				}
			}
			
			System.out.println(productList.get(productID).toString());
			System.out.println("Por favor prima ENTER para voltar ao ecrã dos produtos");
			scanner.next();
			listProductScreen();
		}
	}
	
	public static void removeProduct(){
		
	}
}
