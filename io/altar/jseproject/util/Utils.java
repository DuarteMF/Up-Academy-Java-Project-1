package io.altar.jseproject.util;

import java.util.Collections;
import java.util.Scanner;

import io.altar.jseproject.model.Entity;
import io.altar.jseproject.repository.EntityRepository;

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
			}catch(Exception e){
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
				catch(Exception e){
					System.out.println("Por favor escolha uma opção válida!");
				}
			}

		}
	}
	
	public static Integer validate(Scanner scanner, String text, EntityRepository<Entity> entityList){
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
	}

	public static Integer[] validateIntArray(Scanner scanner, String text){
		while(true){
			String input = scanner.nextLine();
			String[] inputArray = input.split(",\\s*"); //regex -> \\s*
			Integer[] integerArray = new Integer[inputArray.length];
			try{
				for(int i = 0; i < inputArray.length; i++){
					integerArray[i] = Integer.parseInt(inputArray[i]);
				}
				return integerArray;
			}catch(Exception e){
				System.out.println("Por favor escolha uma opção válida!");
				System.out.println(text);
			}
		}
	}
	
}
