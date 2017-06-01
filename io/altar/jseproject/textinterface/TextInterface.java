package io.altar.jseproject.textinterface;

import java.util.LinkedHashMap;
import java.util.Scanner;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.repository.ProductRepository;

public class TextInterface {
	private static int productNumber = 0;
	private static LinkedHashMap<Integer, Product> productList = ProductRepository.getInstance();

	public static void firstScreen() {
		System.out.println("Por favor selecione uma das seguintes opções:\n" + "1)	Listar produtos\n"
				+ "2)	Listar prateleiras\n" + "3)	Sair");
		try (Scanner scanner = new Scanner(System.in)) {
			Integer input = validateOption(1, 3, scanner);

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
		if (!productList.isEmpty()) {
			for (int ID : productList.keySet()) {
				System.out.println(productList.get(ID).toString());
			}
		} else {
			System.out.println("Vazia!");
		}

		System.out.println("Por favor selecione uma das seguintes opções:\n" + "1)	Criar novo produto\n"
				+ "2)	Editar um produto existente\n" + "3)	Consultar o detalhe de um produto\n"
				+ "4)	Remover um produto\n" + "5)	Voltar ao ecrã anterior");
		try (Scanner scanner = new Scanner(System.in)) {
			Integer input = validateOption(1, 6, scanner);

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
			Integer input = validateOption(1, 5, scanner);

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

	public static Integer validateOption(Integer min, Integer max, Scanner scanner) {
		Integer inputValue = null;
		String stored = "";
		while (true) {
			stored = scanner.nextLine();
			try{
				inputValue = Integer.parseInt(stored);
				if ((inputValue >= min && inputValue <= max)) {
					return inputValue;
				}else{
					System.out.println(inputValue >= min);
					System.out.println(max);
					System.out.println(inputValue <= max);
					System.out.println("Por favor escolha uma opção válida!");
				}
			}catch(Exception e){
				System.out.println("Por favor escolha uma opção válida!");
			}
		}
	}

	public static Integer validateInt(Scanner scanner, boolean emptyAllowed) {	
		Integer inputValue = null;
		String stored = "";
		while (true) {
			stored = scanner.nextLine();
			if (stored.isEmpty()) {
				if(emptyAllowed){
					return null;
				} 	
			}			
			else {
				try{
					inputValue = Integer.parseInt(stored);
					return inputValue;
				}
				catch(Exception e){
					System.out.println("Por favor escolha uma opção válida!");
				}
			}

		}
	}

	public static Double validateDouble(Scanner scanner, boolean emptyAllowed) {
		Double inputValue = null;
		String stored = "";
		while (true) {
			stored = scanner.nextLine();
			if (stored.isEmpty()) {
				if(emptyAllowed){
					return null;
				} 	
			}					
			else {
				try{
					inputValue = Double.parseDouble(stored);
					return inputValue;
				}
				catch(Exception e){
					System.out.println("Por favor escolha uma opção válida!");
				}
			}

		}
	}
	
	public static Integer validateID(Scanner scanner){
		Integer productID = null;
		while (true) {
			productID = validateOption(1, productList.size(), scanner);
			if (productList.containsKey(productID)) {
				return productID;
			}
		}
	}
//	public static boolean validateYesNo(Scanner scanner){
//		while(true){
//			String stored = scanner.nextLine();
//			if(stored.equals("s")){
//				return true;
//			}else if(stored.equals("n")){
//				return false;
//			}else{
//				System.out.println("Por favor prima s ou n");
//			}
//		}
//		
//	}

	public static void createProduct() {
		productNumber++;

		try (Scanner scanner = new Scanner(System.in)) {

			System.out.println(
					"Por favor indique a lista de prateleiras em que o produto está exposto (separado por virgulas):");
			String shelfString = scanner.nextLine();
			String[] shelfArray = shelfString.split(",\\s*"); // using regex, \\s* means it will split the string by a comma followed by nothing or whitespace
			// I need to convert the above string array into an integer one;

			System.out.println("Por favor indique o valor unitário de desconto,em percentagem:");
			Integer discount = validateInt(scanner, false);

			System.out.println("Por favor indique o Imposto de Valor Acrescentado, em percentagem:");
			Integer valueAddedTax = validateInt(scanner, false);

			System.out.println("Por favor indique o Preço de Venda ao Público:");
			Double salePrice = validateDouble(scanner, false);

			new Product(productNumber, discount, valueAddedTax, salePrice);

			listProductScreen();
		}
	}
	
	public static void alterProduct() {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Por favor indique a ID do produto a alterar:");

			Integer productID = validateID(scanner);

			System.out.println("Este produto existe nas seguintes prateleiras: ");

			System.out.println("Este produto tem o seguinte desconto: " + productList.get(productID).getDiscount()
					+ "\nInsira o novo valor para este parâmetro (se não inserir nada o valor corrente será mantido):\n");
			Integer newDiscount = validateInt(scanner, true);
			if (newDiscount == null) {
				newDiscount = productList.get(productID).getDiscount();
			}

			System.out.println("Este produto tem o seguinte imposto: " + productList.get(productID).getTax()
					+ "\nInsira o novo valor para este parâmetro (se não inserir nada o valor corrente será mantido):\n");
			Integer newTax = validateInt(scanner, true);
			if (newTax == null) {
				newTax = productList.get(productID).getTax();
			}

			System.out.println("Este produto tem o seguinte preço: " + productList.get(productID).getSalePrice()
					+ "\nInsira o novo valor para este parâmetro (se não inserir nada o valor corrente será mantido):\n");
			Double newSalePrice = validateDouble(scanner, true);
			if (newSalePrice == null) {
				newSalePrice = productList.get(productID).getSalePrice();
			}

			ProductRepository.alterElement(productID, newDiscount, newTax, newSalePrice);

			listProductScreen();
		}
	}

	public static void seeProductDetails() {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Por favor indique a ID do produto a consultar:");

			Integer productID = validateID(scanner);

			System.out.println(productList.get(productID).toString());
			System.out.println("Por favor prima ENTER para voltar ao ecrã dos produtos");
			scanner.nextLine();
			listProductScreen();
		}
	}

	public static void removeProduct() {
		try (Scanner scanner = new Scanner(System.in)) {
//			boolean remove = false;
//			Integer productID = null;
//			while(!remove){
//				System.out.println("Por favor indique a ID do produto a remover:");
//				productID = validateID(scanner);
//				
//				System.out.println("Este é o produto que escolheu:");
//				System.out.println(productList.get(productID).toString());
//				System.out.println("De certeza que o quer apagar? (prima s para Sim e n para Não)");
//				
//				remove = validateYesNo(scanner);
//			}
//			
//			ProductRepository.removeElement(productID);
//			listProductScreen();
			
			Integer productID = validateID(scanner);
			
			System.out.println("Este é o produto que escolheu:");
			System.out.println(productList.get(productID).toString());
			System.out.println("De certeza que o quer apagar? (prima s para Sim e n para Não)");
			while(true){				
				String response = scanner.nextLine();
				if(response.equals("s")){
					ProductRepository.removeElement(productID);
					listProductScreen();
				}else if(response.equals("n")){
					listProductScreen();
				}else{
					System.out.println("Por favor prima s ou n");
				}
			}
		}
	}
}
