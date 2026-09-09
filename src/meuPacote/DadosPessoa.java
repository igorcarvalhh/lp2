package meuPacote;

import java.util.Scanner;

public class DadosPessoa {
    public String nome;
    public String sobreNome;
    public String dia = "5";
    public String mes = "3";
    public String ano = "2003";
    public String numCPF;
    public float peso;
    public float altura;
    public Scanner scanner;

    public DadosPessoa(Scanner scanner) {
        this.scanner = scanner;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public void setNumCPF(String numCPF) {
        this.numCPF = numCPF;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public AtributoPessoa[] getAtributos() {

        return new AtributoPessoa[]{
                new NomePessoa(this.scanner),
                new SobreNomePessoa(this.scanner),
                new NascimentoPessoa(this.scanner),
//                new DiaPessoa(this.scanner),
//                new MesPessoa(this.scanner),
//                new AnoPessoa(this.scanner),
                new NumCPFPessoa(this.scanner),
                new PesoPessoa(this.scanner),
                new AlturaNomePessoa(this.scanner),
        };
    }
}
