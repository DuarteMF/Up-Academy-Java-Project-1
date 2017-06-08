package io.altar.jseproject.repository;

import io.altar.jseproject.model.Shelf;

public class ShelfRepository extends EntityRepository<Shelf> {
	private static final ShelfRepository INSTANCE = new ShelfRepository();

	private ShelfRepository() {}

	public static ShelfRepository getInstance() {
		return INSTANCE;
	}

	public static void alterElement(Integer id, Integer location, Integer capacity, Double price) {
		((Shelf)ShelfRepository.getInstance().get(id)).setLocation(location);
		((Shelf)ShelfRepository.getInstance().get(id)).setCapacity(capacity);
		((Shelf)ShelfRepository.getInstance().get(id)).setLocationRentalPrice(price);
	}
	
	public static void toggleProductPresent(Integer id, Integer productID) {
		if(((Shelf)ShelfRepository.getInstance().get(id)).getProductID()==null){
			((Shelf)ShelfRepository.getInstance().get(id)).setProductID(productID);
		}else{
			((Shelf)ShelfRepository.getInstance().get(id)).setProductID(null);
		}
	}
}
