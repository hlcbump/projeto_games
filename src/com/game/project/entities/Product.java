package com.game.project.entities;

import java.util.Objects;

public abstract class Product {

	private String name;
	
	private Double price;
	
	private String description;
	
	public Product(String name, double price, String description) {
		setName(name);
		setPrice(price);
		setDescription(description);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		Objects.requireNonNull(name);
		if (name.isBlank() || name.isEmpty()) throw new IllegalArgumentException("Name must be provided");
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		if (price < 0.0) throw new IllegalArgumentException("Game price must be positive");
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		Objects.requireNonNull(description);
		if (description.isBlank() || description.isEmpty()) throw new IllegalArgumentException("Description is needed");
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Product))
			return false;
		Product other = (Product) obj;
		return Objects.equals(name, other.name);
	}
	
	
	
}
