/*
    o Registrador serve para gerenciar os objetos que serão manipulados,
    atuando como um "repositório" e centralizando a lógica de manipulação
    dos dados.
    No nosso caso são os objetos 'Game'.
    Podemos adicionar jogos com o método insertGame(Game game) e visualizar
    os jogos com o método getAllGames()
 */

package com.game.project.entities;

import java.util.ArrayList;
import java.util.List;

public class Register {

    private final List<Game> gameRegister;

    public Register(){
        this.gameRegister = new ArrayList<>();
    }

    public void insertGame(Game game){
        gameRegister.add(game);
    }

    public List<Game> getAllGames(){
        return new ArrayList<>(gameRegister);
    }
}


