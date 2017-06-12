package io.altar.jseproject.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import io.altar.jseproject.model.Product;

public class ProductRepository extends EntityRepository<Product> {
	private static final ProductRepository INSTANCE = new ProductRepository();

	private ProductRepository() {}

	public static ProductRepository getInstance() {
		return INSTANCE;
	}

	public static void alterElement(Integer id, Integer[] shelf, String name, Integer discount, Integer tax, Double price) {
		((Product)ProductRepository.getInstance().get(id)).setShelfIdLocation(shelf);
		((Product)ProductRepository.getInstance().get(id)).setName(name);		
		((Product)ProductRepository.getInstance().get(id)).setDiscount(discount);
		((Product)ProductRepository.getInstance().get(id)).setTax(tax);
		((Product)ProductRepository.getInstance().get(id)).setSalePrice(price);
	}
	
	public static void addShelfLocation(Integer id, Integer shelfLocation) {
		Integer[] currentShelfLocation = ((Product)ProductRepository.getInstance().get(id)).getShelfIdLocation();
		Integer[] newShelfLocation = {shelfLocation};
		if(currentShelfLocation != null){
			ArrayList<Integer> temp = (ArrayList<Integer>) Arrays.asList(currentShelfLocation);
			temp.add(shelfLocation);
//			Integer[] newShelfLocation = new Integer[temp.size()];
			newShelfLocation = temp.toArray(newShelfLocation);
		}		
		((Product)ProductRepository.getInstance().get(id)).setShelfIdLocation(newShelfLocation);
	}
	
	
	public static void alterShelfLocation(Integer productId, Integer editedShelfId, Integer newShelfLocation) {
		Integer[] oldShelfList = ((Product) ProductRepository.getInstance().get(productId)).getShelfIdLocation();
		Set<Integer> temp = new HashSet<>();
		Collections.addAll(temp, oldShelfList);
		temp.remove(editedShelfId);
		if(newShelfLocation!=null){
			temp.add(newShelfLocation);			
		}
		Integer[] newShelfList = temp.toArray(new Integer[temp.size()]);
		Arrays.sort(newShelfList);
		((Product)ProductRepository.getInstance().get(productId)).setShelfIdLocation(newShelfList);
	}
}

//public enum ProductRepository extends EntityRepository<Product> {
//	INSTANCE;
//
//	public static void alterElement(Integer id, Integer discount, Integer tax, Double price) {
//		((Product)INSTANCE.get(id)).setDiscount(discount);
//		((Product)INSTANCE.get(id)).setTax(tax);
//		((Product)INSTANCE.get(id)).setSalePrice(price);
//	}
//}
