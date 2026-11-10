package main;

import servico.InventarioService;
import ui.Menu;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            new Menu(new InventarioService(), scanner).iniciar();
        }
    }
}
