package com.game.project.menus;

import com.game.project.entities.Register;

import java.util.Scanner;

public class StartMenu implements Menu{

    private final GameStoreMenu gameStoreMenu;
    private final CartMenu cartMenu;
    private final LibraryMenu libraryMenu;

    public StartMenu(Register register, CartMenu cartMenu, LibraryMenu libraryMenu) {
        this.gameStoreMenu = new GameStoreMenu(register);
        this.cartMenu = cartMenu;
        this.libraryMenu = libraryMenu;
    }

    @Override
    public void show(){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Bem vindo a loja de jogos!");
            System.out.println("1. Catálogo");
            System.out.println("2. Carrinho ");
            System.out.println("3. Biblioteca");
            System.out.println("4. Sair");

            System.out.print("Digite a opcão desejada: ");
            int choice = sc.nextInt();
            sc.nextLine();
            System.out.println();

            switch (choice){
                case 1:
                    gameStoreMenu.show();
                    break;
                case 2:
                    cartMenu.show();
                    break;
                case 3:
                   libraryMenu.show();
                    break;
                case 4:
                    System.out.println("\nObrigado por visitar a nossa loja!\n");
                    return;
                default:
                    System.out.println("\nOpcão inválida. Tente novamente.\n");
            }
        }
    }
}
