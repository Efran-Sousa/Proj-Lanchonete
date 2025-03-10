import java.util.Date;

public class Salgadinho extends Prato {
    private String tipo;
    private String massa;
    private String recheio;

    public Salgadinho(double precoVenda, Date dataValidade, double peso, String tipo, String massa, String recheio) {
        super(precoVenda, dataValidade, peso);
        
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
        return "Salgadinho " + tipo + " de " + massa + " com recheio de " + recheio;
    }
}
