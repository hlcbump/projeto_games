package com.game.project.menus;

import com.game.project.entities.Register;

import java.util.Scanner;

public class StartMenu implements Menu{

    private final GameStoreMenu gameStoreMenu;

    public StartMenu(Register register) {
        this.gameStoreMenu = new GameStoreMenu(register);
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
            System.out.println("");

            switch (choice){
                case 1:
                    gameStoreMenu.show();
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
                    System.out.println("Opcão inválida. Tente novamente.");
            }
        }
    }
}
