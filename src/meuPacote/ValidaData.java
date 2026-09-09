package meuPacote;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ValidaData {
    public static boolean isDia(int dia) {
        return dia >= 1 && dia <= 31;
    }

    public static boolean isDia(String dia) {
        try {
            int diaInt = Integer.parseInt(dia);
            return isDia(diaInt);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isMes(int mes) {
        return mes >= 1 && mes <= 12;
    }

    public static boolean isMes(String mes) {
        if (mes == null) {
            return false;
        }

        String valor = mes.trim().toLowerCase();

        for (Mes m : Mes.values()) {
            if (m.getNome().equals(valor)) {
                return true;
            }

            try {
                int numero = Integer.parseInt(valor);

                if (m.getNumero() == numero) {
                    return true;
                }

            } catch (NumberFormatException e) {
                // Não é um número, então continua procurando pelo nome.
            }
        }

        return false;
    }

    public static boolean isAno(int ano) {
        GregorianCalendar calendario = new GregorianCalendar();

        int anoAtual = calendario.get(Calendar.YEAR);

        return (ano >= anoAtual - 120) && ano <= anoAtual;
    }

    public static boolean isAno(String ano) {
        try {
            int anoInt = Integer.parseInt(ano);
            return isAno(anoInt);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDataValida(int dia, int mes, int ano) {
        if (!isDia(dia)) {
            System.out.println("Dia inválido. Insira um dia existente.");
            return false;
        }

        if (!isMes(mes)) {
            System.out.println("Mês inválido. Insira um mes existente.");
            return false;
        }

        if (!isAno(ano)) {
            System.out.println("Ano inválido. Insira um ano existente.");
            return false;
        }

        return true;
    }

    public static boolean isDataValida(String dia, String mes, String ano) {
        return false;
    }
}
