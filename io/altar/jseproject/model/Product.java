package io.altar.jseproject.model;

import io.altar.jseproject.repository.ProductRepository;

public class Product {
	private int id;
	// private int [] shelfIdLocation;
	private double discount;
	private int tax;
	private double salePrice;	

	public void setId(int id){
		this.id = id;
	}
	
	/*public void setShelfIdLocation(int [] shelfIdLocation){
		this.shelfIdLocation = shelfIdLocation;
	}*/
	
	public void setDiscount(double discount){
		this.discount = discount;
	}
	
	public void setTax(int tax){
		this.tax = tax;
	}
	
	public void setSalePrice(double salePrice){
		this.salePrice = salePrice;
	}
	
	public int getId(){
		return this.id;		
	}
	
	/*public void getShelfIdLocation(){
		System.out.println(this.shelfIdLocation);
	}*/
	
	public double getDiscount(){
		return this.discount;
	}
	
	public int getTax(){
		return this.tax;
	}
	
	public double getSalePrice(){
		return this.salePrice;
	}
	
	public Product(int id, double discount, int tax, double salePrice){
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
		return "ID: " + id + ", Shelves: " + ", Discount: " + discount + ", Tax: " + tax + ", Sale Price: " + salePrice + "\n";
	}
	
}
