package org.example.Sistema;

import org.example.*;
import org.example.Display.MenuAdmin;
import org.example.Display.MenuAluno;
import org.example.Display.MenuPrincipal;
import org.example.Display.MenuProfessor;

public class SistemaEscola {

    private CadastroPessoas cadastro = new CadastroPessoas();

    private MenuAluno menuAluno;
    private MenuProfessor menuProfessor;
    private MenuAdmin menuAdmin;
    private MenuPrincipal menuPrincipal;

    public SistemaEscola() {

        // --- DADOS DE TESTE INSERIDOS ---

        // 1. Admin
        Admin adm = new Admin("Administrador", 111111111, 30, true, 'M', 1);
        adm.definirNovaSenha("admin");
        cadastro.addAdmin(adm);

        // 2. Professores
        Professor prof1 = new Professor("Maria Silva", 11122233344L, 35, true, 'F', 1001);
        prof1.definirNovaSenha("prof123");
        cadastro.addProfessor(prof1);

        Professor prof2 = new Professor("João Santos", 22233344455L, 42, true, 'M', 1002);
        prof2.definirNovaSenha("prof456");
        cadastro.addProfessor(prof2);

        // 3. Alunos
        Aluno aluno1 = new Aluno("Pedro Henrique", 12345678901L, 20, true, 'M', 2001);
        aluno1.definirNovaSenha("aluno123");
        aluno1.setCurso(Curso.CC);
        aluno1.gerarBoleto(2400.00, "2025-01-15", "2025-02-15");
        cadastro.addAluno(aluno1);

        Aluno aluno2 = new Aluno("Julia Ferreira", 23456789012L, 19, true, 'F', 2002);
        aluno2.definirNovaSenha("aluno456");
        aluno2.setCurso(Curso.ADS);
        Bolsa bolsa1 = new Bolsa("Bolsa Mérito Acadêmico", 50);
        aluno2.setBolsas(bolsa1);
        aluno2.gerarBoleto(1200.00, "2025-01-15", "2025-02-15"); // 50% de desconto
        cadastro.addAluno(aluno2);

        Aluno aluno3 = new Aluno("Lucas Martins", 34567890123L, 21, true, 'M', 2003);
        aluno3.definirNovaSenha("aluno789");
        aluno3.setCurso(Curso.SI);
        aluno3.gerarBoleto(2400.00, "2025-01-15", "2025-02-15");
        cadastro.addAluno(aluno3);

        // 4. Planos de Ensino
        PlanoDeEnsino plano1 = new PlanoDeEnsino(prof1, "Estudo de estruturas de dados fundamentais.");
        PlanoDeEnsino plano2 = new PlanoDeEnsino(prof2, "Paradigmas de programação orientada a objetos.");

        // 5. Turmas
        Turma turma1 = new Turma(prof1, "Algoritmos e Estruturas de Dados", "AED-2025-1",
                "2025.1", 4, "Seg 08:00-10:00", "Qua 08:00-10:00", plano1, "AED001");
        cadastro.addTurma(turma1);

        Turma turma2 = new Turma(prof2, "Programação Orientada a Objetos", "POO-2025-1",
                "2025.1", 4, "Ter 10:00-12:00", "Qui 10:00-12:00", plano2, "POO001");
        cadastro.addTurma(turma2);

        // 6. Matrículas e Notas
        turma1.adicionarAluno(aluno1);
        turma1.adicionarAluno(aluno2);
        turma1.adicionarAluno(aluno3);

        turma2.adicionarAluno(aluno1);
        turma2.adicionarAluno(aluno3);

        // Lançar algumas notas de exemplo
        prof1.lancarNota(turma1, aluno1, 8.5, "N1");
        prof1.lancarNota(turma1, aluno1, 9.0, "N2");
        prof2.lancarNota(turma2, aluno3, 9.5, "N1");

        // --- FIM DOS DADOS DE TESTE INSERIDOS ---


        // Instanciar menus
        this.menuAluno = new MenuAluno(this);
        this.menuProfessor = new MenuProfessor(this);
        this.menuAdmin = new MenuAdmin(this);
        this.menuPrincipal = new MenuPrincipal(this);
    }

    // Getters
    public MenuAluno getMenuAluno() { return menuAluno; }
    public MenuProfessor getMenuProfessor() { return menuProfessor; }
    public MenuAdmin getMenuAdmin() { return menuAdmin; }
    public MenuPrincipal getMenuPrincipal() { return menuPrincipal; }

    public CadastroPessoas getCadastro() {
        return cadastro;
    }

    public Pessoa login(int matricula, String senha) {
        Pessoa p = cadastro.buscarPorMatricula(matricula);
        if (p != null && p.verificarSenha(senha)) {
            return p;
        }
        return null;
    }

    public void iniciar() {
        menuPrincipal.mostrar();
    }
}

