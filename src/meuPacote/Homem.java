package meuPacote;

public class Homem extends Pessoa {
    private String genero = "Masculino";

    public Homem(String nome, String sobreNome, String dia, String mes, String ano) {
        super(nome, sobreNome, dia, mes, ano);
    }

    public Homem(String nome, String sobreNome, String dia, String mes, String ano, String numCPF, float peso, float altura) {
        super(nome, sobreNome, dia, mes, ano, numCPF, peso, altura);
    }

    public Homem(DadosPessoa dados) {
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
