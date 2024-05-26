package com.game.project;

import com.game.project.entities.*;
import com.game.project.menus.*;

import java.util.Collections;

import static com.game.project.entities.Category.*;


public class Program {
	
	public static void main(String[] args) {

		// Criando o registrador
		Register register = new Register();

		// Adicionando alguns jogos apenas para teste
		Game game1 = new Game("Rimworld", 59.99, "Slavery", Collections.singletonList(SANDBOX), "Tynan", "Ludeon");
		register.insertGame(game1);

		Game game2 = new Game("CSGO", 79.99, "Boludo", Collections.singletonList(FPS), "Gabe", "Valve");
		register.insertGame(game2);

		// Criando os menus
		StartMenu startMenu = new StartMenu(register);
		GameStoreMenu gameStoreMenu = new GameStoreMenu(register);

		// Mostrando o menu inicial
		startMenu.show();
	}
}
