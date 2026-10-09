package meuPacote.colecao;

import meuPacote.modelo.Homem;
import meuPacote.modelo.PessoaIMC;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * Encapsula um ArrayList de PessoaIMC (objetos Homem ou Mulher) e oferece
 * a ordenacao por varios criterios atraves de Comparators definidos como
 * classes anonimas e de Collections.sort().
 *
 * O ArrayList e usado sem generics (tipo "cru"), como pedido no enunciado;
 * por isso get() faz o casting para PessoaIMC. Os Comparators seguem o
 * exemplo do enunciado (Comparator<PessoaIMC>).
 */
public class MinhaListaOrdenavel {

    // "Tabela" de constantes com os criterios de ordenacao.
    public static final int NOME_AZ = 1;
    public static final int NOME_ZA = 2;
    public static final int PESO = 3;
    public static final int PESO_REVERSO = 4;
    public static final int ALTURA = 5;
    public static final int ALTURA_REVERSO = 6;
    public static final int IMC = 7;
    public static final int IMC_REVERSO = 8;
    public static final int GENERO = 9;
    public static final int GENERO_REVERSO = 10;
    public static final int IDADE = 11;
    public static final int IDADE_REVERSO = 12;
    public static final int DATA_NASC = 13;
    public static final int DATA_NASC_REVERSO = 14;
    public static final int CPF = 15;
    public static final int CPF_REVERSO = 16;

    // Descricao de cada criterio, na posicao (constante - 1), usada no menu.
    private static final String[] DESCRICOES = {
            "Alfabetica (A-Z)",
            "Alfabetica (Z-A)",
            "Menor Peso",
            "Maior Peso",
            "Menor Altura",
            "Maior Altura",
            "Menor IMC",
            "Maior IMC",
            "Homem / Mulher (homens primeiro)",
            "Mulher / Homem (mulheres primeiro)",
            "Menor Idade",
            "Maior Idade",
            "Data de Nascimento (mais antiga primeiro)",
            "Data de Nascimento (mais recente primeiro)",
            "CPF (crescente)",
            "CPF (decrescente)"
    };

    private ArrayList lista = new ArrayList();

    // Nome: compara o nome e, em caso de empate, o sobrenome (sem diferenciar maiusculas).
    public Comparator<PessoaIMC> nomeC = new Comparator<PessoaIMC>() {
        @Override
        public int compare(PessoaIMC p1, PessoaIMC p2) {
            int resultado = p1.getNome().compareToIgnoreCase(p2.getNome());
            if (resultado != 0) {
                return resultado;
            }
            return p1.getSobreNome().compareToIgnoreCase(p2.getSobreNome());
        }
    };

    public Comparator<PessoaIMC> pesoC = new Comparator<PessoaIMC>() {
        @Override
        public int compare(PessoaIMC p1, PessoaIMC p2) {
            float peso1, peso2;
            peso1 = p1.getPeso();
            peso2 = p2.getPeso();
            return Float.compare(peso1, peso2);
        }
    };

    public Comparator<PessoaIMC> alturaC = new Comparator<PessoaIMC>() {
        @Override
        public int compare(PessoaIMC p1, PessoaIMC p2) {
            return Float.compare(p1.getAltura(), p2.getAltura());
        }
    };

    public Comparator<PessoaIMC> imcC = new Comparator<PessoaIMC>() {
        @Override
        public int compare(PessoaIMC p1, PessoaIMC p2) {
            return Float.compare(p1.calculaIMC(), p2.calculaIMC());
        }
    };

    // Genero: identificado por instanceof (nao ha campo de genero). Ordem crescente = Homem antes de Mulher.
    public Comparator<PessoaIMC> generoC = new Comparator<PessoaIMC>() {
        @Override
        public int compare(PessoaIMC p1, PessoaIMC p2) {
            int ordem1 = (p1 instanceof Homem) ? 0 : 1;
            int ordem2 = (p2 instanceof Homem) ? 0 : 1;
            return Integer.compare(ordem1, ordem2);
        }
    };

    public Comparator<PessoaIMC> idadeC = new Comparator<PessoaIMC>() {
        @Override
        public int compare(PessoaIMC p1, PessoaIMC p2) {
            return Integer.compare(p1.getIdade(), p2.getIdade());
        }
    };

    // Data de nascimento: compara as datas completas (GregorianCalendar), nao so o ano.
    public Comparator<PessoaIMC> dataNascC = new Comparator<PessoaIMC>() {
        @Override
        public int compare(PessoaIMC p1, PessoaIMC p2) {
            return p1.getDataNascCalendar().compareTo(p2.getDataNascCalendar());
        }
    };

    // CPF: armazenado com 11 digitos, entao a ordem lexicografica coincide com a numerica.
    public Comparator<PessoaIMC> cpfC = new Comparator<PessoaIMC>() {
        @Override
        public int compare(PessoaIMC p1, PessoaIMC p2) {
            String cpf1 = p1.getNumCPF() == null ? "" : p1.getNumCPF();
            String cpf2 = p2.getNumCPF() == null ? "" : p2.getNumCPF();
            return cpf1.compareTo(cpf2);
        }
    };

    @SuppressWarnings("unchecked")
    public void add(PessoaIMC p) {
        this.lista.add(p);
    }

    public PessoaIMC get(int i) {
        return (PessoaIMC) this.lista.get(i);
    }

    public int size() {
        return this.lista.size();
    }

    public static int numCriterios() {
        return DESCRICOES.length;
    }

    public static String descricao(int criterio) {
        return DESCRICOES[criterio - 1];
    }

    /*
     * Ordena o ArrayList encapsulado segundo o criterio (uma das constantes
     * acima) e o devolve. Criterio desconhecido: a lista volta sem alteracao.
     */
    @SuppressWarnings("unchecked")
    public ArrayList ordena(int criterio) {
        switch (criterio) {
            case NOME_AZ:
                Collections.sort(this.lista, nomeC);
                break;
            case NOME_ZA:
                Collections.sort(this.lista, nomeC.reversed());
                break;
            case PESO:
                Collections.sort(this.lista, pesoC);
                break;
            case PESO_REVERSO:
                Collections.sort(this.lista, pesoC.reversed());
                break;
            case ALTURA:
                Collections.sort(this.lista, alturaC);
                break;
            case ALTURA_REVERSO:
                Collections.sort(this.lista, alturaC.reversed());
                break;
            case IMC:
                Collections.sort(this.lista, imcC);
                break;
            case IMC_REVERSO:
                Collections.sort(this.lista, imcC.reversed());
                break;
            case GENERO:
                Collections.sort(this.lista, generoC);
                break;
            case GENERO_REVERSO:
                Collections.sort(this.lista, generoC.reversed());
                break;
            case IDADE:
                Collections.sort(this.lista, idadeC);
                break;
            case IDADE_REVERSO:
                Collections.sort(this.lista, idadeC.reversed());
                break;
            case DATA_NASC:
                Collections.sort(this.lista, dataNascC);
                break;
            case DATA_NASC_REVERSO:
                Collections.sort(this.lista, dataNascC.reversed());
                break;
            case CPF:
                Collections.sort(this.lista, cpfC);
                break;
            case CPF_REVERSO:
                Collections.sort(this.lista, cpfC.reversed());
                break;
            default:
                break;
        }
        return this.lista;
    }
}
