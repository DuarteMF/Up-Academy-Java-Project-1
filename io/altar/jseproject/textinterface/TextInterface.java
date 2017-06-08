package io.altar.jseproject.textinterface;

import java.util.Arrays;
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
			Integer input = Utils.validate(1, 5, scanner, finalText);

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

	public static void createProduct() {
		boolean correctVAT = false;
		try (Scanner scanner = new Scanner(System.in)) {
			Integer[] shelfIDArray = null;
			Integer[] emptyShelves = Utils.emptyShelves(shelfList);
			if (!shelfList.keySet().isEmpty() && !(emptyShelves.length == 0)) {
				System.out.println(shelfList.getList("shelf"));
				System.out.println(String.format("As seguintes prateleiras estão vazias: %s", Arrays.toString(emptyShelves)));
				System.out.println(
						"Por favor indique a lista de prateleiras em que o produto está exposto, separado por virgulas (pode deixar em branco):");
				shelfIDArray = Utils.validateIntArray(scanner, shelfList.getList("shelf"));
			}
			
			System.out.println("Por favor indique o nome do produto:");
			String name = scanner.nextLine();

			System.out.println("Por favor indique o valor unitário de desconto, em percentagem:");
			Integer discount = Integer.parseInt(Utils.validate(scanner, false, "integer"));

			Integer valueAddedTax = null;
			while (!correctVAT) {
				System.out.println("Por favor indique o IVA, em percentagem (6, 13 ou 23%):");
				valueAddedTax = Integer.parseInt(Utils.validate(scanner, false, "integer"));
				switch (valueAddedTax) {
					case 6:
					case 13:
					case 23:
						correctVAT = true;
						break;
				}
			}

			System.out.println("Por favor indique o Preço de Venda ao Público:");
			Double salePrice = Double.parseDouble(Utils.validate(scanner, false, "double"));

			new Product(shelfIDArray, name, discount, valueAddedTax, salePrice);

			listProductScreen();
		}
	}

	public static void alterProduct(String text) {
		if (!productList.isEmpty()) {
			boolean correctVAT = false;
			try (Scanner scanner = new Scanner(System.in)) {
				System.out.println("Por favor indique a ID do produto a alterar:");

				Integer productID = Utils.validate(scanner, text, "product");
				// Integer productID = Utils.validate(scanner, text,
				// productList);

				if (productID == 0) {
					listProductScreen();
				} else {
					Integer[] shelfIDArray = null;
					Integer[] emptyShelves = Utils.emptyShelves(shelfList);
					if (!shelfList.isEmpty() && !(emptyShelves.length == 0)) {
						System.out.println(shelfList.getList("shelf"));
						System.out.println(String.format("As seguintes prateleiras estão vazias: %s", Arrays.toString(emptyShelves)));
						System.out.println(String.format("Este produto existe nas seguintes prateleiras: %s\nInsira o novo valor para este parâmetro (para manter o valor corrente terá de o inserir de novo, se nào inserir nada, este será apagado):", Arrays.toString(((Product) productList.get(productID)).getShelfIdLocation())));
						shelfIDArray = Utils.validateIntArray(scanner, shelfList.getList("shelf"));
//						if(shelfIDArray==null){
//							shelfIDArray = ((Product) productList.get(productID)).getShelfIdLocation();
//						}
					}
					
					System.out.println(String.format(
							"Este produto tem o seguinte nome: %s\nInsira o novo nome (se não inserir será mantido o nome inicial):",
							((Product) productList.get(productID)).getName()));
					String name = scanner.nextLine();
					if (name.isEmpty()) {
						name = ((Product) productList.get(productID)).getName();
					}

					System.out.println(String.format(
							"Este produto tem o seguinte desconto: %d%%\nInsira o novo valor para este parâmetro (se não inserir nada o valor corrente será mantido):",
							((Product) productList.get(productID)).getDiscount()));
					String discount = Utils.validate(scanner, true, "integer");
					Integer newDiscount = ((Product) productList.get(productID)).getDiscount();
					if (discount != null) {
						newDiscount = Integer.parseInt(discount);
					}

					Integer newTax = null;
					while (!correctVAT) {
						System.out.println(String.format(
								"Este produto tem o seguinte imposto: %d%%\nInsira o novo valor para este parâmetro (se não inserir nada o valor corrente será mantido):",
								((Product) productList.get(productID)).getTax()));
						String tax = Utils.validate(scanner, true, "integer");
						newTax = ((Product) productList.get(productID)).getTax();
						if (tax != null) {
							newTax = Integer.parseInt(tax);
							switch (newTax) {
							case 6:
							case 13:
							case 23:
								correctVAT = true;
								break;
							}
						} else {
							correctVAT = true;
						}
					}

					System.out.println(String.format(
							"Este produto tem o seguinte preço: %.2f€\nInsira o novo valor para este parâmetro (se não inserir nada o valor corrente será mantido):",
							((Product) productList.get(productID)).getSalePrice()));
					String salePrice = Utils.validate(scanner, true, "double");
					Double newSalePrice = ((Product) productList.get(productID)).getSalePrice();
					if (salePrice != null) {
						newSalePrice = Double.parseDouble(salePrice);
					}

					ProductRepository.alterElement(productID, shelfIDArray, name, newDiscount, newTax, newSalePrice);

					listProductScreen();
				}
			}
		} else {
			System.out.println("A lista de produtos está vazia, não há produtos a editar");
			listProductScreen();
		}
	}

	public static void seeProductDetails(String text) {
		if (!productList.isEmpty()) {
			try (Scanner scanner = new Scanner(System.in)) {
				System.out.println("Por favor indique a ID do produto a consultar:");

				Integer productID = Utils.validate(scanner, text, "product");
				if (productID == 0) {
					listProductScreen();
				} else {
					productList.displayElement(productID);
					System.out.println("Por favor prima ENTER para voltar ao ecrã dos produtos");
					scanner.nextLine();
					listProductScreen();
				}
			}
		} else {
			System.out.println("A lista de produtos está vazia, não há produtos para consultar");
			listProductScreen();
		}
	}

	public static void removeProduct(String text) {
		if (!productList.isEmpty()) {
			try (Scanner scanner = new Scanner(System.in)) {

				System.out.println("Por favor indique a ID do produto a remover:");
				Integer productID = Utils.validate(scanner, text, "product");
				if (productID == 0) {
					listProductScreen();
				} else {
					System.out.println("Este é o produto que escolheu:");
					productList.displayElement(productID);
					System.out.println("De certeza que o quer apagar? (prima s para Sim e n para Não)");
					while (true) {
						String response = scanner.nextLine();
						if (response.equals("s")) {
							productList.removeElement(productID);
							listProductScreen();
						} else if (response.equals("n")) {
							listProductScreen();
						} else {
							System.out.println("Por favor prima s ou n");
						}
					}
				}
			}
		} else {
			System.out.println("A lista de produtos está vazia, não há produtos para eliminar");
			listProductScreen();
		}
	}

	public static void createShelf() {

		try (Scanner scanner = new Scanner(System.in)) {

			System.out.println("Por favor indique a localização da prateleira:");
			Integer location = Integer.parseInt(Utils.validate(scanner, false, "integer"));

			System.out.println("Por favor indique a capacidade da prateleira:");
			Integer capacity = Integer.parseInt(Utils.validate(scanner, false, "integer"));

			Integer productID = null;
			if (!productList.keySet().isEmpty()) {
				System.out.println(
						"Por favor indique a ID do produto presente na prateleira (escolha de entre a lista de produtos):");
				String text = "Lista de produtos:\n";
				if (!productList.isEmpty()) {
					for (int ID : productList.keySet()) {
						text += productList.get(ID).toString();
					}
				} else {
					text += "Vazia!\n";
				}
				System.out.println(text);
				productID = Utils.validate(scanner, text, "product");
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

	public static void alterShelf(String text) {
		if (!shelfList.isEmpty()) {
			try (Scanner scanner = new Scanner(System.in)) {
				System.out.println("Por favor indique a ID da prateleira a alterar:");

				Integer shelfID = Utils.validate(scanner, text, "shelf");

				if (shelfID == 0) {
					listShelfScreen();
				} else {
					Integer shelfProductID = null;
					if (!productList.isEmpty()) {
						System.out.println(productList.getList("product"));
						System.out.println(String.format("Esta prateleira contém o seguinte produto: %d\nInsira o novo valor para este parâmetro (para manter o valor corrente terá de o inserir de novo, se nào inserir nada, este será apagado):", ((Shelf) shelfList.get(shelfID)).getProductID()));
						shelfProductID = Utils.validate(scanner, productList.getList("product"), "product");
						if(shelfProductID==null){
							shelfProductID = ((Shelf) shelfList.get(shelfID)).getProductID();
						}
					}
					
					
					System.out.println("Esta prateleira contém os seguintes produtos: ");

					System.out.println(String.format(
							"Esta prateleira tem a seguinte localização: %d\nInsira o novo valor para este parâmetro (se não inserir nada o valor corrente será mantido):",
							((Shelf) shelfList.get(shelfID)).getLocation()));
					String location = Utils.validate(scanner, true, "integer");
					Integer newLocation = ((Shelf) shelfList.get(shelfID)).getLocation();
					if (location != null) {
						newLocation = Integer.parseInt(location);
					}

					System.out.println(String.format(
							"Esta prateleira tem a seguinte capacidade: %d\nInsira o novo valor para este parâmetro (se não inserir nada o valor corrente será mantido):",
							((Shelf) shelfList.get(shelfID)).getCapacity()));
					String capacity = Utils.validate(scanner, true, "integer");
					Integer newCapacity = ((Shelf) shelfList.get(shelfID)).getCapacity();
					if (capacity != null) {
						newCapacity = Integer.parseInt(capacity);
					}

					System.out.println(String.format(
							"Esta prateleira tem o seguinte preço de aluguer: %.2f€\nInsira o novo valor para este parâmetro (se não inserir nada o valor corrente será mantido):",
							((Shelf) shelfList.get(shelfID)).getLocationRentalPrice()));
					String locationRentalPrice = Utils.validate(scanner, true, "double");
					Double newLocationRentalPrice = ((Shelf) shelfList.get(shelfID)).getLocationRentalPrice();
					if (locationRentalPrice != null) {
						newLocationRentalPrice = Double.parseDouble(locationRentalPrice);
					}

					ShelfRepository.alterElement(shelfID, newLocation, newCapacity, newLocationRentalPrice);

					listShelfScreen();
				}
			}
		} else {
			System.out.println("A lista de prateleiras está vazia, não há prateleiras a editar");
			listShelfScreen();
		}
	}

	public static void seeShelfDetails(String text) {
		if (!shelfList.isEmpty()) {
			try (Scanner scanner = new Scanner(System.in)) {
				System.out.println("Por favor indique a ID da prateleira a consultar:");

				Integer shelfID = Utils.validate(scanner, text, "shelf");

				if (shelfID == 0) {
					listShelfScreen();
				} else {
					shelfList.displayElement(shelfID);
					System.out.println("Por favor prima ENTER para voltar ao ecrã das prateleiras");
					scanner.nextLine();
					listShelfScreen();
				}
			}
		} else {
			System.out.println("A lista de prateleiras está vazia, não há prateleiras a consultar");
			listShelfScreen();
		}
	}

	public static void removeShelf(String text) {
		if (!shelfList.isEmpty()) {
			try (Scanner scanner = new Scanner(System.in)) {

				System.out.println("Por favor indique a ID da prateleira a remover:");
				Integer shelfID = Utils.validate(scanner, text, "shelf");

				if (shelfID == 0) {
					listShelfScreen();
				} else {
					System.out.println("Este é a prateleira que escolheu:");
					shelfList.displayElement(shelfID);
					System.out.println("De certeza que a quer apagar? (prima s para Sim e n para Não)");
					while (true) {
						String response = scanner.nextLine();
						if (response.equals("s")) {
							shelfList.removeElement(shelfID);
							listShelfScreen();
						} else if (response.equals("n")) {
							listShelfScreen();
						} else {
							System.out.println("Por favor prima s ou n");
						}
					}
				}
			}
		} else {
			System.out.println("A lista de prateleiras está vazia, não há prateleiras a remover");
			listShelfScreen();
		}
	}
}
