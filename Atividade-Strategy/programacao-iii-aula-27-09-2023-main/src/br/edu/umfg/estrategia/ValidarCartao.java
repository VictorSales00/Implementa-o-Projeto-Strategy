package br.edu.umfg.estrategia;

public class ValidarCartao {
    public static boolean validarCartaoCredito(String numeroDoCartao) {

        if (!numeroDoCartao.matches("\\d{13,19}")) {
            return false;
        }

        // Algoritmo de Luhn
        int soma = 0;
        boolean alternar = false;
        for (int i = numeroDoCartao.length() - 1; i >= 0; i--) {
            int digito = Character.getNumericValue(numeroDoCartao.charAt(i));
            if (alternar) {
                digito *= 2;
                if (digito > 9) {
                    digito -= 9;
                }
            }
            soma += digito;
            alternar = !alternar;
        }

        return soma % 10 == 0;
    }

}