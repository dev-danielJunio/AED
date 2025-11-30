package org.example.Display;

import org.example.Sistema.SistemaEscola;

import java.util.Scanner;

public class MenuPrincipal {

    private SistemaEscola sistema;
    private Scanner sc = new Scanner(System.in);

    public MenuPrincipal(SistemaEscola sistema) {
        this.sistema = sistema;
    }

    public void mostrar() {
        while (true) {
            System.out.println("\n--- PORTAL ESCOLAR ---");
            System.out.println("1 - Aluno");
            System.out.println("2 - Professor");
            System.out.println("3 - Admin");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            int op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1 -> sistema.getMenuAluno().loginAluno();
                case 2 -> sistema.getMenuProfessor().loginProfessor();
                case 3 -> sistema.getMenuAdmin().loginAdmin();
                case 0 -> { return; }
                default -> System.out.println("Opção inválida!");
            }
        }
    }
}