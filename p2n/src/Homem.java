public class Homem extends PessoaIMC {

    public Homem(String nome, String sobreNome, String dia, String mes, String ano, float peso, float altura) {
        super(nome, sobreNome, dia, mes, ano, peso, altura);
    }

    public Homem(String nome, String sobreNome, String dia, String mes, String ano, String numCPF, float peso, float altura) {
        super(nome, sobreNome, dia, mes, ano, numCPF, peso, altura);
    }

    public Homem(DadosPessoa dados) {
        super(dados);
    }

    @Override
    public String resultIMC() {
        float imc = calculaIMC();

        if (imc < 20.7f) {
            return "Abaixo do peso ideal";
        }
        if (imc <= 26.4f) {
            return "Peso ideal";
        }
        return "Acima do peso ideal";
    }
}
