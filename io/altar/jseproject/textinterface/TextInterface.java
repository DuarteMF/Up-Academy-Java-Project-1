package io.altar.jseproject.textinterface;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Scanner;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.repository.ProductRepository;
import io.altar.jseproject.repository.ShelfRepository;

public class TextInterface {
	private static int productNumber = 0;
	private static int shelfNumber = 0;
	private static LinkedHashMap<Integer, Product> productList = ProductRepository.getInstance();
	private static LinkedHashMap<Integer, Shelf> shelfList = ShelfRepository.getInstance();

	public static void firstScreen() {
		String text = "Por favor selecione uma das seguintes opções:\n" + "1)	Listar produtos\n"
				+ "2)	Listar prateleiras\n" + "3)	Sair";
		System.out.println(text);
		try (Scanner scanner = new Scanner(System.in)) {
			Integer input = validateOption(1, 3, scanner, text);

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
		String text = "Lista de produtos:\n";
		if (!productList.isEmpty()) {
			for (int ID : productList.keySet()) {
				text += productList.get(ID).toString();
			}
		} else {
			text += "Vazia!\n";
		}

		String finalText = text + "Por favor selecione uma das seguintes opções:\n" + "1)	Criar novo produto\n"
				+ "2)	Editar um produto existente\n" + "3)	Consultar o detalhe de um produto\n"
				+ "4)	Remover um produto\n" + "5)	Voltar ao ecrã anterior";
		System.out.println(finalText);
		try (Scanner scanner = new Scanner(System.in)) {
			Integer input = validateOption(1, 5, scanner,finalText);

			switch (input) {
			case 1:
				createProduct();
				break;

			case 2:
				alterProduct(text);
				break;

			case 3:
				seeProductDetails(text);
				break;

			case 4:
				removeProduct(text);
				break;

			case 5:
				firstScreen();
				break;
			}
		}
	}

	public static void listShelfScreen() {
		String text = "Lista de prateleiras:";
		if (!shelfList.isEmpty()) {
			for (int ID : shelfList.keySet()) {
				text += shelfList.get(ID).toString();
			}
		} else {
			text += "Vazia!\n";
		}
		String finalText = text + "Por favor selecione uma das seguintes opções:\n" + "1)	Criar nova prateleira\n"
				+ "2)	Editar uma prateleira existente\n" + "3)	Consultar o detalhe de uma prateleira\n"
				+ "4)	Remover uma prateleira\n" + "5)	Voltar ao ecrã anterior";
		
		System.out.println(finalText);
		try (Scanner scanner = new Scanner(System.in)) {
			Integer input = validateOption(1, 5, scanner, finalText);

			switch (input) {
			case 1:
				createShelf();
				break;

			case 2:
				alterShelf(text);
				break;

			case 3:
				seeShelfDetails(text);
				break;

			case 4:
				removeShelf(text);
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

	public static Integer validateOption(Integer min, Integer max, Scanner scanner, String text) {
		Integer inputValue = null;
		String stored = "";
		while (true) {
			stored = scanner.nextLine();
			try{
				inputValue = Integer.parseInt(stored);
				if ((inputValue >= min && inputValue <= max)) {
					return inputValue;
				}else{
					System.out.println("Por favor escolha uma opção válida!");
					System.out.println(text);
				}
			}catch(Exception e){
				System.out.println("Por favor escolha uma opção válida!");
				System.out.println(text);
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
	
	public static Integer validateID(Scanner scanner,String text){
		Integer ID = null;
		while (true) {
			ID = validateOption(1, Collections.max(productList.keySet()), scanner, text);
			if (productList.containsKey(ID)) {
				return ID;
			}else{
				System.out.println("Por favor escolha uma opção válida!");
			}
		}
	}
	
	public static Integer validateIDShelf(Scanner scanner, String text){
		Integer ID = null;
		while (true) {
			ID = validateOption(1, Collections.max(shelfList.keySet()), scanner, text);
			if (shelfList.containsKey(ID)) {
				return ID;
			}else{
				System.out.println("Por favor escolha uma opção válida!");
			}
		}
	}

	public static void createProduct() {
		productNumber++;

		try (Scanner scanner = new Scanner(System.in)) {

			System.out.println(
					"Por favor indique a lista de prateleiras em que o produto está exposto (separado por virgulas):");
			String shelfString = scanner.nextLine();
			String[] shelfArray = shelfString.split(",\\s*"); // using regex, \\s* means it will split the string by a comma followed by nothing or whitespace
			// I need to convert the above string array into an integer one;

			System.out.println("Por favor indique o valor unitário de desconto, em percentagem:");
			Integer discount = validateInt(scanner, false);

			System.out.println("Por favor indique o Imposto de Valor Acrescentado, em percentagem:");
			Integer valueAddedTax = validateInt(scanner, false);

			System.out.println("Por favor indique o Preço de Venda ao Público:");
			Double salePrice = validateDouble(scanner, false);

			new Product(productNumber, discount, valueAddedTax, salePrice);

			listProductScreen();
		}
	}
	
	public static void alterProduct(String text) {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Por favor indique a ID do produto a alterar:");

			Integer productID = validateID(scanner, text);

			System.out.println("Este produto existe nas seguintes prateleiras: ");

			System.out.println("Este produto tem o seguinte desconto: " + productList.get(productID).getDiscount()
					+ "\nInsira o novo valor para este parâmetro (se não inserir nada o valor corrente será mantido):");
			Integer newDiscount = validateInt(scanner, true);
			if (newDiscount == null) {
				newDiscount = productList.get(productID).getDiscount();
			}

			System.out.println("Este produto tem o seguinte imposto: " + productList.get(productID).getTax()
					+ "\nInsira o novo valor para este parâmetro (se não inserir nada o valor corrente será mantido):");
			Integer newTax = validateInt(scanner, true);
			if (newTax == null) {
				newTax = productList.get(productID).getTax();
			}

			System.out.println("Este produto tem o seguinte preço: " + productList.get(productID).getSalePrice()
					+ "\nInsira o novo valor para este parâmetro (se não inserir nada o valor corrente será mantido):");
			Double newSalePrice = validateDouble(scanner, true);
			if (newSalePrice == null) {
				newSalePrice = productList.get(productID).getSalePrice();
			}

			ProductRepository.alterElement(productID, newDiscount, newTax, newSalePrice);

			listProductScreen();
		}
	}

	public static void seeProductDetails(String text) {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Por favor indique a ID do produto a consultar:");

			Integer productID = validateID(scanner, text);

			System.out.println(productList.get(productID).toString());
			System.out.println("Por favor prima ENTER para voltar ao ecrã dos produtos");
			scanner.nextLine();
			listProductScreen();
		}
	}

	public static void removeProduct(String text) {
		try (Scanner scanner = new Scanner(System.in)) {

			System.out.println("Por favor indique a ID do produto a remover:");
			Integer productID = validateID(scanner, text);
			
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
	
	public static void createShelf(){
		shelfNumber++;

		try (Scanner scanner = new Scanner(System.in)) {

			System.out.println("Por favor indique a localização da prateleira:");
			Integer location = validateInt(scanner, false);

			System.out.println("Por favor indique a capacidade da prateleira:");
			Integer capacity = validateInt(scanner, false);

			System.out.println("Por favor indique o Preço de Aluguer da prateleira:");
			Double locationRentalPrice = validateDouble(scanner, false);

			new Shelf(shelfNumber, location, capacity, locationRentalPrice);

			listShelfScreen();
		}
	}

	public static void alterShelf(String text){
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Por favor indique a ID da prateleira a alterar:");

			Integer shelfID = validateIDShelf(scanner, text);

			System.out.println("Esta prateleira contém os seguintes produtos: ");

			System.out.println("Esta prateleira tem a seguinte localização: " + shelfList.get(shelfID).getLocation()
					+ "\nInsira o novo valor para este parâmetro (se não inserir nada o valor corrente será mantido):");
			Integer newLocation = validateInt(scanner, true);
			if (newLocation == null) {
				newLocation = shelfList.get(shelfID).getLocation();
			}

			System.out.println("Esta prateleira tem a seguinte capacidade: " + shelfList.get(shelfID).getCapacity()
					+ "\nInsira o novo valor para este parâmetro (se não inserir nada o valor corrente será mantido):");
			Integer newCapacity = validateInt(scanner, true);
			if (newCapacity == null) {
				newCapacity = shelfList.get(shelfID).getCapacity();
			}

			System.out.println("Esta prateleira tem o seguinte preço de aluguer: " + shelfList.get(shelfID).getLocationRentalPrice()
					+ "\nInsira o novo valor para este parâmetro (se não inserir nada o valor corrente será mantido):");
			Double newLocationRentalPrice = validateDouble(scanner, true);
			if (newLocationRentalPrice == null) {
				newLocationRentalPrice = shelfList.get(shelfID).getLocationRentalPrice();
			}

			ShelfRepository.alterElement(shelfID, newLocation, newCapacity, newLocationRentalPrice);

			listShelfScreen();
		}
	}

	public static void seeShelfDetails(String text){
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Por favor indique a ID da prateleira a consultar:");

			Integer shelfID = validateIDShelf(scanner, text);

			System.out.println(shelfList.get(shelfID).toString());
			System.out.println("Por favor prima ENTER para voltar ao ecrã das prateleiras");
			scanner.nextLine();
			listShelfScreen();
		}
	}

	public static void removeShelf(String text){
		try (Scanner scanner = new Scanner(System.in)) {

			System.out.println("Por favor indique a ID da prateleira a remover:");
			Integer shelfID = validateIDShelf(scanner, text);
			
			System.out.println("Este é a prateleira que escolheu:");
			System.out.println(shelfList.get(shelfID).toString());
			System.out.println("De certeza que a quer apagar? (prima s para Sim e n para Não)");
			while(true){				
				String response = scanner.nextLine();
				if(response.equals("s")){
					ShelfRepository.removeElement(shelfID);
					listShelfScreen();
				}else if(response.equals("n")){
					listShelfScreen();
				}else{
					System.out.println("Por favor prima s ou n");
				}
			}
		}
	}
}
