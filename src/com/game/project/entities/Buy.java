package com.game.project.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Buy {
	private final List<Product> products = new ArrayList<>();
	private final LocalDateTime datetime;
	private Double total;
	
	public Buy(List<Product> products) {
		getProducts().addAll(products);
		total = 0.0;
		getProducts().forEach(p -> total += p.getPrice());
		this.datetime = LocalDateTime.now();
	}

	public List<Product> getProducts() {
		return products;
	}


	public LocalDateTime getDatetime() {
		return datetime;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	
}
