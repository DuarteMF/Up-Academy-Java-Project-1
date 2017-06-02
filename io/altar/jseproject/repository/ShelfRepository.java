package io.altar.jseproject.repository;

import java.util.LinkedHashMap;

import io.altar.jseproject.model.Shelf;

public abstract class ShelfRepository {
	private static LinkedHashMap<Integer,Shelf> shelfList = new LinkedHashMap<Integer,Shelf>();
	 
	 
	public static LinkedHashMap<Integer,Shelf> getInstance(){
		 return shelfList;
	 }
	
	public static void addToList(Shelf shelf){
		 shelfList.put(shelf.getId(),shelf);
	 }
	 
	public static void alterElement(Integer id, Integer location, Integer capacity, Double price) {
		shelfList.get(id).setLocation(location);
		shelfList.get(id).setCapacity(capacity);
		shelfList.get(id).setLocationRentalPrice(price);
	}
	
	public static void removeElement(Integer id){
		shelfList.remove(id);
	}
}
