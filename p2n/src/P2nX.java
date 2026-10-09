import java.util.ArrayList;
import java.util.Scanner;

public class P2nX {
    public static void main(String[] args) {

        MinhaListaOrdenavel lista = new MinhaListaOrdenavel();

        lista.add(new Homem("Carlos", "Pereira", "15", "3", "1965", "52601815906", 85.3f, 1.71f));
        lista.add(new Homem("Bruno", "Almeida", "2", "11", "1998", "08301661305", 62.0f, 1.80f));
        lista.add(new Homem("Rafael", "Souza", "28", "7", "1987", "18609139034", 78.5f, 1.76f));
        lista.add(new Homem("Eduardo", "Lima", "9", "1", "2001", "99603082430", 95.0f, 1.83f));
        lista.add(new Homem("Thiago", "Costa", "30", "5", "1979", "62819482112", 70.2f, 1.68f));
        lista.add(new Mulher("Mariana", "Silva", "2", "2", "1992", "99351819019", 62.8f, 1.75f));
        lista.add(new Mulher("Ana", "Oliveira", "21", "9", "1984", "93786579741", 48.0f, 1.62f));
        lista.add(new Mulher("Juliana", "Rocha", "14", "12", "1995", "54323194897", 80.5f, 1.65f));
        lista.add(new Mulher("Beatriz", "Santos", "5", "6", "1970", "75749118606", 58.4f, 1.60f));
        lista.add(new Mulher("Larissa", "Mendes", "18", "4", "2003", "25276018987", 55.0f, 1.70f));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("    1.Imprimir Lista");
            System.out.println("    2.Sair");
            System.out.print("Digite sua opcao: ");

            if (!scanner.hasNextLine()) {
                break;
            }
            String opcao = scanner.nextLine().trim();

            if (opcao.isEmpty() || opcao.equals("2")) {
                break;
            }

            if (!opcao.equals("1")) {
                System.out.println("Opcao invalida!!");
                continue;
            }

            int criterio = lerCriterio(scanner);

            if (criterio == 0) {
                continue;
            }

            ArrayList ordenada = lista.ordena(criterio);

            System.out.println();
            System.out.println("Ordenacao: " + MinhaListaOrdenavel.descricao(criterio));
            for (Object pessoa : ordenada) {
                System.out.println("--------");
                System.out.print(pessoa);
                System.out.println("--------");
            }
        }

        scanner.close();
    }

    private static int lerCriterio(Scanner scanner) {
        while (true) {
            System.out.println();
            System.out.println("    Escolha seu modo de ordenacao");
            for (int i = 1; i <= MinhaListaOrdenavel.numCriterios(); i++) {
                System.out.println("    " + i + "." + MinhaListaOrdenavel.descricao(i));
            }
            System.out.print("Digite sua opcao: ");

            if (!scanner.hasNextLine()) {
                return 0;
            }
            String entrada = scanner.nextLine().trim();

            if (entrada.isEmpty()) {
                return 0;
            }

            try {
                int criterio = Integer.parseInt(entrada);
                if (criterio >= 1 && criterio <= MinhaListaOrdenavel.numCriterios()) {
                    return criterio;
                }
            } catch (NumberFormatException e) {
            }

            System.out.println("Opcao invalida!!");
        }
    }
}
