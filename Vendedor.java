public class Vendedor {
    private String nome;
    private double salario;
    private double bonus;

    public Vendedor(String nome, double salario) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do vendedor não pode ser vazio");
        }
        if (salario < 0) {
            throw new IllegalArgumentException("Salário não pode ser negativo");
        }
        this.nome = nome;
        this.salario = salario;
        this.bonus = 0;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public double getSalario() {
        return salario;
    }

    public double getBonus() {
        return bonus;
    }

    // Setters
    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do vendedor não pode ser vazio");
        }
        this.nome = nome;
    }

    public void setSalario(double salario) {
        if (salario < 0) {
            throw new IllegalArgumentException("Salário não pode ser negativo");
        }
        this.salario = salario;
    }

    public double calcularBonus(double valorVendido) {
        bonus += valorVendido * 0.02;
        return bonus;
    }
}

