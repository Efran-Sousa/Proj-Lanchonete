package util;

public class FormatadorMoeda {
    public static String formatar(double valor) {
        return String.format("R$ %.2f", valor);
    }
}