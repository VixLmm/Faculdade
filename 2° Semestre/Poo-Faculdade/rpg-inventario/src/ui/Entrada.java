package ui;

import java.util.Scanner;

/**
 * Funções utilitárias de leitura do teclado, com validação e nova tentativa.
 */
public class Entrada {

    /** Lançada quando a entrada acaba (Ctrl+D ou fim de arquivo). */
    public static class EntradaEncerrada extends RuntimeException {
        public EntradaEncerrada() {
            super("Entrada encerrada.");
        }
    }

    private final Scanner scanner;

    public Entrada(Scanner scanner) {
        this.scanner = scanner;
    }

    private String lerLinha(String mensagem) {
        System.out.print(mensagem);
        if (!scanner.hasNextLine()) {
            throw new EntradaEncerrada();
        }
        return scanner.nextLine().trim();
    }

    public String lerTexto(String mensagem) {
        while (true) {
            String texto = lerLinha(mensagem);
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("  ! Campo obrigatório. Tente novamente.");
        }
    }

    /** Lê um inteiro no intervalo [min, max]. */
    public int lerInt(String mensagem, int min, int max) {
        while (true) {
            String texto = lerLinha(mensagem);
            try {
                int valor = Integer.parseInt(texto);
                if (valor >= min && valor <= max) {
                    return valor;
                }
                System.out.println("  ! Digite um número entre " + min + " e " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("  ! Valor inválido. Digite um número inteiro.");
            }
        }
    }

    /** Lê um decimal >= min. Aceita vírgula ou ponto. */
    public double lerDouble(String mensagem, double min) {
        while (true) {
            String texto = lerLinha(mensagem).replace(',', '.');
            try {
                double valor = Double.parseDouble(texto);
                if (Double.isNaN(valor) || Double.isInfinite(valor)) {
                    System.out.println("  ! Valor inválido.");
                } else if (valor < min) {
                    System.out.println("  ! O valor mínimo é " + min + ".");
                } else {
                    return valor;
                }
            } catch (NumberFormatException e) {
                System.out.println("  ! Valor inválido. Digite um número (ex.: 2.5).");
            }
        }
    }

    /** Lê uma linha; se o usuário só apertar Enter, retorna "" (usado em atualizações). */
    public String lerOpcional(String mensagem) {
        return lerLinha(mensagem);
    }

    public boolean confirmar(String mensagem) {
        while (true) {
            String r = lerLinha(mensagem + " (s/n): ").toLowerCase();
            if (r.equals("s")) {
                return true;
            }
            if (r.equals("n")) {
                return false;
            }
            System.out.println("  ! Responda com 's' ou 'n'.");
        }
    }
}
