package io.altar.jseproject.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;

import io.altar.jseproject.model.Entity;
import io.altar.jseproject.model.Product;
import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.repository.EntityRepository;
import io.altar.jseproject.repository.ProductRepository;
import io.altar.jseproject.repository.ShelfRepository;

public abstract class Utils {
	public static Integer validate(Integer min, Integer max, Scanner scanner, String text) {
		Integer inputValue = null;
		String stored = "";
		while (true) {
			stored = scanner.nextLine();
			try {
				inputValue = Integer.parseInt(stored);
				if ((inputValue >= min && inputValue <= max)) {
					return inputValue;
				} else {
					System.out.println("Por favor escolha uma opção válida!");
					System.out.println(text);
				}
			} catch (NumberFormatException e) {
				System.out.println("Por favor escolha uma opção válida!");
				System.out.println(text);
			}
		}
	}

	public static String validate(Scanner scanner, boolean emptyAllowed, String dataType) {
		String stored = "";
		while (true) {
			stored = scanner.nextLine();
			if (stored.isEmpty()) {
				if (emptyAllowed) {
					return null;
				} else {
					System.out.println("Por favor escolha uma opção válida!");
				}
			} else {
				try {
					if (dataType.equals("integer")) {
						Integer.parseInt(stored);
						return stored;
					} else if (dataType.equals("double")) {
						Double.parseDouble(stored);
						return stored;
					}

				} catch (NumberFormatException e) {
					System.out.println("Por favor escolha uma opção válida!");
				}
			}

		}
	}

	public static Integer validate(Scanner scanner, String text, String entityType) {
		Integer ID = null;
		EntityRepository<? extends Entity> entityList = null;
		if (entityType.equals("product")) {
			entityList = (EntityRepository<Product>) ProductRepository.getInstance();
		} else if (entityType.equals("shelf")) {
			entityList = (EntityRepository<Shelf>) ShelfRepository.getInstance();
		}
		while (true) {
			ID = validate(0, Collections.max(entityList.keySet()), scanner, text);
			if (ID == 0) {
				return ID;
			}
			if (entityList.containsKey(ID)) {
				return ID;
			} else {
				System.out.println("Por favor escolha uma opção válida!");
			}
		}
	}

	/*
	 * public static <T extends EntityRepository<Entity>> Integer
	 * validate(Scanner scanner, String text, T entityList){ Integer ID = null;
	 * while (true) { ID = validate(0, Collections.max(entityList.keySet()),
	 * scanner, text); if(ID==0){ return ID; } if (entityList.containsKey(ID)) {
	 * return ID; }else{
	 * System.out.println("Por favor escolha uma opção válida!"); } } }
	 */

	public static ArrayList<Integer> validateIntArray(Scanner scanner, String text, Integer[] emptyShelves) {
		while (true) {
			String input = scanner.nextLine();
			if (!input.isEmpty()) {
				String[] inputArray = input.split(",\\s*"); // regex -> \\s*
				Integer[] integerArray = new Integer[inputArray.length];
				try {
					for (int i = 0; i < inputArray.length; i++) {
						integerArray[i] = Integer.parseInt(inputArray[i]);
					}
					if(validate(integerArray, emptyShelves)){
						return new ArrayList<>(Arrays.asList(integerArray));
					}else{
						System.out.println("Por favor escolha uma opção válida!");
						System.out.println(text);
					}
				} catch (NumberFormatException e) {
					System.out.println("Por favor escolha uma opção válida!");
					System.out.println(text);
				}
			} else {
				return null;
			}
		}
	}

	public static Integer[] emptyShelves() {
		ShelfRepository shelfList = ShelfRepository.getInstance();
		ArrayList<Integer> List = new ArrayList<>();
		for (Integer id : shelfList.keySet()) {
			if (((Shelf) shelfList.get(id)).getProductID() == null) {
				List.add(id);
			}
		}
		Integer[] emptyList = new Integer[List.size()];
		List.toArray(emptyList);
		return emptyList;
	}

	public static Integer[] unassignedProducts(){
		ProductRepository productList = ProductRepository.getInstance();
		ArrayList<Integer> List = new ArrayList<>();
		for (Integer id : productList.keySet()) {
			if (((Product) productList.get(id)).getShelfIdLocation() == null) {
				List.add(id);
			}
		}
		Integer[] emptyList = new Integer[List.size()];
		List.toArray(emptyList);
		return emptyList;
	}
	
	public static boolean validate(Integer[] array1, Integer[] parentArray) {
		boolean exitWhile = false;
		if (array1 != null && parentArray != null) {
			int i = 0;
			while (!exitWhile && i < array1.length) {
				boolean existsInParent = false;
				for (int j = 0; j < parentArray.length; j++) {
					if (array1[i] == parentArray[j]) {
						existsInParent = true;
						break;
					}
				}
				exitWhile = !existsInParent;
				i++;
			}
			if(exitWhile){
				return false;
			}else{
				return true;
			}
		}else{
			return false;
		}
	}
	
//	public static boolean validate(Integer number, Integer[] parentArray) {
//		boolean existsInParent = false;
//		for (int j = 0; j < parentArray.length; j++) {
//			if (number == parentArray[j]) {
//				existsInParent = true;
//				break;
//			}
//		}
//		return existsInParent;
//	}

	public static Integer validate(Scanner scanner, String entityList, Set<Integer> Products){
		Integer inputValue = null;
		while (true) {
			String input = scanner.nextLine();
			if (!input.isEmpty()) {
				try {
					inputValue = Integer.parseInt(input);
					if(Products.contains(inputValue)){
						return inputValue;
					}else{
						System.out.println("Por favor escolha uma opção válida!");
						System.out.println(entityList);
					}
				} catch (NumberFormatException e) {
					System.out.println("Por favor escolha uma opção válida!");
					System.out.println(entityList);
				}
			} else {
				return null;
			}
		}
	}
	
}
