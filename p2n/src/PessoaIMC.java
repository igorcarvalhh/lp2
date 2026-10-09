import java.util.Locale;

public abstract class PessoaIMC extends Pessoa {
    protected float peso;
    protected float altura;

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

    @Override
    public String toString() {
        return super.toString() +
                "Peso: " + this.peso + " Altura: " + this.altura + '\n' +
                "IMC: " + String.format(Locale.ROOT, "%.2f", calculaIMC()) + " " + resultIMC() + '\n';
    }
}
