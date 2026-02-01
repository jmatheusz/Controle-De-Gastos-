package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class InputValidator {
    public static int lerInteiro(Scanner sc, String mensagem) {

        while(true) {
            try {
                System.out.print(mensagem);
                int valor = Integer.parseInt(sc.nextLine());

                if(valor <0){
                    throw new IllegalArgumentException();
                }
                return valor;
            } catch (NumberFormatException e ){
                System.out.println("⚠ Digite apenas números inteiros");
            }catch (IllegalArgumentException e){
                System.out.println("⚠ O número não pode ser negativo.");
            }
        }

    }
    public static double lerDouble(Scanner sc,String mensagem){
        while(true){
            try{
                System.out.print(mensagem);
                double valor = Double.parseDouble(sc.nextLine());

                if(valor <0){
                    throw new IllegalArgumentException();
                }
                return valor;
            }catch (NumberFormatException e){
                System.out.println("⚠ Digite um número válido. Ex: 25.50");
            }catch (IllegalArgumentException e) {
                System.out.println("⚠ O valor deve ser maior que zero.");
            }
        }
    }
    public static LocalDate lerData(Scanner sc,DateTimeFormatter fmt){
        while(true){
            try{
                System.out.print("Digite a data (dd/MM/yyyy):");
                return LocalDate.parse(sc.nextLine(),fmt);

            }catch (Exception e){
                System.out.println("⚠ Data inválida. Use o formato dd/MM/yyyy.");
            }
        }
    }

}
