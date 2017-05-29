package io.altar.jseproject.model;

public class Shelf {
	int id;
	int location;
	int capacity;
	// String product;
	float locationRentalPrice;
	
	public void setId(int id){
		this.id = id;
	}
	
	public void setLocation(int location){
		this.location = location;
	}
	
	public void setCapacity(int capacity){
		this.capacity = capacity;
	}
	
	/*public void setProduct(String product){
		this.product = product;
	}*/
	
	public void setLocationRentalPrice(float locationRentalPrice){
		this.locationRentalPrice = locationRentalPrice;
	}
	
	public void getId(){
		System.out.println(this.id);		
	}
	
	public void getLocation(){
		System.out.println(this.location);
	}
	
	public void getCapacity(){
		System.out.println(this.capacity);
	}
	
	/*public void getProduct(){
		System.out.println(this.product);
	}*/
	
	public void getLocationRentalPrice(){
		System.out.println(this.locationRentalPrice);
	}
}
