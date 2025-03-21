package factory;

import java.util.Scanner;
import model.ItemPedido;
import model.Bebidas;
import util.MenuBuilder;

public class BebidasFactory implements ItemFactory {
    @Override
    public ItemPedido criarItem(Scanner scanner) {
        String[] categorias = {
            "Suco",
            "Refrigerante",
            "Água Mineral"
        };
        
        MenuBuilder.exibirMenu("Adicionar Bebida", categorias, true);
        int categoria = MenuBuilder.lerOpcao(scanner, 0, categorias.length);
        
        if (categoria == 0) {
            return null; // Voltar ao menu principal
        }
        
        double preco = 0;
        String tipo = "";
        String sabor = "";
        String tamanho = "";
        
        switch (categoria) {
            case 1: // Sucos
                String[] saboresSuco = {
                    "Goiaba",
                    "Acerola",
                    "Maracujá",
                    "Abacaxi"
                };
                
                MenuBuilder.exibirMenu("Escolha o sabor do suco", saboresSuco, true);
                int saborSuco = MenuBuilder.lerOpcao(scanner, 0, saboresSuco.length);
                
                if (saborSuco == 0) {
                    return criarItem(scanner); // Voltar à escolha de categoria
                }
                
                tipo = "Suco";
                switch (saborSuco) {
                    case 1: sabor = "de Goiaba"; preco = 4.0; break;
                    case 2: sabor = "de Acerola"; preco = 5.0; break;
                    case 3: sabor = "de Maracujá"; preco = 4.0; break;
                    case 4: sabor = "de Abacaxi"; preco = 4.0; break;
                    default:
                        System.out.println("Sabor inválido!");
                        return criarItem(scanner);
                }
                tamanho = "300ml";
                break;
                
            case 2: // Refrigerantes
                String[] refrigerantes = {
                    "Coca-Cola",
                    "Guaraná"
                };
                
                MenuBuilder.exibirMenu("Escolha o refrigerante", refrigerantes, true);
                int tipoRefri = MenuBuilder.lerOpcao(scanner, 0, refrigerantes.length);
                
                if (tipoRefri == 0) {
                    return criarItem(scanner); // Voltar à escolha de categoria
                }
                
                String[] tamanhosRefri = {
                    "Lata (350ml)",
                    "Garrafa (1L)",
                    "Garrafa (2L)"
                };
                
                MenuBuilder.exibirMenu("Escolha o tamanho", tamanhosRefri, true);
                int tamanhoRefri = MenuBuilder.lerOpcao(scanner, 0, tamanhosRefri.length);
                
                if (tamanhoRefri == 0) {
                    return criarItem(scanner); // Voltar à escolha de categoria
                }
                
                tipo = "Refrigerante";
                switch (tipoRefri) {
                    case 1: sabor = "Coca-Cola"; break;
                    case 2: sabor = "Guaraná"; break;
                    default:
                        System.out.println("Refrigerante inválido!");
                        return criarItem(scanner);
                }
                
                switch (tamanhoRefri) {
                    case 1: tamanho = "Lata (350ml)"; preco = 4.0; break;
                    case 2: tamanho = "Garrafa (1L)"; preco = 8.0; break;
                    case 3: tamanho = "Garrafa (2L)"; preco = 12.0; break;
                    default:
                        System.out.println("Tamanho inválido!");
                        return criarItem(scanner);
                }
                break;
                
            case 3: // Água Mineral
                String[] tamanhosAgua = {
                    "Garrafa (250ml)",
                    "Garrafa (500ml)",
                    "Garrafa (2L)"
                };
                
                MenuBuilder.exibirMenu("Escolha o tamanho da água mineral", tamanhosAgua, true);
                int tamanhoAgua = MenuBuilder.lerOpcao(scanner, 0, tamanhosAgua.length);
                
                if (tamanhoAgua == 0) {
                    return criarItem(scanner); // Voltar à escolha de categoria
                }
                
                tipo = "Água Mineral";
                sabor = "Natural";
                
                switch (tamanhoAgua) {
                    case 1: tamanho = "Garrafa (250ml)"; preco = 2.0; break;
                    case 2: tamanho = "Garrafa (500ml)"; preco = 4.0; break;
                    case 3: tamanho = "Garrafa (2L)"; preco = 6.0; break;
                    default:
                        System.out.println("Tamanho inválido!");
                        return criarItem(scanner);
                }
                break;
                
            default:
                System.out.println("Categoria inválida!");
                return criarItem(scanner);
        }
        
        return new Bebidas(preco, tipo, sabor, tamanho);
    }
}
