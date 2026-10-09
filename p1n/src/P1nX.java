import meuPacote.entrada.*;
import meuPacote.modelo.*;
import meuPacote.validacao.*;

import java.util.Scanner;

public class P1nX {
    public static void main(String[] args) {

        DadosPessoa dados = interpretarArgumentos(args);

        if (dados == null) {
            exibirDocumentacao();
            return;
        }

        Pessoa pessoa = criarPessoa(dados, args[0]);

        if (pessoa == null) {
            exibirDocumentacao();
            return;
        }

        System.out.println("\n" + pessoa);

        Scanner scanner = new Scanner(System.in);

        int qtdPessoas = lerQtdPessoas(scanner);
        Pessoa[] pessoas = lerPessoas(scanner, qtdPessoas);

        exibirPessoas(pessoas);

        scanner.close();
    }

    private static DadosPessoa interpretarArgumentos(String[] args) {

        if (!argumentosValidos(args)) {
            return null;
        }

        DadosPessoa dados = new DadosPessoa(null);

        dados.setNome(args[1]);
        dados.setSobreNome(args[2]);
        dados.setDia(args[3]);
        dados.setMes(String.valueOf(Mes.obterMes(args[4]).getNumero()));
        dados.setAno(args[5]);
        dados.setNumCPF(args[6]);
        dados.setPeso(Pessoa.parseDecimal(args[7]));
        dados.setAltura(Pessoa.parseDecimal(args[8]));

        return dados;
    }

    private static boolean argumentosValidos(String[] args) {

        // Quantidade de argumentos
        if (args.length != 9) {
            System.out.println(
                    "Informações faltando. Por favor, preencha todos os dados."
            );
            return false;
        }

        // Gênero
        if (!args[0].equalsIgnoreCase("m")
                && !args[0].equalsIgnoreCase("f")) {

            System.out.println(
                    "Gênero inválido: " + args[0] + ". Use m ou f."
            );
            return false;
        }

        // Nome
        String erroNome = Pessoa.validaNome(args[1]);
        if (erroNome != null) {
            System.out.println("Nome inválido: " + args[1] + ". " + erroNome);
            return false;
        }

        // Sobrenome 
        String erroSobreNome = Pessoa.validaNome(args[2]);
        if (erroSobreNome != null) {
            System.out.println("Sobrenome inválido: " + args[2] + ". " + erroSobreNome);
            return false;
        }

        // Dia
        if (!ValidaData.isDia(args[3])) {
            System.out.println("Dia inválido: " + args[3]);
            return false;
        }

        // Mês
        if (!ValidaData.isMes(args[4])) {
            System.out.println("Mês inválido: " + args[4]);
            return false;
        }

        // Ano
        if (!ValidaData.isAno(args[5])) {
            System.out.println("Ano inválido: " + args[5]);
            return false;
        }

        // Data completa
        // [TODO] Passar logica de validação de data para a classe Pessoa
        // String erroData = Pessoa.validaData(args[3], args[4], args[5]);
        // ...("Data invalida: " + erroData);
        if (!ValidaData.isDataValida(args[3], args[4], args[5])) {
            String mes = String.format("%02d", Mes.obterMes(args[4]).getNumero());
            System.out.println(
                    "Data inválida: " + args[3] + "/" + mes + "/" + args[5]
            );
            return false;
        }

        // CPF
        if (!ValidaCPF.isCPF(args[6])) {
            System.out.println("CPF inválido: " + args[6]);
            return false;
        }

        // Peso 
        String erroPeso = Pessoa.validaPeso(args[7]);
        if (erroPeso != null) {
            System.out.println("Peso inválido: " + args[7] + ". " + erroPeso);
            return false;
        }

        // Altura 
        String erroAltura = Pessoa.validaAltura(args[8]);
        if (erroAltura != null) {
            System.out.println("Altura inválida: " + args[8] + ". " + erroAltura);
            return false;
        }

        return true;
    }

