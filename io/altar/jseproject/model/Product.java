package io.altar.jseproject.model;

public class Product {
	private int id;
	// private int [] shelfIdLocation;
	private float discount;
	private float tax;
	private float salePrice;
	
	public void setId(int id){
		this.id = id;
	}
	
	/*public void setShelfIdLocation(int [] shelfIdLocation){
		this.shelfIdLocation = shelfIdLocation;
	}*/
	
	public void setDiscount(float discount){
		this.discount = discount;
	}
	
	public void setTax(float tax){
		this.tax = tax;
	}
	
	public void setSalePrice(float salePrice){
		this.salePrice = salePrice;
	}
	
	public void getId(){
		System.out.println(this.id);		
	}
	
	/*public void getShelfIdLocation(){
		System.out.println(this.shelfIdLocation);
	}*/
	
	public void getDiscount(){
		System.out.println(this.discount);
	}
	
	public void getTax(){
		System.out.println(this.tax);
	}
	
	public void getSalePrice(){
		System.out.println(this.salePrice);
	}
}
