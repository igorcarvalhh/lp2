package meuPacote;

import java.util.Scanner;

public class MesPessoa implements AtributoPessoa {
    final Scanner scanner;

    public MesPessoa(Scanner scanner) {
        this.scanner = scanner;
    }

    public void armazenar(DadosPessoa dados, String valor) {
        dados.setNome(valor);
    }

    public String ler() {
        while(true) {
            try {
                System.out.print("Mês de nascimento: ");
                String nome = this.scanner.nextLine();

                if (nome.isBlank()) {
                    return null;
                }

                String erro = validaNome(nome);

                if (erro == null) {
                    return nome;
                }
                System.out.println("Mês inválido. " + erro);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Tente novamente.");
            }
        }
    }

    private static String validaNome(String mes) {
        mes = mes.trim();

        if (mes.matches("^[a-zA-Z].*") && isMes(mes)) {
            return null;
        }

         if (mes.matches("\\d\\d?")) {
             int mesNumero = Integer.parseInt(mes);
             if (mesNumero >= 1 || mesNumero <= 12) {
                 return null;
             }
         }

        return "Mês inválido. Insira um número entre 01 e 12 ou o nome de um mês.";
    }
}
