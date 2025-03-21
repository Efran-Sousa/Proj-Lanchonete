package util;

import java.util.Scanner;

public class MenuBuilder {
    public static void exibirMenu(String titulo, String[] opcoes, boolean mostrarVoltar) {
        System.out.println("\n=== " + titulo + " ===");
        for (int i = 0; i < opcoes.length; i++) {
            System.out.println((i + 1) + ". " + opcoes[i]);
        }
        if (mostrarVoltar) {
            System.out.println("0. Voltar");
        }
        System.out.print("Escolha uma opção: ");
    }
    
    public static int lerOpcao(Scanner scanner, int min, int max) {
        int opcao;
        try {
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer
            
            if (opcao < min || opcao > max) {
                System.out.println("Opção inválida! Escolha entre " + min + " e " + max);
                return lerOpcao(scanner, min, max);
            }
        } catch (Exception e) {
            System.out.println("Entrada inválida! Digite um número.");
            scanner.nextLine(); // Limpar buffer
            return lerOpcao(scanner, min, max);
        }
        return opcao;
    }
}