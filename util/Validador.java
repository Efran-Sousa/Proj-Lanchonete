package util;

public class Validador {
    public static void validarString(String valor, String mensagemErro) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(mensagemErro);
        }
    }
    
    public static void validarNumero(double valor, double minimo, String mensagemErro) {
        if (valor < minimo) {
            throw new IllegalArgumentException(mensagemErro);
        }
    }
}