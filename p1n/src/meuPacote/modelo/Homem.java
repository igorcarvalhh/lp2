package meuPacote.modelo;

import meuPacote.entrada.DadosPessoa;

public class Homem extends Pessoa {
    private static final String GENERO = "Masculino";

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
        return super.toString(GENERO);
    }
}
