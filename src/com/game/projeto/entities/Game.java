package com.game.projeto.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Game extends Product{

	private final List<Category> categories = new ArrayList<Category>();
	private String developer;
	private String publisher;
	
	
	public Game(String name, double price, String description, 
			List<Category> categories,String developer, String publisher) {
		super(name, price, description);
		this.categories.addAll(categories);
		this.setDeveloper(developer);
		this.setPublisher(publisher);
		
	}


	public String getDeveloper() {
		return developer;
	}


	public void setDeveloper(String developer) {
		this.developer = developer;
	}


	public String getPublisher() {
		return publisher;
	}


	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}


	public List<Category> getCategories() {
		return categories;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(developer, publisher);
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Game))
			return false;
		Game other = (Game) obj;
		return Objects.equals(developer, other.developer) && Objects.equals(publisher, other.publisher);
	}

	
	
}
