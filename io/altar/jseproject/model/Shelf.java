package io.altar.jseproject.model;

import io.altar.jseproject.repository.ShelfRepository;

public class Shelf {
	private Integer id;
	private Integer location;
	private Integer capacity;
	// private String product;
	private Double locationRentalPrice;
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public void setLocation(Integer location){
		this.location = location;
	}
	
	public void setCapacity(Integer capacity){
		this.capacity = capacity;
	}
	
	/*public void setProduct(String product){
		this.product = product;
	}*/
	
	public void setLocationRentalPrice(Double locationRentalPrice){
		this.locationRentalPrice = locationRentalPrice;
	}
	
	public Integer getId(){
		return this.id;		
	}
	
	public Integer getLocation(){
		return this.location;
	}
	
	public Integer getCapacity(){
		return this.capacity;
	}
	
	/*public String getProduct(){
		return this.product;
	}*/
	
	public Double getLocationRentalPrice(){
		return this.locationRentalPrice;
	}
	
	public Shelf(int id, Integer location, Integer capacity, Double locationRentalPrice){
		this.id = id;
		this.location = location;
		this.capacity = capacity;
		this.locationRentalPrice = locationRentalPrice;
		ShelfRepository.addToList(this);;
	}
	
	public Shelf(){
		
	}
	
	@Override
	public String toString(){
		return "ID: " + id + ", Location: " + location + ", Capacity: " + capacity + ", Product: " + ", Location Rental Price: " + locationRentalPrice + "\n";
	}
}