    private static Pessoa criarPessoa(DadosPessoa dados, String genero) {
        try {
            if (genero.equalsIgnoreCase("m")) {
                return new Homem(dados);
            } else {
                return new Mulher(dados);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static Pessoa[] lerPessoas(Scanner scanner, int qtdPessoas) {
        Pessoa[] pessoas = new Pessoa[qtdPessoas];

        for (int i = 0; i < qtdPessoas; i++) {
            Pessoa novaPessoa = lerPessoa(scanner);

            if (novaPessoa == null) {
                break;
            }

            pessoas[i] = novaPessoa;
        }

        return pessoas;
    }

    private static void exibirDocumentacao() {
        System.out.printf("""
                Uso: java P1nX <genero> <nome> <sobrenome> <dia> <mes> <ano> <CPF> <peso> <altura>

                    <genero>      m | f (masculino ou feminino)
                    <nome>        somente letras/acentos e espaço, mínimo de 2 caracteres
                    <sobrenome>   somente letras/acentos e espaço, mínimo de 2 caracteres
                    <dia>         1 a 31, precisa existir no <mes>/<ano> informado
                    <mes>         1 a 12, ou nome do mês por extenso em português sem cedilha
                                  (janeiro, fevereiro, marco, abril, ..., dezembro)
                    <ano>         %d a %d (de 120 anos atrás até o ano atual)
                    <CPF>         com ou sem pontuação, ex: 12345678901, 123.456.789-01
                                  ou 123.456.789/01; dígitos verificadores válidos
                    <peso>        em kg, entre 0.5 e 300
                    <altura>      em metros, entre 0.3 e 2.8

                Exemplo:
                    java P1nX m Joao Silva 10 05 2003 12345678909 70.0 1.78
                """, ValidaData.anoMinimo(), ValidaData.anoMaximo());

        return;
    }

    private static int lerQtdPessoas(Scanner scanner) {
        while(true) {
            try {
                System.out.print("Quantas pessoas a mais deseja inserir? ");
                int qtdPessoas = Integer.parseInt(scanner.nextLine());
                if (qtdPessoas >= 0) {
                    System.out.println();
                    return qtdPessoas;
                }
                System.out.println("Quantidade inválida! Insira um número positivo.");
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Tente novamente.");
            }
        }
    }

    private static void exibirPessoas(Pessoa[] pessoas) {
        System.out.println("-----------------------------------------------------------");
        System.out.println("Informações inseridas:");

        int numHomens = 0;
        int listSize = Pessoa.numPessoas() - 1;
        for (int i = 0; i < listSize; i++) {
            if (pessoas[i] instanceof Homem) {
                numHomens++;
            }
            System.out.println(pessoas[i]);
        }

        System.out.println("Número de homens: " + numHomens);
        System.out.println("Número de mulheres: " + (listSize - numHomens));
    }

    private static Pessoa lerGenero(Scanner scanner, DadosPessoa dadosPessoa) {
        while(true) {
            try {
                System.out.print("Esta pessoa é do gênero feminino ou masculino (f ou m)? ");
                String genero = scanner.nextLine();

                if (genero.isBlank()) {
                    return null;
                }

                if (genero.equalsIgnoreCase("m")) {
                    return new Homem(dadosPessoa);
                }

                if (genero.equalsIgnoreCase("f")) {
                    return new Mulher(dadosPessoa);
                }

                System.out.println("Gênero inválido! Insira um dos valore (f ou m).");
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Tente novamente.");
            }
        }
    }

    private static Pessoa lerPessoa(Scanner scanner) {

        DadosPessoa dadosPessoa = new DadosPessoa(scanner);

        for (AtributoPessoa atributo : dadosPessoa.getAtributos()) {
            String valor = atributo.ler();

            if (valor == null) {
                return null;
            }

            atributo.armazenar(dadosPessoa, valor);
        }

        return lerGenero(scanner, dadosPessoa);
    }
}
