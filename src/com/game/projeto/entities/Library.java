package com.game.projeto.entities;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {

	private final Map<LocalDateTime, List<Game>> games = new HashMap<>();
	private final Map<LocalDateTime, List<DLC>> dlcs = new HashMap<>();
	private static Library INSTANCE;
	
	//Privado por padrão
	private Library() {
	}
	
	public static synchronized Library getInstance() {
		if (INSTANCE == null) INSTANCE = new Library();
		return INSTANCE;
	}
	
	public void addProductsPurchased(Buy buy) {
		List<Game> gamesPurchased = buy.getProducts()
				.stream()
				.filter(product -> product instanceof Game)
				.map(game -> (Game) game)
				.toList();
		List<DLC> dlcsPurchased = buy.getProducts()
				.stream()
				.filter(product -> product instanceof DLC)
				.map(game -> (DLC) game)
				.toList();
		if (!gamesPurchased.isEmpty()) getGames().put(buy.getDatetime(), gamesPurchased);
		if (!dlcsPurchased.isEmpty()) getDlcs().put(buy.getDatetime(), dlcsPurchased);
	}


	public Map<LocalDateTime, List<Game>> getGames() {
		return games;
	}

	public Map<LocalDateTime, List<DLC>> getDlcs() {
		return dlcs;
	}
	
	
}
