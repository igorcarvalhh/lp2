package meuPacote.entrada;

import meuPacote.modelo.Pessoa;

import java.util.Scanner;

public class PesoPessoa extends CampoSimplesPessoa {

    public PesoPessoa(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void armazenar(DadosPessoa dados, String valor) {
        dados.setPeso(Pessoa.parseDecimal(valor));
    }

    @Override
    protected String getPergunta() {
        return "Insira o peso: ";
    }

    @Override
    protected String getRotuloErro() {
        return "Peso inválido.";
    }

    @Override
    protected String validar(String valor) {
        return Pessoa.validaPeso(valor);
    }
}
