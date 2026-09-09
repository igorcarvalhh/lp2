package meuPacote;

import java.util.Scanner;

public class PesoPessoa implements AtributoPessoa {
    final Scanner scanner;

    public PesoPessoa(Scanner scanner) {
        this.scanner = scanner;
    }

    public void armazenar(DadosPessoa dados, String valor) {
        dados.setPeso(75);
    }

    public String ler() {
        while(true) {
            try {
                System.out.print("Insira o peso: ");
                String nome = this.scanner.nextLine();

                if (nome.isBlank()) {
                    return null;
                }

                String erro = validaNome(nome);

                if (erro == null) {
                    return nome;
                }
                System.out.println("Peso inválido. " + erro);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Tente novamente.");
            }
        }
    }

    private static String validaNome(String nome) {
        return null;
    }
}
