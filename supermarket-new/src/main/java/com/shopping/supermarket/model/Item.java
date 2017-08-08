package com.shopping.supermarket.model;

import java.util.Objects;

/**
 * 
 * This Class can be extended as FoodItem, WashingItem, GroceryItem etc by defining their own properties
 * 
 */
public class Item {
	
	private int price;
	
	private String sku;
	
	public Item(String sku,int price){
		this.price=price;
		this.sku=sku;
	}

	public int getPrice() {
		return price;
	}
	

	public String getSku() {
		return sku;
	}

	public void setPrice(int price) {
		this.price = price;
	}


	public void setSku(String sku) {
		this.sku = sku;
	}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (price != item.price) return false;
        return sku != null ? sku.equals(item.sku) : item.sku == null;
    }

    public int hashCode(){
		return Objects.hash(sku,price);
	}
	
}
