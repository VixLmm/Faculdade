import java.util.Scanner;

public class atv2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double saldo = 500.00;
        int opcao;

        do {
            System.out.println("\n===== CAIXA ELETRÔNICO =====");
            System.out.println("1 - Consultar Saldo");
            System.out.println("2 - Realizar Depósito");
            System.out.println("3 - Realizar Saque");
            System.out.println("4 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.printf("Saldo atual: R$ %.2f%n", saldo);
                    break;

                case 2:
                    System.out.print("Valor do depósito: R$ ");
                    double deposito = scanner.nextDouble();
                    if (deposito > 0) {
                        saldo += deposito;
                        System.out.println("Depósito realizado com sucesso!");
                    } else {
                        System.out.println("Valor inválido. O depósito deve ser positivo.");
                    }
                    break;

                case 3:
                    System.out.print("Valor do saque: R$ ");
                    double saque = scanner.nextDouble();
                    if (saque <= 0) {
                        System.out.println("Valor inválido. O saque deve ser positivo.");
                    } else if (saque > saldo) {
                        System.out.println("Saldo insuficiente.");
                    } else {
                        saldo -= saque;
                        System.out.println("Saque realizado com sucesso!");
                    }
                    break;

                case 4:
                    System.out.println("Encerrando... Obrigado!");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 4);

        scanner.close();
    }
}
