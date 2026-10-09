package meuPacote.modelo;

import meuPacote.entrada.DadosPessoa;
import meuPacote.validacao.ValidaCPF;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Pessoa {
    private String nome;
    private String sobreNome;
    private GregorianCalendar dataNasc;
    private String numCPF;
    private float peso;
    private float altura;
    private static int countPessoas = 0;

    public Pessoa(String nome, String sobreNome, String dia, String mes, String ano, String numCPF, float peso, float altura) {
        if (numCPF != null && !ValidaCPF.isCPF(numCPF)) {
            throw new IllegalArgumentException("CPF inválido");
        }

        this.nome = nome;
        this.sobreNome = sobreNome;
        this.numCPF = numCPF;
        this.peso = peso;
        this.altura = altura;
        this.dataNasc = new GregorianCalendar(Integer.parseInt(ano), Integer.parseInt(mes) - 1, Integer.parseInt(dia));

        countPessoas++;
    }

    public Pessoa(String nome, String sobreNome, String dia, String mes, String ano) {
        this(nome, sobreNome, dia, mes, ano, null, 0, 0);
    }

    public Pessoa(DadosPessoa dados) {
        this(
                dados.nome,
                dados.sobreNome,
                dados.dia,
                dados.mes,
                dados.ano,
                dados.numCPF,
                dados.peso,
                dados.altura
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
            this.numCPF = numCPF;
        } else {
            System.out.println("CPF inválido.");
        }
    }

    public float getPeso() {
        return this.peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return this.altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
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

    public static String validaPeso(String valor) {
        try {
            float peso = parseDecimal(valor);

            if (peso < 0.5 || peso > 300) {
                return "Insira um valor entre 0.5 e 300 kg.";
            }

            return null;
        } catch (NumberFormatException e) {
            return "Insira um número válido.";
        }
    }

    public static String validaAltura(String valor) {
        try {
            float altura = parseDecimal(valor);

            if (altura < 0.3 || altura > 2.8) {
                return "Insira um valor entre 0.3 e 2.8 metros.";
            }

            return null;
        } catch (NumberFormatException e) {
            return "Insira um número válido.";
        }
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

    public String toString() {
        return "Nome: " + this.nome + '\n' +
                "Sobrenome: " + this.sobreNome + '\n' +
                "Data de Nascimento: " + this.dataNasc + '\n' +
                "CPF: " + this.numCPF + '\n' +
                "Peso: " + this.peso + '\n' +
                "Altura: " + this.altura + '\n';
    }

    protected String toString(String genero) {
        return "Nome: " + this.nome + '\n' +
                "Sobrenome: " + this.sobreNome + '\n' +
                "Idade: " + getIdade() + '\n' +
                "Gênero: " + genero + '\n' +
                "CPF: " + this.numCPF + '\n' +
                "Peso: " + this.peso + '\n' +
                "Altura: " + this.altura + '\n';
    }

    public static int numPessoas() {
        return countPessoas;
    }

}
