package meuPacote;

import java.util.Scanner;

public class NumCPFPessoa implements AtributoPessoa {
    final Scanner scanner;

    public NumCPFPessoa(Scanner scanner) {
        this.scanner = scanner;
    }

    public void armazenar(DadosPessoa dados, String valor) {
        dados.setNumCPF(valor);
    }

    public String ler() {
        while(true) {
            try {
                System.out.print("Insira o CPF: ");
                String nome = this.scanner.nextLine();

                if (nome.isBlank()) {
                    return null;
                }

                String erro = validaCPF(nome);

                if (erro == null) {
                    return nome;
                }
                System.out.println("CPF incorreto. " + erro);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Tente novamente.");
            }
        }
    }

    private static String validaCPF(String cpf) {
        if (!ValidaCPF.isCPF(cpf)) {
            return "Esse CPF não existe.";
        }
        return null;
    }
}
