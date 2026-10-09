import java.util.Scanner;

public abstract class CampoSimplesPessoa implements AtributoPessoa {
    protected final Scanner scanner;

    protected CampoSimplesPessoa(Scanner scanner) {
        this.scanner = scanner;
    }

    protected abstract String getPergunta();

    protected abstract String getRotuloErro();

    protected abstract String validar(String valor);

    @Override
    public String ler() {
        while (true) {
            System.out.print(getPergunta());
            String valor = scanner.nextLine();

            if (valor.isBlank()) {
                return null;
            }

            String erro = validar(valor);

            if (erro == null) {
                return valor;
            }

            System.out.println(getRotuloErro() + " " + erro);
        }
    }
}
