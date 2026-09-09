package meuPacote;

import java.util.Scanner;

public class AlturaNomePessoa implements AtributoPessoa {
    final Scanner scanner;

    public AlturaNomePessoa(Scanner scanner) {
        this.scanner = scanner;
    }

    public void armazenar(DadosPessoa dados, String valor) {
        dados.setAltura(175);
    }

    public String ler() {
        while(true) {
            try {
                System.out.print("Insira o altura: ");
                String nome = this.scanner.nextLine();

                if (nome.isBlank()) {
                    return null;
                }

                String erro = validaNome(nome);

                if (erro == null) {
                    return nome;
                }
                System.out.println("Altura inválida. " + erro);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Tente novamente.");
            }
        }
    }

    private static String validaNome(String nome) {
        return null;
    }
}
