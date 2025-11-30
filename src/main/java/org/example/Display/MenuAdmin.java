package org.example.Display;

import org.example.*;
import org.example.Sistema.SistemaEscola;

import java.util.Scanner;

public class MenuAdmin {

    private SistemaEscola sistema;
    private Scanner sc = new Scanner(System.in);

    public MenuAdmin(SistemaEscola sistema) {
        this.sistema = sistema;
    }

    public void loginAdmin() {
        System.out.println("\n-- Login Admin --");
        System.out.print("Matrícula: ");
        int mat;
        try {
            mat = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Matrícula inválida.");
            return;
        }

        System.out.print("Senha: ");
        String senha = sc.nextLine();

        Pessoa p = sistema.login(mat, senha);

        if (p == null || !(p instanceof Admin)) {
            System.out.println("❌ Matrícula ou senha incorretas!");
            return;
        } else {
            System.out.println("✅ Login realizado com sucesso!");
        }

        mostrar((Admin) p);
    }

    public void mostrar(Admin admin) {
        int opc = -1;

        do {
            System.out.println("\n=== Menu Admin ===");
            System.out.println("1 - Cadastrar Aluno");
            System.out.println("2 - Cadastrar Professor");
            System.out.println("3 - Criar Turma");
            System.out.println("4 - Listar Alunos");
            System.out.println("5 - Listar Professores");
            System.out.println("6 - Listar Turmas");
            System.out.println("7 - Matricular Aluno em Turma");
            System.out.println("8 - Listar Alunos por Turma");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            // ler opção com tratamento
            try {
                opc = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida — digite um número.");
                continue;
            }

            switch (opc) {
                case 1:
                    cadastrarAluno(admin);
                    break;
                case 2:
                    cadastrarProfessor(admin);
                    break;
                case 3:
                    criarTurma(admin);
                    break;
                case 4:
                    listarAlunos();
                    break;
                case 5:
                    listarProfessores();
                    break;
                case 6:
                    listarTurmas();
                    break;
                case 7:
                    matricularAlunoEmTurma(admin);
                    break;
                case 8:
                    listarAlunosPorTurma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opc != 0);
    }

    private void cadastrarAluno(Admin admin) {
        try {
            System.out.println("\n--- Cadastro de Aluno ---");

            System.out.print("Nome: ");
            String nome = sc.nextLine();

            System.out.print("CPF: ");
            long cpf = Long.parseLong(sc.nextLine());

            System.out.print("Idade: ");
            int idade = Integer.parseInt(sc.nextLine());

            System.out.print("Sexo (M/F): ");
            char sexo = sc.nextLine().toUpperCase().charAt(0);

            System.out.print("Matrícula: ");
            int matricula = Integer.parseInt(sc.nextLine());

            Aluno novo = admin.cadastrarAluno(nome, cpf, idade, sexo, matricula);
            sistema.getCadastro().addAluno(novo);

            System.out.println("✅ Aluno cadastrado com sucesso!");

        } catch (Exception e) {
            System.out.println("❌ Erro ao cadastrar aluno! Verifique os dados.");
        }
    }

    private void cadastrarProfessor(Admin admin) {
        try {
            System.out.println("\n--- Cadastro de Professor ---");

            System.out.print("Nome: ");
            String nome = sc.nextLine();

            System.out.print("CPF: ");
            long cpf = Long.parseLong(sc.nextLine());

            System.out.print("Idade: ");
            int idade = Integer.parseInt(sc.nextLine());

            System.out.print("Sexo (M/F): ");
            char sexo = sc.nextLine().toUpperCase().charAt(0);

            System.out.print("Matrícula: ");
            int matricula = Integer.parseInt(sc.nextLine());

            Professor novo = admin.cadastrarProfessor(nome, cpf, idade, sexo, matricula);
            sistema.getCadastro().addProfessor(novo);

            System.out.println("✅ Professor cadastrado com sucesso!");

        } catch (Exception e) {
            System.out.println("❌ Erro ao cadastrar professor! Verifique os dados.");
        }
    }

    private void criarTurma(Admin admin) {
        try {
            System.out.println("\n--- Criar Turma ---");

            System.out.print("Código da Turma: ");
            String cod = sc.nextLine();

            System.out.print("Disciplina: ");
            String disc = sc.nextLine();

            System.out.print("Semestre: ");
            String semestre = sc.nextLine();

            System.out.print("Créditos: ");
            int cred = Integer.parseInt(sc.nextLine());

            System.out.print("Matrícula do Professor: ");
            int matProf = Integer.parseInt(sc.nextLine());

            System.out.print("Horario 1: ");
            String hor1 = sc.nextLine();

            System.out.print("Horario 2: ");
            String hor2 = sc.nextLine();

            Professor p = sistema.getCadastro().buscarProfessor(matProf);

            if (p == null) {
                System.out.println("❌ Professor não encontrado!");
                return;
            }

            PlanoDeEnsino plano = new PlanoDeEnsino(p, "Avaliações");

            Turma t = admin.criarTurma(
                    p,
                    disc,
                    cod,
                    semestre,
                    cred,
                    hor1,
                    hor2,
                    plano,
                    disc.length() >= 3 ? disc.substring(0, 3).toUpperCase() + cod : disc.toUpperCase() + cod
            );

            sistema.getCadastro().addTurma(t);

            System.out.println("✅ Turma criada com sucesso!");

        } catch (Exception e) {
            System.out.println("❌ Erro ao criar turma! Verifique os dados.");
        }
    }

    private void matricularAlunoEmTurma(Admin admin) {
        try {
            System.out.print("Matrícula do Aluno: ");
            int matAluno = Integer.parseInt(sc.nextLine());

            Aluno aluno = sistema.getCadastro().buscarAluno(matAluno);
            if (aluno == null) {
                System.out.println("❌ Aluno não encontrado!");
                return;
            }

            System.out.print("Código da Turma: ");
            String codTurma = sc.nextLine();

            Turma turma = sistema.getCadastro().buscarTurma(codTurma);
            if (turma == null) {
                System.out.println("❌ Turma não encontrada!");
                return;
            }

            admin.matricularAlunoEmTurma(aluno, turma);
            System.out.println("✅ Aluno matriculado com sucesso!");

        } catch (Exception e) {
            System.out.println("❌ Erro ao matricular aluno!");
        }
    }

    // LISTAGENS
    private void listarAlunos() {
        System.out.println("\n--- Lista de Alunos ---");
        sistema.getCadastro().getAlunos().forEach(a ->
                System.out.println(a.getMatricula() + " - " + a.getNome())
        );
    }

    private void listarProfessores() {
        System.out.println("\n--- Lista de Professores ---");
        sistema.getCadastro().getProfessores().forEach(p ->
                System.out.println(p.getMatricula() + " - " + p.getNome())
        );
    }

    private void listarTurmas() {
        System.out.println("\n--- Lista de Turmas ---");
        sistema.getCadastro().getTurmas().forEach(t ->
                System.out.println(t.getCodigoTurma() + " - " + t.getNomeDisciplina())
        );
    }

    private void listarAlunosPorTurma() {
        System.out.print("Código da Turma: ");
        String cod = sc.nextLine();
        sistema.getCadastro().listarAlunosDaTurma(cod);
    }
}
