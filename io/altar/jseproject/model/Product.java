package io.altar.jseproject.model;

import java.util.Arrays;

import io.altar.jseproject.repository.ProductRepository;

public class Product extends Entity{
	private Integer [] shelfIdLocation;
	private Integer discount;
	private Integer tax;
	private Double salePrice;	
	
	public void setShelfIdLocation(Integer [] shelfIdLocation){
		this.shelfIdLocation = shelfIdLocation;
	}
	
	public void setDiscount(Integer discount){
		this.discount = discount;
	}
	
	public void setTax(Integer tax){
		this.tax = tax;
	}
	
	public void setSalePrice(Double salePrice){
		this.salePrice = salePrice;
	}
	
	public Integer[] getShelfIdLocation(){
		return this.shelfIdLocation;
	}
	
	public Integer getDiscount(){
		return this.discount;
	}
	
	public Integer getTax(){
		return this.tax;
	}
	
	public Double getSalePrice(){
		return this.salePrice;
	}
	
	public Product(Integer[] shelfIdLocation, Integer discount, Integer tax, Double salePrice){
		this.shelfIdLocation = shelfIdLocation;
		this.discount = discount;
		this.tax = tax;
		this.salePrice = salePrice;
		ProductRepository.getInstance().addToList(this);
	}
	
	@Override
	public String toString(){
		return "| ID: " + getId() + "| Shelves: " + Arrays.toString(shelfIdLocation) + "| Discount: " + discount + "%| Tax: " + tax + "%| Sale Price: " + salePrice + "€|\n";
	}
	
}
