package com.spring.webapp.soap.provider;


import java.util.HashMap;

import javax.jws.WebService;

import com.spring.webapp.soap.model.Pizza;

@WebService
public class PizzaHutProvider {
	
	PizzaHutProvider(){
	}
	
	HashMap<String, Pizza> Pizzastore = new HashMap<>();
	
	//////////Create Order
	public String CreateOrder(String name, Pizza pizza){
	if (Pizzastore.containsKey(name)){
		return "Have patience "+name+", your order has already been placed!";
	}else{
		Pizzastore.put(name, pizza);
		return "Your order has been placed and will be there in 30 mins!";
	}
	}
	
	//////////Cancel Order
	public String CancelOrder(String name){
		if (Pizzastore.containsKey(name)){
			Pizzastore.remove(name);
			return "Your order has been cancel "+name;
		}else{
			return "Sorry you don't have an exsisting order with us at the moment!";
		}
		}
	
	//////////List All orders
	public HashMap<String, Pizza> ListAllOrders(){
		return Pizzastore;
	}
	
	

}
