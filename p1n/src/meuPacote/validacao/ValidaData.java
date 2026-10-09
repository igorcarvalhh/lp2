package meuPacote.validacao;

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

    public static int anoMinimo() {
        return anoMaximo() - 120;
    }

    public static int anoMaximo() {
        return new GregorianCalendar().get(Calendar.YEAR);
    }

    public static boolean isAno(int ano) {
        return ano >= anoMinimo() && ano <= anoMaximo();
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

        GregorianCalendar data = new GregorianCalendar(ano, mes - 1, dia);

        return data.get(Calendar.YEAR) == ano
                && data.get(Calendar.MONTH) == mes - 1
                && data.get(Calendar.DAY_OF_MONTH) == dia;
    }

    public static boolean isDataValida(String dia, String mes, String ano) {
        if (!isDia(dia) || !isMes(mes) || !isAno(ano)) {
            return false;
        }

        int mesInt;

        try {
            mesInt = Integer.parseInt(mes.trim());
        } catch (NumberFormatException e) {
            mesInt = Mes.obterMes(mes).getNumero();
        }

        return isDataValida(
                Integer.parseInt(dia.trim()),
                mesInt,
                Integer.parseInt(ano.trim())
        );
    }
}
