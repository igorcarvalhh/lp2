package meuPacote;

import java.util.Scanner;

public class SobreNomePessoa implements AtributoPessoa {
    final Scanner scanner;
    final String mensagem = "Insira o sobrenome: ";

    public SobreNomePessoa(Scanner scanner) {
        this.scanner = scanner;
    }

    public void armazenar(DadosPessoa dados, String valor) {
        dados.setSobreNome(valor);
    }

    public String ler() {
        while(true) {
            try {
                System.out.print(this.mensagem);
                String nome = this.scanner.nextLine();

                if (nome.isBlank()) {
                    return null;
                }

                String erro = validaNome(nome);

                if (erro == null) {
                    return nome;
                }
                System.out.println("Sobrenome inválido. " + erro);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Tente novamente.");
            }
        }
    }

    private static String validaNome(String nome) {
        if (!nome.matches("[a-zA-ZÀ-ÿ ]+")) {
            return "O sobrenome não deve conter números ou caracteres especiais.";
        }

        if (nome.length() < 2) {
            return "O sobrenome deve possuir pelo menos 2 caracteres.";
        }

        return null;
    }
}
