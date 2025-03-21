package factory;

import java.util.Scanner;
import model.ItemPedido;
import model.Lanche;
import util.MenuBuilder;

public class LancheFactory implements ItemFactory {
    @Override
    public ItemPedido criarItem(Scanner scanner) {
        String[] opcoes = {
            "Misto",
            "Hambúrguer",
            "X-Burger",
            "X-Eggs",
            "Duplo",
            "X-Calabresa",
            "X-Eggs Calabresa",
            "X-Bacon",
            "X-Eggs Bacon",
            "X-Duplo com bacon",
            "X-Bacon com Calabresa",
            "X-Tudo"
        };
        
        MenuBuilder.exibirMenu("Adicionar Lanche", opcoes, true);
        int opcao = MenuBuilder.lerOpcao(scanner, 0, opcoes.length);
        
        if (opcao == 0) {
            return null; // Voltar ao menu principal
        }
        
        double preco = 0;
        String pao = "";
        String recheio = "";
        String molho = "";
        
        switch (opcao) {
            case 1:
                preco = 8.0;
                pao = "Francês";
                recheio = "Queijo e Presunto";
                molho = "Maionese";
                break;
            case 2:
                preco = 10.0;
                pao = "Brioche";
                recheio = "Carne";
                molho = "Barbecue";
                break;
            case 3:
                preco = 12.0;
                pao = "Australiano";
                recheio = "Carne, Queijo";
                molho = "Maionese";
                break;
            case 4:
                preco = 13.0;
                pao = "Francês";
                recheio = "Carne, Ovo";
                molho = "Maionese";
                break;
            case 5:
                preco = 15.0;
                pao = "Brioche";
                recheio = "Duas Carnes";
                molho = "Barbecue";
                break;
            case 6:
                preco = 14.0;
                pao = "Australiano";
                recheio = "Carne, Calabresa";
                molho = "Maionese";
                break;
            case 7:
                preco = 16.0;
                pao = "Francês";
                recheio = "Carne, Ovo, Calabresa";
                molho = "Maionese";
                break;
            case 8:
                preco = 15.0;
                pao = "Brioche";
                recheio = "Carne, Bacon";
                molho = "Barbecue";
                break;
            case 9:
                preco = 17.0;
                pao = "Francês";
                recheio = "Carne, Ovo, Bacon";
                molho = "Maionese";
                break;
            case 10:
                preco = 18.0;
                pao = "Brioche";
                recheio = "Duas Carnes, Bacon";
                molho = "Barbecue";
                break;
            case 11:
                preco = 17.0;
                pao = "Australiano";
                recheio = "Carne, Bacon, Calabresa";
                molho = "Maionese";
                break;
            case 12:
                preco = 20.0;
                pao = "Francês";
                recheio = "Carne, Ovo, Bacon, Calabresa, Queijo";
                molho = "Maionese, Barbecue";
                break;
            default:
                System.out.println("Opção inválida!");
                return criarItem(scanner); // Recursão para tentar novamente
        }
        
        return new Lanche(preco, pao, recheio, molho);
    }
}