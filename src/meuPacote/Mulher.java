package meuPacote;

public class Mulher extends Pessoa {
    private String genero = "Feminino";

    public Mulher(String nome, String sobreNome, String dia, String mes, String ano) {
        super(nome, sobreNome, dia, mes, ano);
    }

    public Mulher(String nome, String sobreNome, String dia, String mes, String ano, String numCPF, float peso, float altura) {
        super(nome, sobreNome, dia, mes, ano, numCPF, peso, altura);
    }

    public Mulher(DadosPessoa dados) {
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

    public String toString() {
        return "Nome: " + getNome() + '\n' +
                "Sobrenome: " + getSobreNome() + '\n' +
                "Idade: " + getIdade() + '\n' +
                "Gênero: " + this.genero + '\n' +
                "CPF: " + getNumCPF() + '\n' +
                "Peso: " + getPeso() + '\n' +
                "Altura: " + getAltura() + '\n';
    }
}
