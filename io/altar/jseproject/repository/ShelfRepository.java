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
}
