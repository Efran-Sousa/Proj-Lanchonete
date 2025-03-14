import java.util.Scanner;

public class Salgadinho extends Prato {
    private String tipo;
    private String massa;
    private String recheio;

    public Salgadinho(double precoVenda, String tipo, String massa, String recheio) {
        super(precoVenda);
        
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("Tipo não pode ser vazio");
        }
        if (massa == null || massa.trim().isEmpty()) {
            throw new IllegalArgumentException("Massa não pode ser vazia");
        }
        if (recheio == null || recheio.trim().isEmpty()) {
            throw new IllegalArgumentException("Recheio não pode ser vazio");
        }
        
        this.tipo = tipo;
        this.massa = massa;
        this.recheio = recheio;
    }

    // Getters
    public String getTipo() {
        return tipo;
    }

    public String getMassa() {
        return massa;
    }

    public String getRecheio() {
        return recheio;
    }

    // Setters
    public void setTipo(String tipo) {
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("Tipo não pode ser vazio");
        }
        this.tipo = tipo;
    }

    public void setMassa(String massa) {
        if (massa == null || massa.trim().isEmpty()) {
            throw new IllegalArgumentException("Massa não pode ser vazia");
        }
        this.massa = massa;
    }

    public void setRecheio(String recheio) {
        if (recheio == null || recheio.trim().isEmpty()) {
            throw new IllegalArgumentException("Recheio não pode ser vazio");
        }
        this.recheio = recheio;
    }

    @Override
    public String descricao() {
        return tipo +" "+ massa + " com recheio de " + recheio;
    }

    public static Salgadinho chamarSalgadinho(Scanner scanner) {
        System.out.println("\n=== Adicionar Salgadinho ===");
        System.out.println("Escolha o salgadinho:");
        System.out.println("1. Coxinha de Carne");
        System.out.println("2. Coxinha de Frango");
        System.out.println("3. Pastel Frito de Carne");
        System.out.println("4. Pastel Frito de Frango");
        System.out.println("5. Pastel Assado de Carne");
        System.out.println("6. Pastel Assado de Frango");
        System.out.println("7. Empanado de Frango");
        System.out.print("Digite o número da opção desejada: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        
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
                return null;
        }
        
        return new Salgadinho(preco, tipo, massa, recheio);
    }
}
