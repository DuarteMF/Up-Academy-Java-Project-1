package io.altar.jseproject.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

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
			try{
				inputValue = Integer.parseInt(stored);
				if ((inputValue >= min && inputValue <= max)) {
					return inputValue;
				}else{
					System.out.println("Por favor escolha uma opção válida!");
					System.out.println(text);
				}
			}catch(NumberFormatException e){
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
				if(emptyAllowed){
					return null;
				}else{
					System.out.println("Por favor escolha uma opção válida!");
				}
			}			
			else {
				try{
					if(dataType.equals("integer")){
						Integer.parseInt(stored);
						return stored;
					}else if(dataType.equals("double")){
						Double.parseDouble(stored);
						return stored;
					}
					
				}
				catch(NumberFormatException e){
					System.out.println("Por favor escolha uma opção válida!");
				}
			}

		}
	}
	
	public static Integer validate(Scanner scanner, String text, String entityType){
		Integer ID = null;
		EntityRepository<? extends Entity> entityList = null;
		if(entityType.equals("product")){
			entityList = (EntityRepository<Product>)ProductRepository.getInstance();
		}else if(entityType.equals("shelf")){
			entityList = (EntityRepository<Shelf>)ShelfRepository.getInstance();
		}
		while (true) {
			ID = validate(0, Collections.max(entityList.keySet()), scanner, text);
			if(ID==0){
				return ID;
			}
			if (entityList.containsKey(ID)) {
				return ID;
			}else{
				System.out.println("Por favor escolha uma opção válida!");
			}
		}
	}
	
	/*public static <T extends EntityRepository<Entity>> Integer validate(Scanner scanner, String text, T entityList){
		Integer ID = null;
		while (true) {
			ID = validate(0, Collections.max(entityList.keySet()), scanner, text);
			if(ID==0){
				return ID;
			}
			if (entityList.containsKey(ID)) {
				return ID;
			}else{
				System.out.println("Por favor escolha uma opção válida!");
			}
		}
	}*/

	public static Integer[] validateIntArray(Scanner scanner, String text){
		while(true){
			String input = scanner.nextLine();
			if(!input.isEmpty()){
				String[] inputArray = input.split(",\\s*"); // regex -> \\s*
				Integer[] integerArray = new Integer[inputArray.length];
				try {
					for (int i = 0; i < inputArray.length; i++) {
						integerArray[i] = Integer.parseInt(inputArray[i]);
					}
					return integerArray;
				} catch (NumberFormatException e) {
					System.out.println("Por favor escolha uma opção válida!");
					System.out.println(text);
				}
			}else{
				return null;
			}
		}
	}
	
	public static Integer[] emptyShelves(ShelfRepository shelfList){
		ArrayList<Integer> List = new ArrayList<>();
		for(Integer id: shelfList.keySet()){
			if(((Shelf) shelfList.get(id)).getProductID()==null){
				List.add(id);
			}
		}
		Integer[] emptyList = new Integer[List.size()];
		List.toArray(emptyList);
		return emptyList;
	}
	
}
