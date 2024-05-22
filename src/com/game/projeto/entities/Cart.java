package com.game.projeto.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cart {

	private final Set<Product> products = new HashSet<>();
	
	public boolean insertProducts(Product product) {
		return getProducts().add(product);
	}
	
	public boolean removeProducts(Product product) {
		return getProducts().remove(product);
	}
	
	public void empty() {
		getProducts().clear();
	}
	
	public Buy purchaseAll() {
		List<Product> listProducts = new ArrayList<>(getProducts());
		empty();
		return new Buy(listProducts);
	}

	public Set<Product> getProducts() {
		return products;
	}
	
}
