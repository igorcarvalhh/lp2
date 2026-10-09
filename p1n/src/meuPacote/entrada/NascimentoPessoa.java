package meuPacote.entrada;

import meuPacote.validacao.Mes;
import meuPacote.validacao.ValidaData;

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

            if (!ValidaData.isDia(dia)) {
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

            if (!ValidaData.isDataValida(dia, mes, ano)) {
                System.out.println("Data inválida. Tente novamente.");
                continue;
            }

            mes = String.format("%02d", Mes.obterMes(mes).getNumero());
            return dia + "/" + mes + "/" + ano;
        }
    }

    private String normalizarMes(String entrada) {

        Mes mes = Mes.obterMes(entrada);

        if (mes == null) {
            return null;
        }

        return String.format("%02d", mes.getNumero());
    }
}
