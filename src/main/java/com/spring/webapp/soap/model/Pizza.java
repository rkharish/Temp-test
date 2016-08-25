package com.spring.webapp.soap.model;

import java.util.List;

public class Pizza {

	private String type;
	private float price;
	private String size;
	private List<String> toppings;
	private String store;
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public List<String> getToppings() {
		return toppings;
	}

	public void setToppings(List<String> toppings) {
		this.toppings = toppings;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	@Override
	public String toString() {
		return "Pizza [type=" + type + ", price=" + price + ", size=" + size + ", toppings=" + toppings + ", store="
				+ store + "]";
	}

}
