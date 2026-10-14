/* Tabuada Inteligente
 * Objetivo: Praticar a leitura de dados, uso do laço for/while, operadores matemáticos simples, operador de módulo (%) e a condicional if/else.
Enunciado:
Escreva um programa em Java que peça ao usuário para digitar um número inteiro positivo. O programa deve calcular e exibir a tabuada de 1 a 10 desse número. No entanto, o programa possui três regras adicionais:
Se o usuário digitar um número negativo, o programa deve exibir uma mensagem de erro: "Número inválido. Por favor, digite um número maior que zero."
Ao imprimir a tabuada, o programa deve informar, ao lado de cada resultado, se aquele valor é "Par" ou "Ímpar".
O programa deve rodar de forma infinita e mostrar tabuadas até o usuário digitar 0 para finalizar*/
import java.util.Scanner;
public class atv1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Digite um número inteiro positivo (0 para sair): ");
            int n = sc.nextInt();

            if (n == 0) {
                break;
            }

            if (n < 0) {
                System.out.println("Número inválido. Por favor, digite um número maior que zero.");
                continue;
            }

            for (int i = 1; i <= 10; i++) {
                int resultado = n * i;
                String tipo = (resultado % 2 == 0) ? "Par" : "Ímpar";
                System.out.println(n + " x " + i + " = " + resultado + " (" + tipo + ")");
            }
        }

        System.out.println("Programa encerrado.");
        sc.close();
    }
}
