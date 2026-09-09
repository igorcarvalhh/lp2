package meuPacote;

import java.util.Scanner;

public class NomePessoa implements AtributoPessoa {
    final Scanner scanner;

    public NomePessoa(Scanner scanner) {
        this.scanner = scanner;
    }

    public void armazenar(DadosPessoa dados, String valor) {
        dados.setNome(valor);
    }

    public String ler() {
        while(true) {
            try {
                System.out.print("Insira o nome: ");
                String nome = this.scanner.nextLine();

                if (nome.isBlank()) {
                    return null;
                }

                String erro = validaNome(nome);

                if (erro == null) {
                    return nome;
                }
                System.out.println("Nome inválido. " + erro);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Tente novamente.");
            }
        }
    }

    private static String validaNome(String nome) {
        if (!nome.matches("[a-zA-ZÀ-ÿ ]+")) {
            return "O nome não deve conter números ou caracteres especiais.";
        }

        if (nome.length() < 2) {
            return "O nome deve possuir pelo menos 2 caracteres.";
        }

        return null;
    }
}
