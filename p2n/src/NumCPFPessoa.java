import java.util.Scanner;

public class NumCPFPessoa extends CampoSimplesPessoa {

    public NumCPFPessoa(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void armazenar(DadosPessoa dados, String valor) {
        dados.setNumCPF(valor);
    }

    @Override
    protected String getPergunta() {
        return "Insira o CPF: ";
    }

    @Override
    protected String getRotuloErro() {
        return "CPF incorreto.";
    }

    @Override
    protected String validar(String valor) {
        return ValidaCPF.isCPF(valor) ? null : "Esse CPF não existe.";
    }
}
