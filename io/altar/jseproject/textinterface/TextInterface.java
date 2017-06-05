package io.altar.jseproject.textinterface;

import java.util.Collections;
import java.util.Scanner;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.repository.ProductRepository;
import io.altar.jseproject.repository.ShelfRepository;
import io.altar.jseproject.util.Utils;

public class TextInterface {
	private static ProductRepository productList = ProductRepository.getInstance();
	private static ShelfRepository shelfList = ShelfRepository.getInstance();

	public static void firstScreen() {
		String text = "Por favor selecione uma das seguintes opções:\n" + "1)	Listar produtos\n"
				+ "2)	Listar prateleiras\n" + "3)	Sair";
		System.out.println(text);
		try (Scanner scanner = new Scanner(System.in)) {
			Integer input = Utils.validate(1, 3, scanner, text);

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
			for (Integer ID : productList.keySet()) {
				text += productList.get(ID).toString();
			}
		} else {
			text += "Vazia!\n";
		}

		String finalText = text + "\nPor favor selecione uma das seguintes opções:\n" + "1)	Criar novo produto\n"
				+ "2)	Editar um produto existente\n" + "3)	Consultar o detalhe de um produto\n"
				+ "4)	Remover um produto\n" + "5)	Voltar ao ecrã anterior";
		System.out.println(finalText);
		try (Scanner scanner = new Scanner(System.in)) {
			Integer input = Utils.validate(1, 5, scanner,finalText);

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
		String text = "Lista de prateleiras:\n";
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
			Integer input = Utils.validate(1, 5, scanner, finalText);

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
	
	public static Integer validateID(Scanner scanner,String text){
		Integer ID = null;
		while (true) {
			ID = Utils.validate(0, Collections.max(productList.keySet()), scanner, text);
			if(ID==0){
				return ID;
			}
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
			ID = Utils.validate(1, Collections.max(shelfList.keySet()), scanner, text);
			if (shelfList.containsKey(ID)) {
				return ID;
			}else{
				System.out.println("Por favor escolha uma opção válida!");
			}
		}
	}
	
	public static void createProduct() {

		try (Scanner scanner = new Scanner(System.in)) {
			Integer[] shelfIDArray = null;
			if(!shelfList.keySet().isEmpty()){
				System.out.println(
						"Por favor indique a lista de prateleiras em que o produto está exposto (separado por virgulas):");
				String text = "Lista de prateleiras:\n";
				for (int ID : shelfList.keySet()) {
					text += shelfList.get(ID).toString();
				}
				System.out.println(text);
				shelfIDArray = Utils.validateIntArray(scanner, text);
			}			

			System.out.println("Por favor indique o valor unitário de desconto, em percentagem:");
			Integer discount = Integer.parseInt(Utils.validate(scanner, false, "integer"));

			System.out.println("Por favor indique o Imposto de Valor Acrescentado, em percentagem:");
			Integer valueAddedTax = Integer.parseInt(Utils.validate(scanner, false, "integer"));

			System.out.println("Por favor indique o Preço de Venda ao Público:");
			Double salePrice = Double.parseDouble(Utils.validate(scanner, false, "double"));

			new Product(shelfIDArray, discount, valueAddedTax, salePrice);

			listProductScreen();
		}
	}
	
	public static void alterProduct(String text) {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Por favor indique a ID do produto a alterar:");

			Integer productID = validateID(scanner, text);

			System.out.println("Este produto existe nas seguintes prateleiras: ");

			System.out.println("Este produto tem o seguinte desconto: " + ((Product)productList.get(productID)).getDiscount()
					+ "\nInsira o novo valor para este parâmetro (se não inserir nada o valor corrente será mantido):");
			String discount = Utils.validate(scanner, true, "integer");
			Integer newDiscount = ((Product)productList.get(productID)).getDiscount();
			if (discount != null) {
				newDiscount = Integer.parseInt(discount);
			}

			System.out.println("Este produto tem o seguinte imposto: " + ((Product)productList.get(productID)).getTax()
					+ "\nInsira o novo valor para este parâmetro (se não inserir nada o valor corrente será mantido):");
			String tax = Utils.validate(scanner, true, "integer");
			Integer newTax = ((Product)productList.get(productID)).getTax();
			if (tax != null) {
				newTax = Integer.parseInt(tax);
			}

			System.out.println("Este produto tem o seguinte preço: " + ((Product)productList.get(productID)).getSalePrice()
					+ "\nInsira o novo valor para este parâmetro (se não inserir nada o valor corrente será mantido):");
			String salePrice = Utils.validate(scanner, true, "double");
			Double newSalePrice = ((Product)productList.get(productID)).getSalePrice();
			if (salePrice != null) {
				newSalePrice = Double.parseDouble(salePrice);
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
					productList.removeElement(productID);
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

		try (Scanner scanner = new Scanner(System.in)) {

			System.out.println("Por favor indique a localização da prateleira:");
			Integer location = Integer.parseInt(Utils.validate(scanner, false, "integer"));

			System.out.println("Por favor indique a capacidade da prateleira:");
			Integer capacity = Integer.parseInt(Utils.validate(scanner, false, "integer"));
			
			Integer productID = null;
			if(!productList.keySet().isEmpty()){
				System.out.println("Por favor indique a ID do produto presente na prateleira (escolha de entre a lista de produtos):");
				String text = "Lista de produtos:\n";
				if (!productList.isEmpty()) {
					for (int ID : productList.keySet()) {
						text += productList.get(ID).toString();
					}
				} else {
					text += "Vazia!\n";
				}
				System.out.println(text);
				productID = validateID(scanner, text);
				if (productID == null) {
					productID = 0;
				}
			}			

			System.out.println("Por favor indique o Preço de Aluguer da prateleira:");
			Double locationRentalPrice = Double.parseDouble(Utils.validate(scanner, false, "double"));

			new Shelf(location, capacity, productID, locationRentalPrice);

			listShelfScreen();
		}
	}

	public static void alterShelf(String text){
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Por favor indique a ID da prateleira a alterar:");

			Integer shelfID = validateIDShelf(scanner, text);

			System.out.println("Esta prateleira contém os seguintes produtos: ");

			System.out.println("Esta prateleira tem a seguinte localização: " + ((Shelf)shelfList.get(shelfID)).getLocation()
					+ "\nInsira o novo valor para este parâmetro (se não inserir nada o valor corrente será mantido):");
			String location = Utils.validate(scanner, true, "integer");
			Integer newLocation = ((Shelf)shelfList.get(shelfID)).getLocation();
			if (location != null) {
				newLocation = Integer.parseInt(location);
			}

			System.out.println("Esta prateleira tem a seguinte capacidade: " + ((Shelf)shelfList.get(shelfID)).getCapacity()
					+ "\nInsira o novo valor para este parâmetro (se não inserir nada o valor corrente será mantido):");
			String capacity = Utils.validate(scanner, true, "integer");
			Integer newCapacity = ((Shelf)shelfList.get(shelfID)).getCapacity();
			if (capacity != null) {
				newCapacity = Integer.parseInt(capacity);
			}

			System.out.println("Esta prateleira tem o seguinte preço de aluguer: " + ((Shelf)shelfList.get(shelfID)).getLocationRentalPrice()
					+ "\nInsira o novo valor para este parâmetro (se não inserir nada o valor corrente será mantido):");
			String locationRentalPrice = Utils.validate(scanner, true, "double");
			Double newLocationRentalPrice = ((Shelf)shelfList.get(shelfID)).getLocationRentalPrice();
			if (locationRentalPrice != null) {
				newLocationRentalPrice = Double.parseDouble(locationRentalPrice);
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
					shelfList.removeElement(shelfID);
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
