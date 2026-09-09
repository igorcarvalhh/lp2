package meuPacote;

import java.util.Scanner;

public class AnoPessoa implements AtributoPessoa {
    final Scanner scanner;

    public AnoPessoa(Scanner scanner) {
        this.scanner = scanner;
    }

    public void armazenar(DadosPessoa dados, String valor) {
        dados.setNome(valor);
    }

    public String ler() {
        while(true) {
            try {
                System.out.print("Ano de nascimento: ");
                String nome = this.scanner.nextLine();

                if (nome.isBlank()) {
                    return null;
                }

                String erro = validaNome(nome);

                if (erro == null) {
                    return nome;
                }
                System.out.println("Ano inválido. " + erro);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Tente novamente.");
            }
        }
    }

    private static String validaNome(String nome) {
        return null;
    }
}
