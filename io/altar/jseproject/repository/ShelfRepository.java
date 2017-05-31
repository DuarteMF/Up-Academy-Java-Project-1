package io.altar.jseproject.repository;

import java.util.ArrayList;

import io.altar.jseproject.model.Shelf;

public abstract class ShelfRepository {
	private static ArrayList<Shelf> shelfList = new ArrayList<Shelf>();
	 
	 
	 public static ArrayList<Shelf> getInstance(){
		 return shelfList;
	 }
}
