package meuPacote.entrada;

import meuPacote.modelo.Pessoa;

import java.util.Scanner;

public class SobreNomePessoa extends CampoSimplesPessoa {

    public SobreNomePessoa(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void armazenar(DadosPessoa dados, String valor) {
        dados.setSobreNome(valor);
    }

    @Override
    protected String getPergunta() {
        return "Insira o sobrenome: ";
    }

    @Override
    protected String getRotuloErro() {
        return "Sobrenome inválido.";
    }

    @Override
    protected String validar(String valor) {
        return Pessoa.validaNome(valor);
    }
}
