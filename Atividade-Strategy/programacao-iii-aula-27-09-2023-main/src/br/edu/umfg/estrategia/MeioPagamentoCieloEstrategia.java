package br.edu.umfg.estrategia;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;

public class MeioPagamentoCieloEstrategia implements MeioPagamentoEstrategia {

    private String numeroCartao;
    private String cpf;
    private String cvv;
    private String dataValidade;
    private String tipoCartao;

    public MeioPagamentoCieloEstrategia(String numeroCartao, String cpf, String cvv, String dataValidade, String tipoCartao) {
        this.numeroCartao = numeroCartao;
        this.cpf = cpf;
        this.cvv = cvv;
        this.dataValidade = dataValidade;
        this.tipoCartao = tipoCartao;
    }

    @Override
    public void pagar(Double valor) {
        System.out.printf("Pagamento via Credito Cielo no valor," + valor + ", realizado com sucesso!");
    }

    public static boolean validarCartao(String numeroCartao) {
        if (!numeroCartao.matches("\\d{13,19}")) {
            return false;
        }
        // Algoritmo de Luhn
        int soma = 0;
        boolean alternar = false;
        for (int i = numeroCartao.length() - 1; i >= 0; i--) {
            int digito = Character.getNumericValue(numeroCartao.charAt(i));
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

    public static boolean validarCPF(String CPF) {
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") ||
                CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999") ||
                (CPF.length() != 11))
            return (false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char) (r + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char) (r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                return (true);
            else return (false);
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    public boolean validarCVV(String CVV) {
        if (CVV.length() == 3) {
            return true;
        }
        return false;
    }

    public static boolean validarDataValidade(String dataValidade) {
        try {
            // Define o formato esperado para a data de validade (MM/yy)
            DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("MM/yy");

            // Faz o parsing da data fornecida
            YearMonth data = YearMonth.parse(dataValidade, formatoData);

            // Obtém o YearMonth atual
            YearMonth dataAtual = YearMonth.now();

            // Verifica se a data de validade é uma data futura
            return data.isAfter(dataAtual);
        } catch (DateTimeParseException e) {
            // A data fornecida não está no formato correto
            return false;
        }
    }

    public void validarDadosCredito() {
        if (validarCPF(cpf)) {
            if (validarCartao(numeroCartao)) {
                if (validarCVV(cvv)) {
                    if (validarDataValidade(dataValidade)) {
                        System.out.println("* Todos os dados são validos *");

                    } else {
                        System.out.println("* Data de validade Inválida *");
                        System.exit(0);
                    }
                } else {
                    System.out.println("* CVV inválido, apenas 3 digitos *");
                    System.exit(0);
                }
            } else {
                System.out.println("* Numero do Cartão inválido *");
                System.exit(0);
            }
        } else {
            System.out.println("* CPF INVÁLIDO *");
            System.exit(0);
        }
    }

    public void validarDadosDebito() {
        if (validarCPF(cpf)) {
            if (validarCartao(numeroCartao)) {
                if (validarCVV(cvv)) {
                    if (validarDataValidade(dataValidade)) {
                        System.out.println("* Todos os dados são validos *");

                    } else {
                        System.out.println("* Data de validade Inválida *");
                        System.exit(0);
                    }
                } else {
                    System.out.println("* CVV inválido (apenas 3 digitos) *");
                    System.exit(0);
                }
            } else {
                System.out.println("* Numero do Cartão inválido *");
                System.exit(0);
            }
        } else {
            System.out.println("* CPF INVÁLIDO *");
            System.exit(0);
        }
    }

}


