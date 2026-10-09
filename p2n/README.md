# P2n – PessoaIMC e Ordenação

Implementação do enunciado `P2n-PessoaIMC-Ordenacao-2025-2.md` (esse arquivo não foi alterado).

## Como compilar e executar

```
javac -d out $(find src -name "*.java")
java -cp out P2nX
```

## Estrutura

```
src/
├── P2nX.java                          classe principal (menu)
└── meuPacote/
    ├── modelo/
    │   ├── Pessoa.java                (P1n, sem peso/altura)
    │   ├── PessoaIMC.java             (nova, abstrata)
    │   ├── Homem.java                 (agora herda de PessoaIMC)
    │   └── Mulher.java                (agora herda de PessoaIMC)
    ├── colecao/
    │   └── MinhaListaOrdenavel.java   (nova)
    ├── validacao/                     (P1n, sem alteração)
    └── entrada/                       (P1n, só Peso/AlturaPessoa ajustadas)
```

Todas as classes do P1n foram copiadas de `../lp2/src`, como pede o enunciado.
O P2n não lê dados do teclado; o pacote `entrada` foi mantido para reuso no próximo programa.

## Parte 1 – Decisões de projeto

### Pessoa
- Foram removidos os campos `peso` e `altura`, seus `get`/`set` e a exibição no `toString`.
- Os validadores `validaPeso`/`validaAltura` (métodos de classe) foram para `PessoaIMC`, junto dos campos
  a que se referem. As classes `PesoPessoa` e `AlturaPessoa` passaram a chamar `PessoaIMC.validaPeso/validaAltura`.
- Construtores: `(nome, sobreNome, dia, mes, ano)` e `(nome, sobreNome, dia, mes, ano, numCPF)`;
  ou seja, os construtores do P1n sem peso e altura.
- O CPF é armazenado só com os 11 dígitos (requisito do P1n que o código anterior não cumpria) e exibido
  formatado com `ValidaCPF.imprimeCPF`. Isso também permite ordenar por CPF comparando Strings.
- `toString` passou a usar `getDataNasc()` (dd/MM/yyyy). Antes ele imprimia o `GregorianCalendar` cru.
- **Gênero sem campo rótulo:** o enunciado diz que um campo para rotular o gênero estaria errado. Por isso
  foram removidas a constante `GENERO` de Homem/Mulher e o `toString(String genero)` de Pessoa. O gênero é
  exibido com `getClass().getSimpleName()`, que dá "Homem" ou "Mulher" conforme a classe real do objeto.
- Novo `getDataNascCalendar()`, que devolve uma **cópia** (`clone`) da data, para o comparador de data de
  nascimento sem expor o objeto interno.

### PessoaIMC (abstrata)
- Campos `peso` e `altura` **`float` e `protected`**, como pede o texto. O diagrama da Figura 1 mostra
  `-double`, mas o texto é explícito ("ambos do tipo float. Faça estes campos protegidos") e prevalece.
- Construtores alinhados com os de Pessoa, mais `peso` e `altura`. Os demais campos são repassados via
  `super(...)`. Há também o construtor com `DadosPessoa`, alinhado ao de Pessoa.
- Peso ou altura ≤ 0 lançam `IllegalArgumentException`, pelo mesmo critério que Pessoa já usava para
  CPF inválido. Isso evita divisão por zero no IMC.
- `calculaIMC()` **sem parâmetros**: usa os campos e retorna `float` (peso / altura²). O diagrama mostra
  `calculaIMC(double, double)`, mas o texto diz que ele "utiliza os valores dos campos altura e o peso".
- `resultIMC()` é abstrato.
- `toString()` reaproveita `super.toString()` (nome, classe, nascimento, CPF, idade) e acrescenta
  `Peso: x Altura: y` e `IMC: valor classificação`. A classificação vem de `resultIMC()` por polimorfismo.

