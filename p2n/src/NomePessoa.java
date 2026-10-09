import java.util.Scanner;

public class NomePessoa extends CampoSimplesPessoa {

    public NomePessoa(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void armazenar(DadosPessoa dados, String valor) {
        dados.setNome(valor);
    }

    @Override
    protected String getPergunta() {
        return "Insira o nome: ";
    }

    @Override
    protected String getRotuloErro() {
        return "Nome inválido.";
    }

    @Override
    protected String validar(String valor) {
        return Pessoa.validaNome(valor);
    }
}
