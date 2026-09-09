package meuPacote;

public enum Mes {

    JANEIRO(1, "janeiro"),
    FEVEREIRO(2, "fevereiro"),
    MARCO(3, "marco"),
    ABRIL(4, "abril"),
    MAIO(5, "maio"),
    JUNHO(6, "junho"),
    JULHO(7, "julho"),
    AGOSTO(8, "agosto"),
    SETEMBRO(9, "setembro"),
    OUTUBRO(10, "outubro"),
    NOVEMBRO(11, "novembro"),
    DEZEMBRO(12, "dezembro");

    private final int numero;
    private final String nome;

    Mes(int numero, String nome) {
        this.numero = numero;
        this.nome = nome;
    }

    public int getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }

    public static Mes obterMes(String entrada) {

        entrada = entrada.trim();

        for (Mes mes : values()) {

            if (entrada.equals(String.valueOf(mes.numero))
                    || entrada.equals(String.format("%02d", mes.numero))
                    || entrada.equalsIgnoreCase(mes.nome)) {

                return mes;
            }
        }

        return null;
    }
}