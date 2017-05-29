package io.altar.jseproject.textinterface;

import java.util.Scanner;

public class TextInterface {
	public static void main(String[] args) {
		
	}
	
	public static void firstScreen(){
		System.out.println("Por favor selecione uma das seguintes opções:\n"
				+ "1)	Listar produtos\n"
				+ "2)	Listar prateleiras\n"
				+ "3)	Sair");
		
		Scanner scanner = new Scanner(System.in);
		int input = Integer.parseInt(scanner.nextLine());
		scanner.close();
		
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
	
	public static void listProductScreen(){
		//List Products
		System.out.println("Por favor selecione uma das seguintes opções:\n"
				+ "1)	Criar novo produto\n"
				+ "2)	Editar um produto existente\n"
				+ "3)	Consultar o detalhe de um produto\n"
				+ "4)	Remover um produto\n"
				+ "5)	Voltar ao ecrã anterior\n");
		
		Scanner scanner = new Scanner(System.in);
		int input = Integer.parseInt(scanner.nextLine());
		scanner.close();
		
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
				break;
		}
	}
	
	public static void listShelfScreen(){
		//List Shelves
		System.out.println("Por favor selecione uma das seguintes opções:\n"
				+ "1)	Criar novo produto\n"
				+ "2)	Editar um produto existente\n"
				+ "3)	Consultar o detalhe de um produto\n"
				+ "4)	Remover um produto\n"
				+ "5)	Voltar ao ecrã anterior\n");
		
		Scanner scanner = new Scanner(System.in);
		int input = Integer.parseInt(scanner.nextLine());
		scanner.close();
		
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
				break;
		}
	}
	
	public static void leave(){
		
	}
}
