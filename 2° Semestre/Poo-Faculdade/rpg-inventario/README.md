# RPG de Inventário — Mini Sistema CRUD com Java

## Nome da aplicação
**RPG de Inventário** — gerenciador de itens de um personagem de RPG (CLI, sem interface gráfica e sem persistência).

## Descrição das funcionalidades
Menu em loop que só termina quando o usuário digita `0`.

| Opção | Função | Detalhes |
|-------|--------|----------|
| 1 | Adicionar item | Escolhe o tipo (Arma, Armadura ou Poção) e informa nome, peso, valor e o atributo do tipo (dano, defesa ou cura) |
| 2 | Listar itens | Mostra todos os itens e o total de peso e moedas |
| 3 | Atualizar item | Escolhe pelo ID; Enter mantém o valor atual de cada campo |
| 4 | Remover item | Escolhe pelo ID e pede confirmação (s/n) |
| 0 | Sair | Encerra o programa |

**Validações:** nome obrigatório; peso e valor não podem ser negativos; dano, defesa e cura devem ser maiores que zero; entradas não numéricas são recusadas e a pergunta se repete; IDs inexistentes são avisados.

## Organização do código
```
src/
├── main/     Main.java                        → ponto de entrada
├── ui/       Menu.java, Entrada.java          → menu CLI e leitura validada do teclado
├── servico/  InventarioService.java           → regras do CRUD (lista em memória)
└── modelo/   Item.java (abstrata)             → família com herança
              ├── Arma.java
              ├── Armadura.java
              └── Pocao.java
```

Conceitos usados: estruturas condicionais (`if`/`switch`), laços (`do-while`, `while`, `for`), funções (métodos), classes, encapsulamento, herança, classe abstrata, polimorfismo (`getTipo()`, `getDetalhe()`, `setAtributo()`).

## Como executar
Requisito: JDK 17 ou superior (`java -version` e `javac -version`).

1. Entre na pasta do projeto:
   ```bash
   cd rpg-inventario
   ```
2. Compile:
   ```bash
   mkdir -p out
   javac -encoding UTF-8 -d out $(find src -name '*.java')
   ```
3. Execute:
   ```bash
   java -cp out main.Main
   ```
   Se os acentos aparecerem quebrados no terminal, use:
   ```bash
   java -Dstdout.encoding=UTF-8 -cp out main.Main
   ```

No Windows (PowerShell), o passo 2 pode ser:
```powershell
mkdir out
javac -encoding UTF-8 -d out (Get-ChildItem -Recurse -Filter *.java src).FullName
```
