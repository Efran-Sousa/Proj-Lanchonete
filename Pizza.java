
import java.util.Date;

public class Pizza extends Prato {
    private String recheio;
    private String borda;
    private String molho;

    public Pizza(double precoVenda, Date dataValidade, double peso, String recheio, String borda, String molho) {
        super(precoVenda, dataValidade, peso);
        
        if (recheio == null || recheio.trim().isEmpty()) {
            throw new IllegalArgumentException("Recheio não pode ser vazio");
        }
        if (borda == null || borda.trim().isEmpty()) {
            throw new IllegalArgumentException("Borda não pode ser vazia");
        }
        if (molho == null || molho.trim().isEmpty()) {
            throw new IllegalArgumentException("Molho não pode ser vazio");
        }
        
        this.recheio = recheio;
        this.borda = borda;
        this.molho = molho;
    }

    // Getters
    public String getRecheio() {
        return recheio;
    }

    public String getBorda() {
        return borda;
    }

    public String getMolho() {
        return molho;
    }

    // Setters
    public void setRecheio(String recheio) {
        if (recheio == null || recheio.trim().isEmpty()) {
            throw new IllegalArgumentException("Recheio não pode ser vazio");
        }
        this.recheio = recheio;
    }

    public void setBorda(String borda) {
        if (borda == null || borda.trim().isEmpty()) {
            throw new IllegalArgumentException("Borda não pode ser vazia");
        }
        this.borda = borda;
    }

    public void setMolho(String molho) {
        if (molho == null || molho.trim().isEmpty()) {
            throw new IllegalArgumentException("Molho não pode ser vazio");
        }
        this.molho = molho;
    }

    @Override
    public String descricao() {
        return String.format("Pizza de %s com borda de %s e molho de %s", 
                           recheio, borda, molho);
    }
}
