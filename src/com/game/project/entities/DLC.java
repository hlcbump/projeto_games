package com.game.project.entities;

import java.util.Objects;

public class DLC extends Product{

	public static class DLCBuilder{
		
		private String name;
		private Double price;
		private String description;
		private Game game;
		
		public DLCBuilder setName(String name) {
			this.name = name;
			return this;
		}
		public DLCBuilder setPrice(Double price) {
			this.price = price;
			return this;
		}
		public DLCBuilder setDescription(String description) {
			this.description = description;
			return this;
		}
		public DLCBuilder setGame(Game game) {
			this.game = game;
			return this;
		}
		
		public DLC build() {
			return new DLC(name, price, description, game);
		}
		
		
	}
	
	private Game game;
	
	public DLC(String name, Double price, String description, Game game) {
		super(name, price, description);
		setGame(game);
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		Objects.requireNonNull(game);
		this.game = game;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(game);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof DLC))
			return false;
		DLC other = (DLC) obj;
		return Objects.equals(game, other.game);
	}
	
	
}
