package com.game.project.menus;

import com.game.project.entities.*;

import java.util.List;
import java.util.Scanner;

public class GameStoreMenu implements Menu{

    private final Register register;

    public GameStoreMenu(Register register) {
        this.register = register;
    }

    @Override
    public void show(){

        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("Catalogo");
            System.out.println("1. Ver todos os jogos");
            System.out.println("2. Buscar por categoria");
            System.out.println("3. Buscar por nome");
            System.out.println("4. Buscar por data");
            System.out.println("5. Selecionar um jogo para o carrinho");
            System.out.println("6. Voltar para o Menu Principal");

            System.out.print("Selecione uma opcão: ");
            int choice = sc.nextInt();
            System.out.println("");

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
                    //filterByDate(sc)
                    break;
                case 5:
                    selectGame(sc);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Opcão inválida. Tente novamente");
            }
        }
    }

    public void displayGames(List<Game> games){
        if(games.isEmpty()){
            System.out.println("Não há jogos disponíveis.");
        } else {
            for (Game game : games){
                /*
                    Ao passar um objeto como argumento, automaticamente
                    o método toString() da classe é chamado.
                 */
                System.out.println(game);
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

    // Game não há atributo date
    public void filterByDate(Scanner sc){

    }

    public void selectGame(Scanner sc){
        System.out.println("Digite o NOME do jogo que deseja adicionar ao carrinho: ");
        String name = sc.next();

        Game selectedGame = register.getAllGames().stream()
                .filter(game -> game.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);

        if (selectedGame != null){
            // lógica para adicionar ao carrinho
            System.out.println("Jogo adicionado ao carrinho com sucesso.");
        } else {
            System.out.println("Jogo não encontrado.");
        }
    }
}
