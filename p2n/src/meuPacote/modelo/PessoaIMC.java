package meuPacote.modelo;

import meuPacote.entrada.DadosPessoa;

import java.util.Locale;

/*
 * Classe abstrata que acrescenta a Pessoa os dados para o calculo do IMC.
 * Nao se criam objetos PessoaIMC, apenas de suas subclasses Homem e Mulher,
 * que implementam a classificacao do IMC (resultIMC).
 */
public abstract class PessoaIMC extends Pessoa {
    protected float peso;
    protected float altura;

    // Construtores "alinhados" com os de Pessoa, recebendo adicionalmente peso e altura.
    public PessoaIMC(String nome, String sobreNome, String dia, String mes, String ano,
                     float peso, float altura) {
        super(nome, sobreNome, dia, mes, ano);
        inicializa(peso, altura);
    }

    public PessoaIMC(String nome, String sobreNome, String dia, String mes, String ano,
                     String numCPF, float peso, float altura) {
        super(nome, sobreNome, dia, mes, ano, numCPF);
        inicializa(peso, altura);
    }

    public PessoaIMC(DadosPessoa dados) {
        super(dados);
        inicializa(dados.peso, dados.altura);
    }

    // Peso e altura positivos evitam divisao por zero em calculaIMC.
    private void inicializa(float peso, float altura) {
        if (peso <= 0 || altura <= 0) {
            throw new IllegalArgumentException("Peso e altura devem ser positivos");
        }
        this.peso = peso;
        this.altura = altura;
    }

    public float getPeso() {
        return this.peso;
    }

    public float getAltura() {
        return this.altura;
    }

    public float calculaIMC() {
        return this.peso / (this.altura * this.altura);
    }

    public abstract String resultIMC();

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

    /*
     * Reuso por heranca: os dados pessoais vem de Pessoa.toString() e aqui
     * so se acrescentam peso, altura e IMC. A classificacao vem de resultIMC(),
     * resolvida por polimorfismo em Homem ou Mulher.
     */
    @Override
    public String toString() {
        return super.toString() +
                "Peso: " + this.peso + " Altura: " + this.altura + '\n' +
                "IMC: " + String.format(Locale.ROOT, "%.2f", calculaIMC()) + " " + resultIMC() + '\n';
    }
}
