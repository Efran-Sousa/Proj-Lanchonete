import java.util.Scanner;

public class Lanche extends Prato {
    private String pao;
    private String recheio;
    private String molho;

    public Lanche(double precoVenda, String pao, String recheio, String molho) {
        super(precoVenda);
        
        if (pao == null || pao.trim().isEmpty()) {
            throw new IllegalArgumentException("Tipo de pão não pode ser vazio");
        }
        if (recheio == null || recheio.trim().isEmpty()) {
            throw new IllegalArgumentException("Recheio não pode ser vazio");
        }
        if (molho == null || molho.trim().isEmpty()) {
            throw new IllegalArgumentException("Molho não pode ser vazio");
        }
        
        this.pao = pao;
        this.recheio = recheio;
        this.molho = molho;
    }

    // Getters
    public String getPao() {
        return pao;
    }

    public String getRecheio() {
        return recheio;
    }

    public String getMolho() {
        return molho;
    }

    // Setters
    public void setPao(String pao) {
        if (pao == null || pao.trim().isEmpty()) {
            throw new IllegalArgumentException("Tipo de pão não pode ser vazio");
        }
        this.pao = pao;
    }

    public void setRecheio(String recheio) {
        if (recheio == null || recheio.trim().isEmpty()) {
            throw new IllegalArgumentException("Recheio não pode ser vazio");
        }
        this.recheio = recheio;
    }

    public void setMolho(String molho) {
        if (molho == null || molho.trim().isEmpty()) {
            throw new IllegalArgumentException("Molho não pode ser vazio");
        }
        this.molho = molho;
    }

    @Override
    public String descricao() {
        return "Lanche de " + recheio + " no pão " + pao + " com molho de " + molho;
    }

    public static Lanche chamarLanche(Scanner scanner) {
        System.out.println("\n=== Adicionar Lanche ===");
        System.out.println("Escolha o lanche:");
        System.out.println("1. Misto");
        System.out.println("2. Hambúrguer");
        System.out.println("3. X-Burger");
        System.out.println("4. X-Eggs");
        System.out.println("5. Duplo");
        System.out.println("6. X-Calabresa");
        System.out.println("7. X-Eggs Calabresa");
        System.out.println("8. X-Bacon");
        System.out.println("9. X-Eggs Bacon");
        System.out.println("10. X-Duplo com bacon");
        System.out.println("11. X-Bacon com Calabresa");
        System.out.println("12. X-Tudo");
        System.out.print("Digite o número da opção desejada: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        
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
                return null;
        }
        
        return new Lanche(preco, pao, recheio, molho);
    }
}