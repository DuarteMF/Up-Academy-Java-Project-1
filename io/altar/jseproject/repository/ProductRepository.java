package io.altar.jseproject.repository;

import io.altar.jseproject.model.Product;

public class ProductRepository extends EntityRepository<Product> {
	private static final ProductRepository INSTANCE = new ProductRepository();

	private ProductRepository() {}

	public static ProductRepository getInstance() {
		return INSTANCE;
	}

	public static void alterElement(Integer id, Integer discount, Integer tax, Double price) {
		((Product)ProductRepository.getInstance().get(id)).setDiscount(discount);
		((Product)ProductRepository.getInstance().get(id)).setTax(tax);
		((Product)ProductRepository.getInstance().get(id)).setSalePrice(price);
	}
}
