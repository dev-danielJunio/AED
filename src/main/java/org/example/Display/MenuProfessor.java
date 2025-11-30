package org.example.Display;

import org.example.*;
import org.example.Sistema.CadastroPessoas;
import org.example.Sistema.SistemaEscola;

import java.util.Scanner;

public class MenuProfessor {

    private CadastroPessoas cadastro;
    private SistemaEscola sistema;
    private Scanner sc = new Scanner(System.in);

    public MenuProfessor(SistemaEscola sistema) {
        this.sistema = sistema;
    }

    public void loginProfessor() {
        System.out.println("\n-- Login Professor --");
        System.out.print("Matrícula: ");
        int mat;
        try{
            mat = Integer.parseInt(sc.nextLine());
        }catch(NumberFormatException e){
            System.out.println("Matrícula inválida.");
            return;
        }
        System.out.print("Senha: ");
        String senha = sc.nextLine();

        Pessoa p = sistema.login(mat, senha);

        if (p == null || !(p instanceof Professor)) {
            System.out.println("❌ Matrícula ou senha incorretas!");
            return;
        } else{
            System.out.println("✅Login Realizado com Sucesso!");
        }

        mostrar((Professor)p);
    }

    private void mostrar(Professor p) {
        int opc = -1;
        while (true) {
            System.out.println("\n=== Menu Professor ===");
            System.out.println("1 - Listar turmas que leciona");
            System.out.println("2 - Lançar nota para aluno");
            System.out.println("3 - Lançar falta para aluno");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            try {
                opc = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida — digite um número.");
                continue;
            }

            switch (opc) {
                case 1 -> listarTurmas(p);
                case 2 -> lancarNota(p);
                case 3 -> lancarFalta(p);
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("❌ Opção inválida!");
            }
        }
    }

    private void listarTurmas(Professor prof) {
        System.out.println("\n=== Turmas Lecionadas ===");

        boolean encontrou = false;
        for (Turma t : cadastro.getTurmas()) {
            if (t.getResponsavel().equals(prof)) {
                encontrou = true;
                System.out.println("• Turma: " + t.getCodigoTurma() + " (" + t.getNomeDisciplina() + ")");
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma turma encontrada.");
        }
    }

    private void lancarNota(Professor prof) {
        System.out.println("\n=== Lançar Nota ===");

        System.out.print("Código da Turma: ");
        String codTurma = sc.nextLine();

        Turma turma = cadastro.buscarTurma(codTurma);

        if (turma == null) {
            System.out.println("❌ Turma não encontrada!");
            return;
        }

        if (!turma.getResponsavel().equals(prof)) {
            System.out.println("❌ Você não leciona esta turma!");
            return;
        }

        System.out.print("Matrícula do aluno: ");
        int mat = Integer.parseInt(sc.nextLine());
        Pessoa p = cadastro.buscarPorMatricula(mat);

        if (!(p instanceof Aluno aluno)) {
            System.out.println("❌ Aluno não encontrado!");
            return;
        }

        System.out.print("Tipo da nota (AV1/AV2/FINAL): ");
        String tipo = sc.nextLine();

        System.out.print("Nota: ");
        double nota = Double.parseDouble(sc.nextLine());

        prof.lancarNota(turma, aluno, nota, tipo);
        System.out.println("✔ Nota lançada com sucesso!");
    }

    private void lancarFalta(Professor prof) {
        System.out.println("\n=== Lançar Falta ===");

        System.out.print("Código da Turma: ");
        String codTurma = sc.nextLine();

        Turma turma = cadastro.buscarTurma(codTurma);

        if (turma == null) {
            System.out.println("❌ Turma não encontrada!");
            return;
        }

        if (!turma.getResponsavel().equals(prof)) {
            System.out.println("❌ Você não é o responsável por esta turma!");
            return;
        }

        System.out.print("Matrícula do aluno: ");
        int mat = Integer.parseInt(sc.nextLine());
        Pessoa p = cadastro.buscarPorMatricula(mat);

        if (!(p instanceof Aluno aluno)) {
            System.out.println("❌ Aluno não encontrado!");
            return;
        }

        prof.lancarFalta(turma, aluno);
        System.out.println("✔ Falta lançada com sucesso!");
    }
}
