package meuPacote.entrada;

import meuPacote.modelo.Pessoa;

import java.util.Scanner;

public class AlturaPessoa extends CampoSimplesPessoa {

    public AlturaPessoa(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void armazenar(DadosPessoa dados, String valor) {
        dados.setAltura(Pessoa.parseDecimal(valor));
    }

    @Override
    protected String getPergunta() {
        return "Insira a altura: ";
    }

    @Override
    protected String getRotuloErro() {
        return "Altura inválida.";
    }

    @Override
    protected String validar(String valor) {
        return Pessoa.validaAltura(valor);
    }
}
