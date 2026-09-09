package meuPacote;

import java.util.GregorianCalendar;
import java.util.Scanner;

public class NascimentoPessoa implements AtributoPessoa {
    final Scanner scanner;

    public NascimentoPessoa(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void armazenar(DadosPessoa dados, String valor) {
        String[] partes = valor.split("/");

        String dia = partes[0];
        String mes = partes[1];
        String ano = partes[2];

        dados.setDia(dia);
        dados.setMes(mes);
        dados.setAno(ano);
    }

    @Override
    public String ler() {
        while (true) {

            // DIA
            System.out.print("Dia de nascimento: ");
            String dia = scanner.nextLine();

            if (dia.isBlank()) {
                return null;
            }

            if (!diaValido(dia)) {
                System.out.println(
                        "Dia inválido. Insira um valor entre 01 e 31."
                );
                continue;
            }

            // MÊS
            System.out.print("Mês de nascimento: ");
            String mes = scanner.nextLine();

            if (mes.isBlank()) {
                return null;
            }

            mes = normalizarMes(mes);

            if (mes == null) {
                System.out.println(
                        "Mês inválido. Insira um número entre 01 e 12 "
                                + "ou o nome de um mês."
                );
                continue;
            }

            // ANO
            System.out.print("Ano de nascimento: ");
            String ano = scanner.nextLine();

            if (ano.isBlank()) {
                return null;
            }

            if (!dataValida(dia, mes, ano)) {
                System.out.println("Data inválida. Tente novamente.");
                continue;
            }

            return dia + "/" + mes + "/" + ano;
        }
    }

    private boolean diaValido(String dia) {
        try {
            dia = dia.trim();
            if (!dia.matches("^\\d\\d?")) {
                return false;
            }
            int diaNumero = Integer.parseInt(dia);

            return diaNumero >= 1 && diaNumero <= 31;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String normalizarMes(String entrada) {

        Mes mes = Mes.obterMes(entrada);

        if (mes == null) {
            return null;
        }

        return String.format("%02d", mes.getNumero());
    }

    private boolean dataValida(String dia, String mes) {

        try {
            int diaNumero = Integer.parseInt(dia);
            int mesNumero = Integer.parseInt(mes);

            return diaNumero >= 1 && mesNumero >= 1 && mesNumero <= 12;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean dataValida(String dia, String mes, String ano) {

        try {
            int diaNumero = Integer.parseInt(dia);
            int mesNumero = Integer.parseInt(mes);
            int anoNumero = Integer.parseInt(ano);

            GregorianCalendar data = new GregorianCalendar(
                    anoNumero,
                    mesNumero - 1,
                    diaNumero
            );

            return data.get(GregorianCalendar.DAY_OF_MONTH) == diaNumero
                    && data.get(GregorianCalendar.MONTH) == mesNumero - 1
                    && data.get(GregorianCalendar.YEAR) == anoNumero;

        } catch (NumberFormatException e) {
            return false;
        }
    }


}
