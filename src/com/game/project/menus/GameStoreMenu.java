package com.game.project.menus;

import com.game.project.entities.*;

import java.util.List;
import java.util.Scanner;

public class GameStoreMenu implements Menu{

    private final Register register;
    private final CartMenu cartMenu;

    public GameStoreMenu(Register register, CartMenu cartMenu) {
        this.register = register;
        this.cartMenu = cartMenu;
    }

    @Override
    public void show(){

        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("\nCatalogo");
            System.out.println("1. Ver todos os jogos");
            System.out.println("2. Buscar por categoria");
            System.out.println("3. Buscar por nome");
            System.out.println("4. Selecionar um jogo para o carrinho");
            System.out.println("5. Ir para o carrinho");
            System.out.println("6. Voltar para o Menu Principal");

            System.out.print("Selecione uma opcão: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    displayGames(register.getAllGames());
                    break;
                case 2:
                    filterByCategory(sc);
                    break;
                case 3:
                    filterByName(sc);
                    break;
                case 4:
                    selectGame(sc);
                    break;
                case 5:
                    cartMenu.show();
                case 6:
                    return;
                default:
                    System.out.println("Opcão inválida. Tente novamente\n");
            }
        }
    }

    //método para mostrar os jogos do catálogo (sem utilizar toString)
    public void displayGames(List<Game> games){
        if(games.isEmpty()){
            System.out.println("Não há jogos disponíveis.");
        } else {
            for (Game game : games){
                System.out.println("Jogo " + games.indexOf(game) + ": " + game.getName() + " " + game.getPrice() + " " + game.getDescription() + " " + game.getCategories());
            }
        }
    }

    /*
        utilizando stream para manipular lista de forma mais
        declarativa e intuitiva sem a necessidade de loops

        (não é necessario importar porque estão sendo
        chamados em uma lista e não diretamente em um objeto)
     */
    public void filterByCategory(Scanner sc){

        System.out.println("\nCategorias: \n\n" + List.of(Category.values()) + "\n");
        System.out.print("Digite a categoria do jogo: ");


        String categoryName = sc.next().toUpperCase();
        System.out.println("Buscando por " + categoryName + "\n");
        Category categories = Category.valueOf(categoryName);

        List<Game> filteredGames = register.getAllGames().stream()
                .filter(game -> game.getCategories().contains(categories))
                .toList();


        displayGames(filteredGames);
    }

    public void filterByName(Scanner sc){

        System.out.println("Digite o nome do jogo: ");
        String name = sc.next();

        List<Game> filteredGames = register.getAllGames().stream()
                .filter(game -> game.getName().equalsIgnoreCase(name))
                .toList();

        displayGames(filteredGames);
    }

    public void selectGame(Scanner sc){
        cartMenu.addGameToCart(sc);
    }
}
