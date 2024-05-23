package com.game.project.menus;

import java.util.Scanner;

public class StartMenu implements Menu{

    /*
    private CartMenu cartMenu;
    private LibraryMenu libraryMenu;
    private GameStoreMenu gameStoreMenu;

    public StartMenu(){

    }

    public StartMenu(CartMenu cartMenu, LibraryMenu libraryMenu, GameStoreMenu gameStoreMenu){
        this.cartMenu = cartMenu;
        this.libraryMenu = libraryMenu;
        this.gameStoreMenu = gameStoreMenu;
    }

     */

    @Override
    public void show(){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Bem vindo a loja de jogos!");
            System.out.println("1. Catálogo");
            System.out.println("2. Carrinho ");
            System.out.println("3. Biblioteca");
            System.out.println("4. Sair");

            System.out.print("Digite a opcao desejada: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice){
                case 1:
                    //gameStoreMenu.show();
                    break;
                case 2:
                    //cartMenu.show();
                    break;
                case 3:
                   //libraryMenu.show();
                    break;
                case 4:
                    System.out.println("Obrigado por visitar a nossa loja!");
                    return;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }
        }
    }

}
