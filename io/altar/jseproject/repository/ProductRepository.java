package io.altar.jseproject.repository;

import java.util.LinkedHashMap;

import io.altar.jseproject.model.Product;

public abstract class ProductRepository {
//	 private static ArrayList<Product> productList = new ArrayList<Product>();
	 private static LinkedHashMap<Integer,Product> productList = new LinkedHashMap<Integer,Product>();
	 
	 
//	 public static ArrayList<Product> getInstance(){
//		 return productList;
//	 }
	 
	 public static LinkedHashMap<Integer,Product> getInstance(){
		 return productList;
	 }
	 
//	 public static void addToList(Product product){
//		 productList.add(product);
//	 }
	 
	 public static void addToList(Product product){
		 productList.put(product.getId(),product);
	 }
	 
	public static void alterElement(Integer id, Integer discount, Integer tax, Double price) {
		productList.get(id).setDiscount(discount);
		productList.get(id).setTax(tax);
		productList.get(id).setSalePrice(price);
	}
	
	public static void removeElement(Integer id){
		productList.remove(id);
	}
}
