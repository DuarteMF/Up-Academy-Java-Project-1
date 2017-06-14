package io.altar.jseproject.repository;

import java.util.ArrayList;
import java.util.Collections;

import io.altar.jseproject.model.Product;

public class ProductRepository extends EntityRepository<Product> {
	private static final ProductRepository INSTANCE = new ProductRepository();

	private ProductRepository() {}

	public static ProductRepository getInstance() {
		return INSTANCE;
	}

	public static void alterElement(Integer id, ArrayList<Integer> shelf, String name, Integer discount, Integer tax, Double price) {
		((Product)ProductRepository.getInstance().get(id)).setShelfIdLocation(shelf);
		((Product)ProductRepository.getInstance().get(id)).setName(name);		
		((Product)ProductRepository.getInstance().get(id)).setDiscount(discount);
		((Product)ProductRepository.getInstance().get(id)).setTax(tax);
		((Product)ProductRepository.getInstance().get(id)).setSalePrice(price);
	}
	
	public static void addShelfLocation(Integer id, Integer shelfLocation) {
		ArrayList<Integer> currentShelfLocation = ((Product)ProductRepository.getInstance().get(id)).getShelfIdLocation();
		if(currentShelfLocation != null){
			currentShelfLocation.add(shelfLocation);
		}		
		((Product)ProductRepository.getInstance().get(id)).setShelfIdLocation(currentShelfLocation);
	}
	
	public static void alterShelfLocation(Integer shelfOldProductId, Integer shelfNewProductfId, Integer shelfId) {
		if(shelfOldProductId!=shelfNewProductfId){
			if (shelfOldProductId != null) {
				ArrayList<Integer> oldProductShelfList = ((Product) ProductRepository.getInstance()
						.get(shelfOldProductId)).getShelfIdLocation();
				oldProductShelfList.remove(shelfId);
				((Product) ProductRepository.getInstance().get(shelfOldProductId))
						.setShelfIdLocation(oldProductShelfList);
			}

			if (shelfNewProductfId != null) {
			ArrayList<Integer> newProductShelfList = ((Product) ProductRepository.getInstance().get(shelfNewProductfId))
					.getShelfIdLocation();
				newProductShelfList.add(shelfId);
				Collections.sort(newProductShelfList);
				((Product) ProductRepository.getInstance().get(shelfNewProductfId))
						.setShelfIdLocation(newProductShelfList);
			}
		}
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
