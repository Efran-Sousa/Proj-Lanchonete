import java.util.Date;

public class Lanche extends Prato {
    private String pao;
    private String recheio;
    private String molho;

    public Lanche(double precoVenda, Date dataValidade, double peso, String pao, String recheio, String molho) {
        super(precoVenda, dataValidade, peso);
        
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
}