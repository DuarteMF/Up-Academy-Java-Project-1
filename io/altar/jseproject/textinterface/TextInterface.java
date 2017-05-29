package io.altar.jseproject.textinterface;

import java.util.Scanner;

public class TextInterface {
	
	public static void firstScreen(){
		System.out.println("Por favor selecione uma das seguintes op��es:\n"
				+ "1)	Listar produtos\n"
				+ "2)	Listar prateleiras\n"
				+ "3)	Sair");
		
		try(Scanner scanner = new Scanner(System.in)){
			while(!(scanner.hasNextInt()) || scanner.nextInt()>3){
				System.out.println("Por favor escolha entre uma das 3 op��es!");
				scanner.nextLine();
			}
			int input = scanner.nextInt();
			switch(input){
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
	
	public static void listProductScreen(){
		//List Products
		System.out.println("Por favor selecione uma das seguintes op��es:\n"
				+ "1)	Criar novo produto\n"
				+ "2)	Editar um produto existente\n"
				+ "3)	Consultar o detalhe de um produto\n"
				+ "4)	Remover um produto\n"
				+ "5)	Voltar ao ecr� anterior");
		
		try(Scanner scanner = new Scanner(System.in)){
			while(!(scanner.hasNextInt()) || scanner.nextInt()>5){
				System.out.println("Por favor escolha entre uma das 5 op��es!");
				scanner.nextLine();
			}
			int input = scanner.nextInt();
			switch(input){
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
	
	public static void listShelfScreen(){
		//List Shelves
		System.out.println("Por favor selecione uma das seguintes op��es:\n"
				+ "1)	Criar nova prateleira\n"
				+ "2)	Editar uma prateleira existente\n"
				+ "3)	Consultar o detalhe de uma prateleira\n"
				+ "4)	Remover uma prateleira\n"
				+ "5)	Voltar ao ecr� anterior");
		
		try(Scanner scanner = new Scanner(System.in)){
			while(!(scanner.hasNextInt()) || scanner.nextInt()>5){
				System.out.println("Por favor escolha entre uma das 5 op��es!");
				scanner.nextLine();
			}
			int input = scanner.nextInt();
			switch(input){
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
	
	public static void leave(){
		
	}
}
