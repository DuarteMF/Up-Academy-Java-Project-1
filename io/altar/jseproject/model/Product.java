package io.altar.jseproject.model;

import io.altar.jseproject.repository.ProductRepository;

public class Product {
	private Integer id;
	// private int [] shelfIdLocation;
	private Integer discount;
	private Integer tax;
	private Double salePrice;	

	public void setId(Integer id){
		this.id = id;
	}
	
	/*public void setShelfIdLocation(int [] shelfIdLocation){
		this.shelfIdLocation = shelfIdLocation;
	}*/
	
	public void setDiscount(Integer discount){
		this.discount = discount;
	}
	
	public void setTax(Integer tax){
		this.tax = tax;
	}
	
	public void setSalePrice(Double salePrice){
		this.salePrice = salePrice;
	}
	
	public Integer getId(){
		return this.id;		
	}
	
	/*public void getShelfIdLocation(){
		System.out.println(this.shelfIdLocation);
	}*/
	
	public Integer getDiscount(){
		return this.discount;
	}
	
	public Integer getTax(){
		return this.tax;
	}
	
	public Double getSalePrice(){
		return this.salePrice;
	}
	
	public Product(int id, Integer discount, Integer tax, Double salePrice){
		this.id = id;
		this.discount = discount;
		this.tax = tax;
		this.salePrice = salePrice;
		ProductRepository.addToList(this);;
	}
	
	public Product(){
		
	}
	
	@Override
	public String toString(){
		return "ID: " + id + ", Shelves: " + ", Discount: " + discount + ", Tax: " + tax + ", Sale Price: " + salePrice;
	}
	
}
