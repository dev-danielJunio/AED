package org.example.Display;

import org.example.*;
import org.example.Sistema.SistemaEscola;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MenuAdmin {

    // Instanciação de bolsas PREDEFINIDAS :D
    Bolsa cotaracial = new Bolsa("Cota Racial", 50);
    Bolsa pobres = new Bolsa("Cota Social", 100);
    Bolsa ENEM = new Bolsa("Cota Enem", 30);

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
            System.out.println("9 - Dar bolsa Aluno");
            System.out.println("10 - Gerar boleto a aluno");
            System.out.println("11 - Excluir Aluno");
            System.out.println("12 - Excluir Professor");
            System.out.println("13 - Excluir Turma");
            System.out.println("14 - Remover bolsa Aluno");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

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
                case 9:
                    darBolsa();
                    break;
                case 10:
                    gerarFatura();
                    break;
                case 11:
                    removerAluno();
                    break;
                case 12:
                    removerProfessor();
                    break;
                case 13:
                    removerTurma();
                    break;
                case 14:
                    removerBolsa();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opc != 0);
    }

    private void removerAluno() {
        System.out.println("\n--- Remover aluno do sistema❗❗❗ [CUIDADO] ---\n");

        System.out.print("Digite a matrícula do Aluno: ");
        int matricula = 0;
        try {
            matricula = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Matrícula inválida.");
            return;
        }

        Aluno aluno = sistema.getCadastro().buscarAluno(matricula);

        if (aluno == null) {
            System.out.println("❌ Aluno não encontrado!");
            return;
        }

        System.out.print("Tem certeza que deseja excluir o aluno " + matricula + "? (S/N): ");
        String confirmacao = sc.nextLine();

        if (!confirmacao.equalsIgnoreCase("S")) {
            System.out.println("Operação cancelada.");
            return;
        }

        boolean sucesso = sistema.getCadastro().removerAluno(matricula);

        if (sucesso) {
            System.out.println("✅ Aluno removido do sistema e de todas as turmas.");
        } else {
            System.out.println("⚠️ Aluno não encontrado com essa matrícula.");
        }

    }

    private void removerProfessor() {
        System.out.println("\n--- Excluir Professor ---");
        System.out.print("Matrícula do Professor: ");
        int matricula;
        try {
            matricula = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Matrícula inválida.");
            return;
        }

        boolean sucesso = sistema.getCadastro().removerProfessor(matricula);

        if (sucesso) {
            System.out.println("✅ Professor removido com sucesso!");
        } else {
            System.out.println("❌ ERRO: Não foi possível remover.");
            System.out.println("Motivo provável: O professor está vinculado a uma turma ativa ou a matrícula não existe.");
            System.out.println("Dica: Remova as turmas deste professor primeiro ou substitua-o.");
        }
    }

    private void removerTurma() {
        System.out.println("\n--- Excluir Turma ---");
        System.out.print("Código da Turma (ex: CCO10-T01): ");
        String codigo = sc.nextLine();

        System.out.println("⚠️ ATENÇÃO: Isso removerá a turma e apagará a matéria do histórico dos alunos matriculados.");
        System.out.print("Tem certeza? (S/N): ");
        String confirmacao = sc.nextLine();

        if (!confirmacao.equalsIgnoreCase("S")) {
            System.out.println("Operação cancelada.");
            return;
        }

        boolean sucesso = sistema.getCadastro().removerTurma(codigo);

        if (sucesso) {
            System.out.println("✅ Turma excluída e dados dos alunos atualizados.");
        } else {
            System.out.println("❌ Turma não encontrada.");
        }
    }

    private void gerarFatura() {
        System.out.println("\n--- Gerar boleto ---\n");

        System.out.print("Digite a matrícula do Aluno: ");
        int matricula = 0;
        try {
            matricula = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Matrícula inválida.");
            return;
        }

        Aluno aluno = sistema.getCadastro().buscarAluno(matricula);

        if (aluno == null) {
            System.out.println("❌ Aluno não encontrado!");
            return;
        }

        System.out.println("Aluno selecionado: " + aluno.getNome());
        int valor = aluno.getCreditos();

        if (aluno.getCurso() == Curso.SI) {
            valor *= 80;
        } else if (aluno.getCurso() == Curso.CC) {
            valor *= 100;
        } else {
            valor *= 120;
        }

        LocalDate data = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String d1 = data.format(formato);
        String d2 = data.plusDays(3).format(formato);
        aluno.gerarBoleto(valor, d1, d2);


    }

    private void darBolsa() {
        System.out.println("\n--- Conceder Bolsa de Estudos ---");

        System.out.print("Digite a matrícula do Aluno: ");
        int matricula = 0;
        try {
            matricula = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Matrícula inválida.");
            return;
        }

        Aluno aluno = sistema.getCadastro().buscarAluno(matricula);

        if (aluno == null) {
            System.out.println("❌ Aluno não encontrado!");
            return;
        }

        System.out.println("Aluno selecionado: " + aluno.getNome());

        System.out.println("Escolha a bolsa:");
        System.out.println("1 - Cota Racial (50%)");
        System.out.println("2 - Cota Social (100%)");
        System.out.println("3 - Cota ENEM (30%)");
        System.out.print("Opção: ");

        int opc = Integer.parseInt(sc.nextLine());
        Bolsa bolsaEscolhida = null;

        switch (opc) {
            case 1:
                bolsaEscolhida = cotaracial;
                break;
            case 2:
                bolsaEscolhida = pobres;
                break;
            case 3:
                bolsaEscolhida = ENEM;
                break;
            default: System.out.println("Opção inválida."); return;
        }

        aluno.setBolsas(bolsaEscolhida);
    }

    private void removerBolsa() {
            System.out.println("\n--- Remover bolsa de Estudos ---");

            System.out.print("Digite a matrícula do Aluno: ");
            int matricula = 0;
            try {
                matricula = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Matrícula inválida.");
                return;
            }

            Aluno aluno = sistema.getCadastro().buscarAluno(matricula);

            if (aluno == null) {
                System.out.println("❌ Aluno não encontrado!\n");
                return;
            }

            System.out.println("Aluno selecionado: " + aluno.getNome());
            aluno.tirarBolsa();
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
            if (sexo != 'M' && sexo != 'F') {
                System.out.printf("❌ Erro ao cadastrar aluno! Verifique os dados.\n");
                return;
            }

            System.out.print("Matrícula: ");
            int matricula = Integer.parseInt(sc.nextLine());

            System.out.print("Curso: (1-CC, 2-SI, 3-ADS): ");
            int curso = Integer.parseInt(sc.nextLine());
            if (curso < 1 || curso > 3) {
                System.out.printf("❌ Erro ao cadastrar aluno! Verifique os dados.\n");
            }

            Aluno novo = admin.cadastrarAluno(nome, cpf, idade, sexo, matricula);
            switch(curso) {
                case 1 -> novo.setCurso(Curso.CC);
                case 2 -> novo.setCurso(Curso.SI);
                case 3 -> novo.setCurso(Curso.ADS);
            }
            sistema.getCadastro().addAluno(novo);


            System.out.println("✅ Aluno cadastrado com sucesso!\n");

        } catch (Exception e) {
            System.out.println("❌ Erro ao cadastrar aluno! Verifique os dados.\n");
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

            System.out.println("✅ Professor cadastrado com sucesso!\n");

        } catch (Exception e) {
            System.out.println("❌ Erro ao cadastrar professor! Verifique os dados.\n");
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

            System.out.println("✅ Turma criada com sucesso!\n");

        } catch (Exception e) {
            System.out.println("❌ Erro ao criar turma! Verifique os dados.\n");
        }
    }

    private void matricularAlunoEmTurma(Admin admin) {
        try {
            System.out.print("Matrícula do Aluno: ");
            int matAluno = Integer.parseInt(sc.nextLine());

            Aluno aluno = sistema.getCadastro().buscarAluno(matAluno);
            if (aluno == null) {
                System.out.println("❌ Aluno não encontrado!\n");
                return;
            }

            System.out.print("Código da Turma: ");
            String codTurma = sc.nextLine();

            Turma turma = sistema.getCadastro().buscarTurma(codTurma);
            if (turma == null) {
                System.out.println("❌ Turma não encontrada!\n");
                return;
            }

            admin.matricularAlunoEmTurma(aluno, turma);
            aluno.setCreditos(+ 4);
            System.out.println("✅ Aluno matriculado com sucesso!\n");

        } catch (Exception e) {
            System.out.println("❌ Erro ao matricular aluno!\n");
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
