package com.game.project.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Game extends Product{

	public static class GameBuilder{
			
			private String name;
			private Double price;
			private String description;
			private final List<Category> categories = new ArrayList<>();
			private String developer;
			private String publisher;
			
			public GameBuilder setName(String name) {
				this.name = name;
				return this;
			}
			public GameBuilder setPrice(Double price) {
				this.price = price;
				return this;
			}
			public GameBuilder setDescription(String description) {
				this.description = description;
				return this;
			}
			public GameBuilder setDeveloper(String developer) {
				this.developer = developer;
				return this;
			}
			public GameBuilder setPublisher(String publisher) {
				this.publisher = publisher;
				return this;
			}
			
			public GameBuilder addCategory(Category category) {
				categories.add(category);
				return this;
			}
			
			public Game build() {
				return new Game(name, price, description, List.copyOf(categories), developer, publisher);
			}
	}
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

	// Segundo esse artigo, é uma boa pratica fazer o método toString dentro da própria classe (https://www.devmedia.com.br/como-criar-sobreposicoes-usando-o-metodo-tostring-em-java/29042)
	@Override
	public String toString() {
		return "{" +
				"name='" + getName() + '\'' +
				", price=" + getPrice() +
				", description='" + getDescription() + '\'' +
				", categories=" + categories +
				", developer='" + developer + '\'' +
				", publisher='" + publisher + '\'' +
				'}';
	}
}
