package meuPacote.modelo;

import meuPacote.entrada.DadosPessoa;
import meuPacote.validacao.ValidaCPF;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/*
 * P2n: os campos peso e altura, seus get/set e sua exibicao no toString
 * foram movidos para PessoaIMC. Os validadores de peso e altura tambem
 * foram para PessoaIMC, pois so fazem sentido junto desses campos.
 */
public class Pessoa {
    private String nome;
    private String sobreNome;
    private GregorianCalendar dataNasc;
    private String numCPF;
    private static int countPessoas = 0;

    public Pessoa(String nome, String sobreNome, String dia, String mes, String ano, String numCPF) {
        if (numCPF != null && !ValidaCPF.isCPF(numCPF)) {
            throw new IllegalArgumentException("CPF inválido");
        }

        this.nome = nome;
        this.sobreNome = sobreNome;
        // O CPF e armazenado apenas com os 11 digitos (requisito do P1n).
        this.numCPF = numCPF == null ? null : numCPF.replaceAll("[^\\d]", "");
        this.dataNasc = new GregorianCalendar(Integer.parseInt(ano), Integer.parseInt(mes) - 1, Integer.parseInt(dia));

        countPessoas++;
    }

    public Pessoa(String nome, String sobreNome, String dia, String mes, String ano) {
        this(nome, sobreNome, dia, mes, ano, null);
    }

    public Pessoa(DadosPessoa dados) {
        this(
                dados.nome,
                dados.sobreNome,
                dados.dia,
                dados.mes,
                dados.ano,
                dados.numCPF
                );
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobreNome() {
        return this.sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public String getDataNasc() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(this.dataNasc.getTime());
    }

    // Devolve uma copia da data para que a ordenacao possa compara-la
    // sem expor o objeto interno (encapsulamento).
    public GregorianCalendar getDataNascCalendar() {
        return (GregorianCalendar) this.dataNasc.clone();
    }

    public int getIdade() {
        GregorianCalendar nascimento = this.dataNasc;

        GregorianCalendar hoje = new GregorianCalendar();

        int idade = hoje.get(Calendar.YEAR) - nascimento.get(Calendar.YEAR);

        if (hoje.get(Calendar.MONTH) < nascimento.get(Calendar.MONTH) ||
                (hoje.get(Calendar.MONTH) == nascimento.get(Calendar.MONTH) &&
                        hoje.get(Calendar.DAY_OF_MONTH) < nascimento.get(Calendar.DAY_OF_MONTH))) {
            idade--;
        }

        return idade;
    }

    public String getNumCPF() {
        return this.numCPF;
    }

    public void setNumCPF(String numCPF) {
        if (ValidaCPF.isCPF(numCPF)) {
            this.numCPF = numCPF.replaceAll("[^\\d]", "");
        } else {
            System.out.println("CPF inválido.");
        }
    }

    public static String lerNome(Scanner scanner) {
        while(true) {
            try {
                System.out.print("Insira o nome: ");
                String nome = scanner.nextLine();

                String erro = validaNome(nome);

                if (erro == null) {
                    return nome;
                }
                System.out.println("Nome inválido. " + erro + ".");
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Tente novamente.");
            }
        }
    }

    public static String validaNome(String nome) {
        if (nome == null || nome.isBlank()) {
            return "O nome não pode ser vazio.";
        }

        if (nome.length() < 2) {
            return "O nome deve possuir pelo menos 2 caracteres.";
        }

        if (!nome.matches("[a-zA-ZÀ-ÿ ]+")) {
            return "O nome não deve conter números ou caracteres especiais.";
        }

        return null;
    }

    public static float parseDecimal(String valor) {
        return Float.parseFloat(valor.trim().replace(",", "."));
    }

    public void setDia(String dia) {
        this.dataNasc.set(GregorianCalendar.DAY_OF_MONTH, Integer.parseInt(dia));
    }
    public void setMes(String mes) {
        this.dataNasc.set(GregorianCalendar.MONTH, Integer.parseInt(mes) - 1);
    }
    public void setAno(String ano) {
        this.dataNasc.set(GregorianCalendar.YEAR, Integer.parseInt(ano));
    }

    /*
     * O genero e obtido pela propria classe do objeto (getClass), e nao por
     * um campo rotulo: um objeto Homem mostra "(Homem)" e um Mulher "(Mulher)".
     */
    @Override
    public String toString() {
        String cpf = this.numCPF == null ? "não informado" : ValidaCPF.imprimeCPF(this.numCPF);

        return "Nome: " + this.nome + " " + this.sobreNome + " (" + getClass().getSimpleName() + ")\n" +
                "Data de Nascimento: " + getDataNasc() + '\n' +
                "CPF: " + cpf + '\n' +
                "Idade: " + getIdade() + " anos\n";
    }

    public static int numPessoas() {
        return countPessoas;
    }

}
