public class Mulher extends PessoaIMC {

    public Mulher(String nome, String sobreNome, String dia, String mes, String ano, float peso, float altura) {
        super(nome, sobreNome, dia, mes, ano, peso, altura);
    }

    public Mulher(String nome, String sobreNome, String dia, String mes, String ano, String numCPF, float peso, float altura) {
        super(nome, sobreNome, dia, mes, ano, numCPF, peso, altura);
    }

    public Mulher(DadosPessoa dados) {
        super(dados);
    }

    @Override
    public String resultIMC() {
        float imc = calculaIMC();

        if (imc < 19f) {
            return "Abaixo do peso ideal";
        }
        if (imc <= 25.8f) {
            return "Peso ideal";
        }
        return "Acima do peso ideal";
    }
}
