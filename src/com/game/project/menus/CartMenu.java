package com.game.project.menus;

import com.game.project.entities.*;

import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Optional;

public class CartMenu implements Menu{

    private final Cart cart;
    private final Register register;

    public CartMenu(Register register, Cart cart){
        this.register = register;
        this.cart = cart;
    }

    @Override
    public void show() {

        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("Carrinho");
            System.out.println("1. Adicionar jogos ao carrinho");
            System.out.println("2. Remover jogos do carrinho");
            System.out.println("3. Visualizar seu carrinho");
            System.out.println("4. Finalizar compra");
            System.out.println("5. Sair do carrinho");
            System.out.print("Digite a opção: ");
            int choice = sc.nextInt();

            switch (choice){
                case 1:
                    addGameToCart(sc);
                    break;
                case 2:
                    removeGameFromCart(sc);
                    break;
                case 3:
                    viewCart();
                    break;
                case 4:
                    checkout();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente\n");
            }
        }
    }

    public void displayGames(List<Game> games){
        if(games.isEmpty()){
            System.out.println("Não há jogos disponíveis.\n");
        } else {
            for (Game game : games){
                /*
                    Ao passar um objeto como argumento, automaticamente
                    o método toString() da classe é chamado.
                 */
                System.out.println(game);
            }
            System.out.println();
        }
    }

    private void addGameToCart(Scanner sc){
        System.out.println("Jogos disponiveis: ");

        List<Game> allGames = register.getAllGames().stream()
                .toList();

        displayGames(allGames);

        System.out.println("Digite o NOME do jogo que deseja adicionar ao carrinho: ");
        String name = sc.next();

        Game selectedGame = register.getAllGames().stream()
                .filter(game -> game.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);

        if (selectedGame != null){
            cart.insertProducts(selectedGame);
            System.out.println("\nJogo adicionado ao carrinho com sucesso.\n");
        } else {
            System.out.println("\nJogo não encontrado.\n");
        }
    }

    private void removeGameFromCart(Scanner sc){

        Set<Product> myCart = cart.getProducts();

        if (!myCart.isEmpty()){

            System.out.println("Seu carrinho: ");
            myCart.forEach(System.out::println);
            System.out.println();

            while (true){
                System.out.println("Remoção");
                System.out.println("1. Remover apenas um jogo do carrinho");
                System.out.println("2. Remover todos os jogos do carrinho");
                System.out.println("3. Sair da remoção");
                System.out.print("Escolha uma opcao: ");

                int choice = sc.nextInt();

                // obtendo todos os produtos do meu carrinho
                Set<Product> products = cart.getProducts();

                switch (choice){
                    case 1:
                        System.out.print("\nDigite o nome do jogo que deseja excluir: ");
                        String name = sc.next();



                        // utilizando optional para representar um valor que pode ou não estar presente
                        Optional<Product> gameToRemove = products.stream()
                                .filter(product -> product.getName().equalsIgnoreCase(name))
                                .findFirst();

                        if (gameToRemove.isPresent()){
                            System.out.print("\nPara confirmar a exclusão digite novamente o nome do jogo: ");

                            String confirmation = sc.next();

                            if (confirmation.equals(name)) {
                                cart.removeProducts(gameToRemove.get());
                                System.out.println("\nJogo removido do carrinho\n");
                            } else {
                                System.out.println("\nErro ao confirmar o nome. Tente novamente\n");
                            }

                        } else {
                            System.out.println("\nJogo não encontrado no carrinho\n");
                        }

                        break;
                    case 2:
                        if (products != null){
                            System.out.println("\nTem certeza que deseja remover todos os jogos do carrinho? \n");
                            String confirmation = sc.next();

                            if (confirmation.equalsIgnoreCase("s")){
                                cart.empty();
                                System.out.println("\nCarrinho esvaziado com sucesso.\n");
                            } else {
                                System.out.println("\nNão foi possivel confirmar a exclusão\n");
                            }
                        } else {

                            System.out.println("\nO carrinho já esta vazio.\n");
                        }

                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("\nOpção inválida\n");
                }
            }

        } else {
            System.out.println("\nCarrinho vazio\n");
        }
    }

    private void viewCart(){

        Set<Product> myCart = cart.getProducts();

        if (!myCart.isEmpty()){
            System.out.println("\nSeu carrinho contém: \n");
            myCart.forEach(System.out::println);
            System.out.println();
        } else {
            System.out.println("\nCarrinho vazio\n");
        }
    }

    private void checkout(){

        Set<Product> myCart = cart.getProducts();

        if(!myCart.isEmpty()){
            // lógica para adicionar na biblioteca
            System.out.println("Jogo adicionado com sucesso na sua bilioteca");
        } else {
            System.out.println("Seu carrinho está vazio.");
        }
    }
}