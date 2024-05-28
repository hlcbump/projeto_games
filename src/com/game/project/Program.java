package com.game.project;

import com.game.project.entities.*;
import com.game.project.menus.*;

import java.util.Collections;

import static com.game.project.entities.Category.*;


public class Program {
	
	public static void main(String[] args) {

		// Criando os objetos (library usando o padrão Singleton)
		Register register = new Register();
		Cart cart = new Cart();
		Library library = Library.getInstance();

		// Adicionando alguns jogos apenas para teste
		Game game1 = new Game("Rimworld", 59.99, "Slavery", Collections.singletonList(SANDBOX), "Tynan", "Ludeon");
		register.insertGame(game1);

		Game game2 = new Game("CSGO", 79.99, "Boludo", Collections.singletonList(FPS), "Gabe", "Valve");
		register.insertGame(game2);

		// Criando os menus
		CartMenu cartMenu = new CartMenu(register, cart, library);
		LibraryMenu libraryMenu = new LibraryMenu(library);
		StartMenu startMenu = new StartMenu(register, cartMenu, libraryMenu);

		// Mostrando o menu inicial
		startMenu.show();
	}
}
