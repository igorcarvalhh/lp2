package meuPacote;

import java.util.Scanner;

public class DiaPessoa implements AtributoPessoa {
    final Scanner scanner;

    public DiaPessoa(Scanner scanner) {
        this.scanner = scanner;
    }

    public void armazenar(DadosPessoa dados, String valor) {
        // [TODO]
    }

    public String ler() {
        while(true) {
            try {
                System.out.print("Dia de nascimento: ");
                String nome = this.scanner.nextLine();

                if (nome.isBlank()) {
                    return null;
                }

                String erro = validaNome(nome);

                if (erro == null) {
                    return nome;
                }
                System.out.println("Dia inválido. " + erro);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Tente novamente.");
            }
        }
    }

    private static String validaNome(String dia) {
        dia = dia.trim();

        if (!dia.matches("^\\d\\d?")) {
            return "Insira um valor entre 01 e 31.";
        }

        int diaNumero = Integer.parseInt(dia);

        if (diaNumero < 1 || diaNumero > 31) {
            return "Insira um valor entre 01 e 31.";
        }

        return null;
    }
}
