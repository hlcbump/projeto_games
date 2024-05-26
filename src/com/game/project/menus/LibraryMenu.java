package com.game.project.menus;

import com.game.project.entities.Game;
import com.game.project.entities.Library;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class LibraryMenu implements Menu{

    private final Library library;

    public LibraryMenu(Library library){
        this.library = library;
    }

    @Override
    public void show() {
        Map <LocalDateTime, List<Game>> gamesMap = library.getGames();

        if (gamesMap.isEmpty()){
            System.out.println("\nSua biblioteca está vazia! Vá visitar o nosso catálogo.\n");
        } else {
            System.out.println("\nSua biblioteca: \n");
            gamesMap.values().forEach(gamesList -> {
                gamesList.forEach(System.out::println);
            });
        }
    }
}
