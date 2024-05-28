package com.game.project.menus;

import com.game.project.entities.*;

import java.util.*;

public class CartMenu implements Menu{

    private final Cart cart;
    private final Register register;
    private final Library library;

    public CartMenu(Register register, Cart cart, Library library){
        this.register = register;
        this.cart = cart;
        this.library = library;
    }

    @Override
    public void show() {

        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("\nCarrinho");
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
                    checkout(sc);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente\n");
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

    public void addGameToCart(Scanner sc){

        List<Game> allGames = register.getAllGames();

        System.out.println("\nJogos disponíveis: \n");
        displayGames(register.getAllGames());
        System.out.println("\nDigite o Índice do jogo que deseja adicionar ao carrinho: ");
        int gameIndex = sc.nextInt();

        if (gameIndex >= 0 && gameIndex < allGames.size()){
            Game selectedGame = allGames.get(gameIndex);
            System.out.println("Jogo escolhido: " + selectedGame);
            cart.insertProducts(selectedGame);
            System.out.println("\nJogo adicionado ao carrinho com sucesso.");
        } else {
            System.out.println("\nJogo não encontrado. Tente novamente");
        }
    }

    //método para remover itens do carrinho
    private void removeGameFromCart(Scanner sc){

        /*
            -obtendo todos os produtos do carrinho-
            o método getProducts() da classe Cart retorna um Set,
            sendo assim a necessidade do set ao invés de lista
        */
        Set<Product> myCart = cart.getProducts();

        if (!myCart.isEmpty()){

            System.out.println("Seu carrinho: ");
            myCart.forEach(System.out::println);
            System.out.println();

            while (true){
                System.out.println("\nRemoção");
                System.out.println("1. Remover apenas um jogo do carrinho");
                System.out.println("2. Remover todos os jogos do carrinho");
                System.out.println("3. Sair da remoção");
                System.out.print("Escolha uma opcao: ");

                int choice = sc.nextInt();

                //obtendo todos os produtos do carrinho
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

    //método que verifica se há itens no carrinho
    private void viewCart(){

        //obtendo todos os produtos do carrinho
        Set<Product> myCart = cart.getProducts();

        if (!myCart.isEmpty()){
            System.out.println("\nSeu carrinho contém: \n");
            myCart.forEach(System.out::println);
            System.out.println();
        } else {
            System.out.println("\nCarrinho vazio\n");
        }
    }

    //método que será usado pelo metodo checkout
    private double calculateTotalPrice(Set<Product> products){
        double totalPrice = 0.0;

        for (Product product : products)
            totalPrice += product.getPrice();
        return totalPrice;
    }

    private void checkout(Scanner sc){

        //obtendo todos os produtos do carrinho
        Set<Product> myCart = cart.getProducts();

        if(!myCart.isEmpty()){

            System.out.println("\nSeu carrinho contém: \n");
            myCart.forEach(System.out::println);
            System.out.println("\nPreço total: \n" + calculateTotalPrice(myCart));

            System.out.println("\nDeseja efetuar a compra? (S ou N)\n");
            String confirmation = sc.next();

            //convertendo o set do carrinho para list
            List<Product> productList = new ArrayList<>(myCart);

            if (confirmation.equalsIgnoreCase("S")){

                //usando o método purchaseAll() de cart
                Buy purchase = cart.purchaseAll();

                //adicionando o objeto com a compra na biblioteca
                library.addProductsPurchased(purchase);

                System.out.println("\nJogo adicionado com sucesso na sua bilioteca.\n");
            } else {
                System.out.println("\nVocê escolheu não continuar com a compra.\n");
            }
        } else {
            System.out.println("\nSeu carrinho está vazio.\n");
        }
    }
}