package factory;

import java.util.Scanner;
import model.ItemPedido;
import model.Salgadinho;
import util.MenuBuilder;

public class SalgadinhoFactory implements ItemFactory {
    @Override
    public ItemPedido criarItem(Scanner scanner) {
        String[] opcoes = {
            "Coxinha de Carne",
            "Coxinha de Frango",
            "Pastel Frito de Carne",
            "Pastel Frito de Frango",
            "Pastel Assado de Carne",
            "Pastel Assado de Frango",
            "Empanado de Frango"
        };
        
        MenuBuilder.exibirMenu("Adicionar Salgadinho", opcoes, true);
        int opcao = MenuBuilder.lerOpcao(scanner, 0, opcoes.length);
        
        if (opcao == 0) {
            return null; // Voltar ao menu principal
        }
        
        double preco = 0;
        String tipo = "";
        String massa = "";
        String recheio = "";
        
        switch (opcao) {
            case 1:
                preco = 3.0;
                tipo = "Coxinha";
                massa = "Frita";
                recheio = "Carne";
                break;
            case 2:
                preco = 3.0;
                tipo = "Coxinha";
                massa = "Frita";
                recheio = "Frango";
                break;
            case 3:
                preco = 4.0;
                tipo = "Pastel";
                massa = "Frito";
                recheio = "Carne";
                break;
            case 4:
                preco = 4.0;
                tipo = "Pastel";
                massa = "Frito";
                recheio = "Frango";
                break;
            case 5:
                preco = 5.0;
                tipo = "Pastel";
                massa = "Assado";
                recheio = "Carne";
                break;
            case 6:
                preco = 5.0;
                tipo = "Pastel";
                massa = "Assado";
                recheio = "Frango";
                break;
            case 7:
                preco = 6.0;
                tipo = "Empanado";
                massa = "Frito";
                recheio = "Frango";
                break;
            default:
                System.out.println("Opção inválida!");
                return criarItem(scanner);
        }
        
        return new Salgadinho(preco, tipo, massa, recheio);
    }
}
