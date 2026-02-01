package application;

import model.entities.GerenciadorDeGasto;

import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try (Scanner sc = new Scanner(System.in)) {
            GerenciadorDeGasto gerenciador = new GerenciadorDeGasto();
            GastoController controller = new GastoController(sc, fmt, gerenciador);

            int opcao;
            do {
                controller.exibirMenu();
                opcao = controller.lerOpcao();
                controller.executarOpcao(opcao);
            } while (opcao != 0);
        }
    }
}