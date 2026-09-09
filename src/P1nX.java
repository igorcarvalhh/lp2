import meuPacote.*;

import java.util.Scanner;

public class P1nX {
    public static void main(String[] args) {

        DadosPessoa dados = interpretarArgumentos(args);

        if (dados == null) {
            exibirDocumentacao();
            return;
        }

        Pessoa pessoa = criarPessoa(dados);
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

        DadosPessoa dados = new DadosPessoa();

        dados.setNome(args[1]);
        dados.setSobreNome(args[2]);
        dados.setDia(Integer.parseInt(args[3]));
        dados.setMes(Mes.obterMes(args[4]).getNumero());
        dados.setAno(Integer.parseInt(args[5]));
        dados.setNumCPF(args[6]);
        dados.setPeso(Float.parseFloat(args[7]));
        dados.setAltura(Float.parseFloat(args[8]));

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
        if (args[1].isBlank()) {
            System.out.println("Nome não pode ser vazio.");
            return false;
        }

        // Sobrenome
        if (args[2].isBlank()) {
            System.out.println("Sobrenome não pode ser vazio.");
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

        // CPF
        if (!ValidaCPF.isCPF(args[6])) {
            System.out.println("CPF inválido: " + args[6]);
            return false;
        }

        // Peso
        try {
            float peso = Float.parseFloat(args[7]);

            if (peso <= 0) {
                System.out.println("Peso inválido: " + args[7]);
                return false;
            }

        } catch (NumberFormatException e) {
            System.out.println("Peso inválido: " + args[7]);
            return false;
        }

        // Altura
        try {
            float altura = Float.parseFloat(args[8]);

            if (altura <= 0) {
                System.out.println("Altura inválida: " + args[8]);
                return false;
            }

        } catch (NumberFormatException e) {
            System.out.println("Altura inválida: " + args[8]);
            return false;
        }

        return true;
    }

    private static Pessoa(DadosPessoa dados) {
        try {
            // exiba as informações do objeto criado usando o metodo toString()
            if (args[0].equalsIgnoreCase("m")) {
                pessoa = new Homem(args[1], args[2], args[3], args[4], args[5], args[6], Float.parseFloat(args[7]), Float.parseFloat(args[8]));
            } else {
                pessoa = new Mulher(args[1], args[2], args[3], args[4], args[5], args[6], Float.parseFloat(args[7]), Float.parseFloat(args[8]));
            }

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            exibirDocumentacao();
            return;
        }
    }

    private static Pessoa[] lerPessoas(Scanner scanner, int qtdPessoas) {
        new Pessoa[qtdPessoas];

        for (int i = 0; i < qtdPessoas; i++) {
            Pessoa novaPessoa = lerPessoa(ler);

            if (novaPessoa == null) {
                break;
            }

            pessoas[i] = novaPessoa;
        }
    }

    private static void exibirDocumentacao() {
        System.out.println("""
                Uso: java P1nX <genero> <nome> <sobrenome> <dia> <mes> <ano> <CPF> <peso> <altura>
        
                    <genero>      m | f
                    <nome>        nome
                    <sobrenome>   sobrenome
                    <dia>         1-31
                    <mes>         1-12 | nome do mês
                    <ano>         AAAA
                    <CPF>         CPF válido
                    <peso>        peso em kg
                    <altura>      altura em metros
        
                Exemplo:
                    java P1nX m Joao Silva 10 05 2003 12345678909 70.0 1.78
                """);

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

                if (genero.equals("m")) {
                    return new Homem(dadosPessoa);
                }

                if (genero.equals("f")) {
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
