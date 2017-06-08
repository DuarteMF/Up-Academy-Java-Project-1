package io.altar.jseproject.model;

import io.altar.jseproject.repository.ShelfRepository;

public class Shelf extends Entity{
	private Integer location;
	private Integer capacity;
	private Integer productID;
	private Double locationRentalPrice;
	
	public void setLocation(Integer location){
		this.location = location;
	}
	
	public void setCapacity(Integer capacity){
		this.capacity = capacity;
	}
	
	public void setProductID(Integer productID){
		this.productID = productID;
	}
	
	public void setLocationRentalPrice(Double locationRentalPrice){
		this.locationRentalPrice = locationRentalPrice;
	}
	
	public Integer getLocation(){
		return this.location;
	}
	
	public Integer getCapacity(){
		return this.capacity;
	}
	
	public Integer getProductID(){
		return this.productID;
	}
	
	public Double getLocationRentalPrice(){
		return this.locationRentalPrice;
	}
	
	public Shelf(Integer location, Integer capacity, Integer productID, Double locationRentalPrice){
		this.location = location;
		this.capacity = capacity;
		this.productID = productID;
		this.locationRentalPrice = locationRentalPrice;
		ShelfRepository.getInstance().addToList(this);
	}
	
	@Override
	public String toString(){
		return String.format("| ID: %d | Localização: %d | Capacidade: %d | ID do Produto: %d | Preço de Aluguer: %.2f€|\n", getId(), location, capacity, productID, locationRentalPrice);
	}
}