### Homem e Mulher
- Herdam de `PessoaIMC`, mantêm os construtores (agora com `peso`/`altura` obrigatórios) e implementam
  `resultIMC()` com a tabela do enunciado. Os limites são inclusivos na faixa de peso ideal:
  - Homem: `< 20.7` abaixo; `20.7 ≤ IMC ≤ 26.4` ideal; `> 26.4` acima.
  - Mulher: `< 19` abaixo; `19 ≤ IMC ≤ 25.8` ideal; `> 25.8` acima.
- Não sobrescrevem `toString`: o de `PessoaIMC` já mostra tudo, inclusive a classe (diagrama da Figura 1).

## Parte 2 – Decisões de projeto

### MinhaListaOrdenavel
- Encapsula um `ArrayList` **sem generics** (tipo cru), seguindo "Evite usar GENERICS". Por isso
  `get(int i)` faz o casting para `PessoaIMC`. Os `Comparator<PessoaIMC>` seguem exatamente o modelo dado
  no enunciado. As advertências de tipo cru em `add` e `ordena` foram silenciadas com
  `@SuppressWarnings("unchecked")`.
- `add(PessoaIMC p)` e `get(int i)` não validam nada nem lançam exceções, como pede o enunciado.
- Comparadores como **classes anônimas** (forma principal do enunciado), um campo por critério:
  `nomeC`, `pesoC`, `alturaC`, `imcC`, `generoC`, `idadeC`, `dataNascC`, `cpfC`.
  - Números: `Float.compare` / `Integer.compare` (métodos de classe das wrapper classes).
  - Nome: `String.compareToIgnoreCase` no nome e, em caso de empate, no sobrenome.
  - Gênero: usa `instanceof Homem` (não existe campo de gênero). Crescente = homens primeiro.
  - Data de nascimento: compara os `GregorianCalendar` (data completa).
  - CPF: compara as Strings de 11 dígitos, o que equivale à ordem numérica.
- A ordem decrescente usa `comparador.reversed()`, sem duplicar comparadores.
- "Tabela" de constantes `public static final int` (`NOME_AZ`, `NOME_ZA`, `PESO`, `PESO_REVERSO`, …,
  `CPF_REVERSO`), 16 critérios. Cobre todos os pedidos (nome, peso, IMC, gênero, idade, data de
  nascimento, CPF) e também altura, que aparece no item 3 e no menu de exemplo.
- `ordena(int criterio)` usa `Collections.sort(lista, comparador)` num `switch`, **com `break`** em cada
  `case` (o exemplo do enunciado não tem `break` e cairia no caso seguinte). Ele ordena o ArrayList
  encapsulado e o devolve. Critério desconhecido devolve a lista sem alteração.
- Um vetor `DESCRICOES` guarda o texto de cada critério, para o menu ser gerado a partir da própria classe.

### P2nX (classe principal)
- Como permitido ("pode colocar estas etapas dentro do método main"), o `main` cria a
  `MinhaListaOrdenavel`, insere **5 Homem e 5 Mulher** criados no código (CPFs com dígitos verificadores
  válidos) e mostra o menu.
- Menu principal: `1` imprime a lista; `2` **ou ENTER** encerra (o exemplo termina com `[ENTER]`).
  Qualquer outra entrada (ex.: `A`) mostra `Opcao invalida!!` e repete o menu.
- Menu de ordenação: número de 1 a 16 lista as pessoas ordenadas. ENTER volta ao menu principal. Entrada
  inválida mostra `Opcao invalida!!` e repete a pergunta.
- Cada pessoa é exibida pelo seu `toString` entre linhas `--------`, mostrando a classe (Homem/Mulher),
  nascimento, CPF, idade, peso, altura, IMC e a avaliação do IMC.
- Fim da entrada padrão (EOF) encerra o programa sem exceção.
- Os nomes de exemplo não têm acento, porque `compareToIgnoreCase` ordena caracteres acentuados depois do "z".
